package dao.fichadas.colectivo;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.HibernateUtil;
import modelo.fichadas.colectivo.TramoColectivo;

public class TramoColectivoDao {
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
	
	public int agregarTramo(TramoColectivo tramo) {
		int id = 0;
		try {
			iniciaOperacion();
			id = Integer.parseInt(session.save(tramo).toString());
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return id;
	}
	
	public void modificarTramo(TramoColectivo tramo) {
		try {
			iniciaOperacion();
			session.update(tramo);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
	}
	
	public void eliminarTramo(TramoColectivo tramo) {
		try {
			iniciaOperacion();
			session.delete(tramo);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
	}
	
	public TramoColectivo traerTramo(int idtramo) throws HibernateException {
		TramoColectivo tramo = null;
		try {
			iniciaOperacion();
			tramo = (TramoColectivo) session.get(TramoColectivo.class, idtramo);
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return tramo;
	}
	
	@SuppressWarnings("unchecked")
	public List<TramoColectivo> traerTramos() throws HibernateException {
		List<TramoColectivo> lista = null;
		try {
			iniciaOperacion();
			lista = session.createQuery("from TramoColectivo t order by t.idTramo asc").list();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return lista;
	}
}
