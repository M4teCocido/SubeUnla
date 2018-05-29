package dao.fichadas;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.HibernateUtil;
import modelo.fichadas.Fichada;

public class FichadaDao {
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
	
	public int agregarFichada(Fichada fichada) {
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
	
	public void modificarFichada(Fichada fichada) {
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
	
	public void elimninarFichada(Fichada fichada) {
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
	
	public Fichada traerFichada(int idFichada) throws HibernateException {
		Fichada fichada = null;
		try {
			iniciaOperacion();
			fichada = (Fichada) session.get(Fichada.class, idFichada);
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return fichada;
	}
	
	@SuppressWarnings("unchecked")
	public List<Fichada> traerFichadas() throws HibernateException {
		List<Fichada> lista = null;
		try {
			iniciaOperacion();
			lista = session.createQuery("from Fichada f order by f.idFichada asc").list();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return lista;
	}
}
