package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.HibernateUtil;
import modelo.Documento;

public class DocumentoDao {
	private static Session session;
	private Transaction tx;
	
	private void iniciaOperacion() throws HibernateException {
		session = HibernateUtil.getSessionFactory().openSession();
		tx = session.beginTransaction();	
	}
	
	private void manejaExcepcion(HibernateException he) {
		tx.rollback();
		throw new HibernateException("ERROR en la capa de acceso a datos" , he);
	}
	
	public int agregarDocumento(Documento documento) {
		int id = 0;
		try {
			iniciaOperacion();
			id = Integer.parseInt(session.save(documento).toString());
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return id;
	}
	
	public void modificarDocumento(Documento documento) {
		try {
			iniciaOperacion();
			session.update(documento);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
	}
	
	public void eliminarDocumento(Documento documento) {
		try {
			iniciaOperacion();
			session.delete(documento);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
	}
	
	public Documento traerDocumento(int idDocumento) throws HibernateException {
		Documento documento = null;
		try {
			iniciaOperacion();
			documento = (Documento) session.get(Documento.class, idDocumento);
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return documento;
	}
	
	public Documento traerDocumento(String numero) throws HibernateException {
		Documento documento = null;
		try {
			iniciaOperacion();
			documento = (Documento) session.get(Documento.class, numero);
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return documento;
	}
	
	@SuppressWarnings("unchecked")
	public List<Documento> traerDocumentos() throws HibernateException {
		List<Documento> lista = null;
		try {
			iniciaOperacion();
			lista = session.createQuery("from Documento d order by d.idDocumento asc").list();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return lista;
	}
}
