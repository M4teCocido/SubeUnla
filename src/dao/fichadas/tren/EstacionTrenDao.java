package dao.fichadas.tren;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.HibernateUtil;
import modelo.fichadas.tren.EstacionTren;

public class EstacionTrenDao {
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
	
	public int agregarEstacion(EstacionTren estacion) {
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
	
	public void modificarEstacion(EstacionTren estacion) {
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
	
	public void eliminarEstacion(EstacionTren estacion) {
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
	
	public EstacionTren traerEstacion(int idEstacion) throws HibernateException {
		EstacionTren estacion = null;
		try {
			iniciaOperacion();
			estacion = (EstacionTren) session.get(EstacionTren.class, idEstacion);
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return estacion;
	}
	
	@SuppressWarnings("unchecked")
	public List<EstacionTren> traerEstaciones() throws HibernateException {
		List<EstacionTren> lista = null;
		try {
			iniciaOperacion();
			lista = session.createQuery("from EstacionTren e order by e.idEstacion asc").list();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public List<EstacionTren> traerEstacionPorIdLinea(int idLinea){
		List<EstacionTren> lista = null;
		try {
			iniciaOperacion();
			lista = session.createQuery("from EstacionTren e where e.idLinea = " + idLinea + "order by e.idEstacion").list();
		}catch(HibernateException he) {
			manejaExcepcion(he);
			throw he;
		}finally {
			session.close();
		}
		return lista;
	}
}
