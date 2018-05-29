package dao.fichadas.subte;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.HibernateUtil;
import modelo.fichadas.subte.FichadaSubte;

public class FichadaSubteDao {
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
	
	public int agregarFichada(FichadaSubte fichada) {
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
	
	public void modificarFichada(FichadaSubte fichada) {
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
	
	public void eliminarFichada(FichadaSubte fichada) {
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
	
	public FichadaSubte traerFichada(int idFichada) throws HibernateException {
		FichadaSubte fichada = null;
		try {
			iniciaOperacion();
			fichada = (FichadaSubte) session.get(FichadaSubte.class, idFichada);
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return fichada;
	}
	
	@SuppressWarnings("unchecked")
	public List<FichadaSubte> traerFichadas() throws HibernateException {
		List<FichadaSubte> lista = null;
		try {
			iniciaOperacion();
			lista = session.createQuery("from FichadaSubte f order by f.idFichada").list();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return lista;
	}
}
