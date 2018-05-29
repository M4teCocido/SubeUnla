package dao.fichadas.tren;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.HibernateUtil;
import modelo.fichadas.tren.ViajeTren;

public class ViajeTrenDao {
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
	
	public int agregarViaje(ViajeTren viaje) {
		int id = 0;
		try {
			iniciaOperacion();
			id = Integer.parseInt(session.save(viaje).toString());
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return id;
	}
	
	public void modificarViaje(ViajeTren viaje) {
		try {
			iniciaOperacion();
			session.update(viaje);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
	}
	
	public void eliminarViaje(ViajeTren viaje) {
		try {
			iniciaOperacion();
			session.delete(viaje);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
	}
	
	public ViajeTren traerViaje(int idViaje) throws HibernateException {
		ViajeTren fichada = null;
		try {
			iniciaOperacion();
			fichada = (ViajeTren) session.get(ViajeTren.class, idViaje);
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return fichada;
	}
	
	@SuppressWarnings("unchecked")
	public List<ViajeTren> traerFichadas() throws HibernateException {
		List<ViajeTren> lista = null;
		try {
			iniciaOperacion();
			lista = session.createQuery("from ViajeTren v order by v.idViaje asc").list();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return lista;
	}
}
