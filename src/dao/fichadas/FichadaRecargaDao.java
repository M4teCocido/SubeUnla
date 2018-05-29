package dao.fichadas;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.HibernateUtil;
import modelo.fichadas.FichadaRecarga;

public class FichadaRecargaDao {
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
	
	public int agregarFichadaRecarga(FichadaRecarga fichada) {
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
	
	public void modificarFichadaRecarga(FichadaRecarga fichada) {
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
	
	public void elimninarFichadaRecarga(FichadaRecarga fichada) {
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
	
	public FichadaRecarga traerFichada(int idFichada) throws HibernateException {
		FichadaRecarga fichada = null;
		try {
			iniciaOperacion();
			fichada = (FichadaRecarga) session.get(FichadaRecarga.class, idFichada);
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return fichada;
	}
	
	@SuppressWarnings("unchecked")
	public List<FichadaRecarga> traerFichadas() throws HibernateException {
		List<FichadaRecarga> lista = null;
		try {
			iniciaOperacion();
			lista = session.createQuery("from FichadaRecarga f order by f.idFichada asc").list();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return lista;
	}
}
