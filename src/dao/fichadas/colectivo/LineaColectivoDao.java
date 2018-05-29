package dao.fichadas.colectivo;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.HibernateUtil;
import modelo.fichadas.colectivo.LineaColectivo;

public class LineaColectivoDao {
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
	
	public int agregarLinea(LineaColectivo linea) {
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
	
	public void modificarLinea(LineaColectivo linea) {
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
	
	public void eliminarLinea(LineaColectivo linea) {
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
	
	public LineaColectivo traerLineaPorId(int idLinea) throws HibernateException {
		LineaColectivo linea = null;
		try {
			iniciaOperacion();
			linea = (LineaColectivo) session.get(LineaColectivo.class, idLinea);
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return linea;
	}
	
	@SuppressWarnings("unchecked")
	public List<LineaColectivo> traerLineas() throws HibernateException {
		List<LineaColectivo> lista = null;
		try {
			iniciaOperacion();
			lista = session.createQuery("from LineaColectivo l order by l.idLinea asc").list();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return lista;
	}

}
