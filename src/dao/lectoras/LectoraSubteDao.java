package dao.lectoras;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.HibernateUtil;
import modelo.lectoras.LectoraSubte;

public class LectoraSubteDao {
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
	
	public int agregarLectora(LectoraSubte lectora) {
		int id = 0;
		try {
			iniciaOperacion();
			id = Integer.parseInt(session.save(lectora).toString());
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return id;
	}
	
	public void modificarLectora(LectoraSubte lectora) {
		try {
			iniciaOperacion();
			session.update(lectora);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
	}
	
	public void elimninarLectora(LectoraSubte lectora) {
		try {
			iniciaOperacion();
			session.delete(lectora);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
	}
	
	public LectoraSubte traerLectora(int idLectora) throws HibernateException {
		LectoraSubte lectora= null;
		try {
			iniciaOperacion();
			lectora = (LectoraSubte) session.get(LectoraSubte.class, idLectora);
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return lectora;
	}
	
	@SuppressWarnings("unchecked")
	public List<LectoraSubte> traerLectoras() throws HibernateException {
		List<LectoraSubte> lista = null;
		try {
			iniciaOperacion();
			lista = session.createQuery("from LectoraSubte l order by l.idLectora asc").list();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public List<LectoraSubte> traerLectorasPorIdEstacion(int idEstacion) throws HibernateException {
		List<LectoraSubte> lista = null;
		try {
			iniciaOperacion();
			lista = session.createQuery("from LectoraSubte l where l.idEstacion =" + idEstacion +  " order by l.idLectora asc").list();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return lista;
	}
}
