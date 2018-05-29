package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import dao.HibernateUtil;
import dao.descuentos.DescuentoBoletoEstudiantilDao;
import dao.descuentos.DescuentoTarifaSocialDao;
import modelo.Persona;
import modelo.Descuentos.DescuentoBoletoEstudiantil;
import modelo.Descuentos.DescuentoTarifaSocial;

public class PersonaDao {
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
	
	public int agregarPersona(Persona persona) {
		int id = 0;
		try {
			iniciaOperacion();
			id = Integer.parseInt(session.save(persona).toString());
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return id;
	}
	
	public void modificarPersona(Persona persona) {
		try {
			iniciaOperacion();
			session.update(persona);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
	}
	
	public void eliminarPersona(Persona persona) {
		try {
			iniciaOperacion();
			session.delete(persona);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
	}
	
	public Persona traerPersona(int idPersona) throws HibernateException {
		Persona persona = null;
		try {
			iniciaOperacion();
			persona = (Persona) session.get(Persona.class, idPersona); //tambien puede ser persona = (Persona) session.createQuery("from Persona p where p.idPersona=" + idPersona).uniqueResult();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return persona;
	}
	
	public Persona traerPersona(String documento) throws HibernateException {
		Persona persona = null;
		try {
			iniciaOperacion();
			persona = (Persona) session.get(Persona.class, documento);
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return persona;
	}
	
	@SuppressWarnings("unchecked")
	public List<Persona> traerPersonas() throws HibernateException {
		List<Persona> lista = null;
		try {
			iniciaOperacion();
			lista = session.createQuery("from Persona p order by p.idPersona asc").list();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return lista;
	}
}
