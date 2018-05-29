package dao.fichadas.tren;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.HibernateUtil;
import modelo.fichadas.tren.SeccionTren;

public class SeccionTrenDao {
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
	
	public int agregarSeccion(SeccionTren seccion) {
		int id = 0;
		try {
			iniciaOperacion();
			id = Integer.parseInt(session.save(seccion).toString());
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return id;
	}
	
	public void modificarSeccion(SeccionTren seccion) {
		try {
			iniciaOperacion();
			session.update(seccion);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
	}
	
	public void eliminarSeccion(SeccionTren seccion) {
		try {
			iniciaOperacion();
			session.delete(seccion);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
	}
	
	public SeccionTren traerSeccion(int idSeccion) throws HibernateException {
		SeccionTren fichada = null;
		try {
			iniciaOperacion();
			fichada = (SeccionTren) session.get(SeccionTren.class, idSeccion);
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return fichada;
	}
	
	@SuppressWarnings("unchecked")
	public List<SeccionTren> traerSecciones() throws HibernateException {
		List<SeccionTren> lista = null;
		try {
			iniciaOperacion();
			lista = session.createQuery("from SeccionTren s order by s.idSeccion asc").list();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return lista;
	}
}
