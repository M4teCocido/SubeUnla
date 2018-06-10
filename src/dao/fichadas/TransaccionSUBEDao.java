package dao.fichadas;

import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.HibernateUtil;
import modelo.fichadas.TransaccionSUBE;

public class TransaccionSUBEDao {
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
	
	public int agregarTransaccion(TransaccionSUBE transaccion) {
		int id = 0;
		try {
			iniciaOperacion();
			id = Integer.parseInt(session.save(transaccion).toString());
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return id;
	}
	
	public void modificarTransaccion(TransaccionSUBE transaccion) {
		try {
			iniciaOperacion();
			session.update(transaccion);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
	}
	
	public void eliminarTransaccion(TransaccionSUBE transaccion) {
		try {
			iniciaOperacion();
			session.delete(transaccion);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
	}
	
	public TransaccionSUBE traerTransaccion(int idTransaccion) throws HibernateException {
		TransaccionSUBE transaccion = null;
		try {
			iniciaOperacion();
			transaccion = (TransaccionSUBE) session.get(TransaccionSUBE.class, idTransaccion);
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return transaccion;
	}
	
	@SuppressWarnings("unchecked")
	public List<TransaccionSUBE> traerTransacciones() throws HibernateException {
		List<TransaccionSUBE> lista = null;
		try {
			iniciaOperacion();
			lista = session.createQuery("from TransaccionSUBE t order by t.idTransaccion asc").list();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return lista;
	}
	
	
	
	
}
