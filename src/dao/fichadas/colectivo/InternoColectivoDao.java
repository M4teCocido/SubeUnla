package dao.fichadas.colectivo;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.HibernateUtil;
import modelo.fichadas.colectivo.InternoColectivo;

public class InternoColectivoDao {

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
	
	public int agregarInterno(InternoColectivo interno) {
		int id = 0;
		try {
			iniciaOperacion();
			id = Integer.parseInt(session.save(interno).toString());
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return id;
	}
	
	public void modificarInterno(InternoColectivo interno) {
		try {
			iniciaOperacion();
			session.update(interno);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
	}
	
	public void eliminarInterno(InternoColectivo interno) {
		try {
			iniciaOperacion();
			session.delete(interno);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
	}
	
	public InternoColectivo traerInterno(int idInterno) throws HibernateException {
		InternoColectivo interno = null;
		try {
			iniciaOperacion();
			interno = (InternoColectivo) session.get(InternoColectivo.class, idInterno);
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return interno;
	}
	
	@SuppressWarnings("unchecked")
	public List<InternoColectivo> traerInternos() throws HibernateException {
		List<InternoColectivo> lista = null;
		try {
			iniciaOperacion();
			lista = session.createQuery("from InternoColectivo t order by t.idInterno asc").list();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return lista;
	}
	
	@SuppressWarnings("unchecked")
	public List<InternoColectivo> traerInternosPorIdLinea(int idLinea){
		List<InternoColectivo> lista = null;
		try {
			iniciaOperacion();
			lista = session.createQuery("from InternoColectivo i where i.idLinea = " + idLinea + " order by i.idInterno").list();
		}catch(HibernateException he) {
			manejaExcepcion(he);
			throw he;
		}finally {
			session.close();
		}
		return lista;
	}
}
