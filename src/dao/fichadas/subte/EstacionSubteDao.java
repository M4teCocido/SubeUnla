package dao.fichadas.subte;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.HibernateUtil;
import modelo.fichadas.subte.EstacionSubte;

public class EstacionSubteDao {
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
	
	public int agregarEstacion(EstacionSubte estacion) {
		int id = 0;
		try {
			iniciaOperacion();
			id = Integer.parseInt(session.save(estacion).toString());
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return id;
	}
	
	public void modificarEstacion(EstacionSubte estacion) {
		try {
			iniciaOperacion();
			session.update(estacion);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
	}
	
	public void eliminarEstacion(EstacionSubte estacion) {
		try {
			iniciaOperacion();
			session.delete(estacion);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
	}
	
	public EstacionSubte traerEstacion(int idEstacion) throws HibernateException {
		EstacionSubte estacion = null;
		try {
			iniciaOperacion();
			estacion = (EstacionSubte) session.get(EstacionSubte.class, idEstacion);
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return estacion;
	}
	
	@SuppressWarnings("unchecked")
	public List<EstacionSubte> traerEstaciones() throws HibernateException {
		List<EstacionSubte> lista = null;
		try {
			iniciaOperacion();
			lista = session.createQuery("from EstacionSubte e order by e.idEstacion asc").list();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public List<EstacionSubte> traerEstacionPorIdLinea (int idLinea) throws HibernateException {
		List<EstacionSubte> lista = null;
		try {
			iniciaOperacion();
			lista = session.createQuery("from EstacionSubte e where e.idLinea = " + idLinea + "order by e.idEstacion").list();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return lista;
	}
}
