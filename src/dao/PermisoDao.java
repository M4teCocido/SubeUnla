package dao;


import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.HibernateUtil;
import modelo.Permiso;

public class PermisoDao {

	private static Session session;
	private Transaction tx;
	
	private void iniciaOperacion() throws HibernateException {
		session = HibernateUtil.getSessionFactory().openSession();
		tx = session.beginTransaction();	
	}
	
	private void manejaExcepcion(HibernateException he) throws HibernateException {
		tx.rollback();
		throw new HibernateException("ERROR en la capa de acceso a datos" , he);
	}
	
	public int agregarPermiso(Permiso permiso) {
		int id = 0;
		try {
			iniciaOperacion();
			System.out.println("Hola");
			System.out.println(session.save(permiso).toString());
			id = Integer.parseInt(session.save(permiso).toString());
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return id;
	}
	
	public void modificarPermiso(Permiso permiso) throws HibernateException{
		try {
			iniciaOperacion();
			session.update(permiso);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
	}
	
	public void eliminarPermiso(Permiso permiso) throws HibernateException{
		try {
			iniciaOperacion();
			session.delete(permiso);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
	}
	
	public Permiso traerPermisoPorId(int idPermiso) throws HibernateException {
		Permiso permiso = null;
		try {
			iniciaOperacion();
			permiso = (Permiso) session.get(Permiso.class, idPermiso);
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return permiso;
	}
	
	public Permiso traerPermisoPorNombre(String nombre) throws HibernateException {
		Permiso permiso = null;
		try {
			iniciaOperacion();
			permiso = (Permiso) session.get(Permiso.class, nombre);
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return permiso;
	}
	
	public Permiso traerPermisoPorCodigo(String codigo) {
		Permiso permiso = null;
		try {
			iniciaOperacion();
			permiso = (Permiso) session.createQuery("from permiso p where p.codigo = " + codigo).uniqueResult();
		} finally {
			session.close();
		}
		return permiso;
	}
	
	@SuppressWarnings("unchecked")
	public List<Permiso> traerPermisos() throws HibernateException {
		List<Permiso> lista = null;
		try {
			iniciaOperacion();
			lista = session.createQuery("from Permiso p order by p.idPermiso asc").list();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return lista;
	}
}
