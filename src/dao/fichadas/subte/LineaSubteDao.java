package dao.fichadas.subte;

import java.util.List;

import javax.sound.sampled.Line;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.HibernateUtil;
import modelo.fichadas.subte.LineaSubte;

public class LineaSubteDao {
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
	
	public int agregarLinea(LineaSubte linea) {
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
	
	public void modificarLinea(LineaSubte linea) {
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
	
	public void eliminarLinea(LineaSubte linea) {
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
	
	public LineaSubte traerLinea(int idLinea) throws HibernateException {
		LineaSubte linea = null;
		try {
			iniciaOperacion();
			linea = (LineaSubte) session.get(LineaSubte.class, idLinea);
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return linea;
	}
	
	@SuppressWarnings("unchecked")
	public List<LineaSubte> traerLineas() throws HibernateException {
		List<LineaSubte> lista = null;
		try {
			iniciaOperacion();
			lista = session.createQuery("from LineaSubte l order by l.idLinea asc").list();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return lista;
	}
}
