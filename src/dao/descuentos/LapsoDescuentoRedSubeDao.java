package dao.descuentos;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.HibernateUtil;
import modelo.Descuentos.LapsoDescuentoRedSube;

public class LapsoDescuentoRedSubeDao {
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
	
	public int agregarLapso(LapsoDescuentoRedSube lapso) {
		int id = 0;
		try {
			iniciaOperacion();
			id = Integer.parseInt(session.save(lapso).toString());
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return id;
	}
	
	public void modificarLapso(LapsoDescuentoRedSube lapso) {
		try {
			iniciaOperacion();
			session.update(lapso);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
	}
	
	public void eliminarLapso(LapsoDescuentoRedSube lapso) {
		try {
			iniciaOperacion();
			session.delete(lapso);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
	}
	
	public LapsoDescuentoRedSube traerLapso(int idLapso) throws HibernateException {
		LapsoDescuentoRedSube lapso = null;
		try {
			iniciaOperacion();
			lapso = (LapsoDescuentoRedSube) session.get(LapsoDescuentoRedSube.class, idLapso);
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return lapso;
	}
	
	public LapsoDescuentoRedSube traerLapsoPorDescuento(int idDescuento) throws HibernateException {
		LapsoDescuentoRedSube lapso = null;
		try {
			iniciaOperacion();
			lapso = (LapsoDescuentoRedSube) session.createQuery("from lapsodescuentoredsube l WHERE l.idDescuentoRedSube = " + idDescuento).uniqueResult();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return lapso;
	}
	
	@SuppressWarnings("unchecked")
	public List<LapsoDescuentoRedSube> traerDescuentos() throws HibernateException {
		List<LapsoDescuentoRedSube> lista = null;
		try {
			iniciaOperacion();
			lista = session.createQuery("from lapsodescuentoredsube l order by l.idLapso asc").list();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return lista;
	}
}
