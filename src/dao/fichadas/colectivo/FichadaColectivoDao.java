package dao.fichadas.colectivo;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.HibernateUtil;
import modelo.fichadas.colectivo.FichadaColectivo;

public class FichadaColectivoDao {
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
	
	public int agregarFichada(FichadaColectivo fichada) {
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
	
	public void modificarFichada(FichadaColectivo fichada) {
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
	
	public void eliminarFichada(FichadaColectivo fichada) {
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
	
	public FichadaColectivo traerFichada(int idFichada) throws HibernateException {
		FichadaColectivo fichada = null;
		try {
			iniciaOperacion();
			fichada = (FichadaColectivo) session.get(FichadaColectivo.class, idFichada);
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return fichada;
	}
	
	@SuppressWarnings("unchecked")
	public List<FichadaColectivo> traerFichadas() throws HibernateException {
		List<FichadaColectivo> lista = null;
		try {
			iniciaOperacion();
			lista = session.createQuery("from FichadaColectivo f order by f.idFichada asc").list();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return lista;
	}
}
