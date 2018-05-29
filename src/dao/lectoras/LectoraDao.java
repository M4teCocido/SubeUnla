package dao.lectoras;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.HibernateUtil;
import modelo.lectoras.Lectora;

public class LectoraDao {
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
	
	public int agregarLectora(Lectora lectora) {
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
	
	public void modificarLectora(Lectora lectora) {
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
	
	public void elimninarLectora(Lectora lectora) {
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
	
	public Lectora traerLectora(int idLectora) throws HibernateException {
		Lectora lectora= null;
		try {
			iniciaOperacion();
			lectora = (Lectora) session.get(Lectora.class, idLectora);
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return lectora;
	}
	
	@SuppressWarnings("unchecked")
	public List<Lectora> traerLectoras() throws HibernateException {
		List<Lectora> lista = null;
		try {
			iniciaOperacion();
			lista = session.createQuery("from Lectora l order by l.idLectora asc").list();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return lista;
	}
}
