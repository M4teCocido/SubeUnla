package dao.fichadas.tren;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.HibernateUtil;
import modelo.fichadas.tren.FichadaTren;

public class FichadaTrenDao {
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
	
	public int agregarFichada(FichadaTren fichada) {
		int id = 0;
		try {
			iniciaOperacion();
			id = Integer.parseInt(session.save(fichada).toString());
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return id;
	}
	
	public void modificarFichada(FichadaTren fichada) {
		try {
			iniciaOperacion();
			session.update(fichada);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
	}
	
	public void eliminarFichada(FichadaTren fichada) {
		try {
			iniciaOperacion();
			session.delete(fichada);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
	}
	
	public FichadaTren traerFichada(int idFichada) throws HibernateException {
		FichadaTren fichada = null;
		try {
			iniciaOperacion();
			fichada = (FichadaTren) session.get(FichadaTren.class, idFichada);
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return fichada;
	}
	
	@SuppressWarnings("unchecked")
	public List<FichadaTren> traerFichadas() throws HibernateException {
		List<FichadaTren> lista = null;
		try {
			iniciaOperacion();
			lista = session.createQuery("from FichadaTren f order by f.idFichada asc").list();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return lista;
	}
}
