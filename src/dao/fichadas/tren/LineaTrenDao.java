package dao.fichadas.tren;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.HibernateUtil;
import modelo.fichadas.tren.LineaTren;

public class LineaTrenDao {
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
	
	public int agregarLinea(LineaTren linea) {
		int id = 0;
		try {
			iniciaOperacion();
			id = Integer.parseInt(session.save(linea).toString());
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return id;
	}
	
	public void modificarLinea(LineaTren linea) {
		try {
			iniciaOperacion();
			session.update(linea);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
	}
	
	public void eliminarLinea(LineaTren linea) {
		try {
			iniciaOperacion();
			session.delete(linea);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
	}
	
	public LineaTren traerLineaPorId(int idLinea) throws HibernateException {
		LineaTren fichada = null;
		try {
			iniciaOperacion();
			fichada = (LineaTren) session.get(LineaTren.class, idLinea);
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return fichada;
	}
	
	@SuppressWarnings("unchecked")
	public List<LineaTren> traerLineas() throws HibernateException {
		List<LineaTren> lista = null;
		try {
			iniciaOperacion();
			lista = session.createQuery("from LineaTren l order by l.idLinea asc").list();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return lista;
	}
}
