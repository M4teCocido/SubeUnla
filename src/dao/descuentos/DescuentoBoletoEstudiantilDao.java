package dao.descuentos;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.HibernateUtil;
import javassist.runtime.Desc;
import modelo.Descuentos.DescuentoBoletoEstudiantil;

public class DescuentoBoletoEstudiantilDao {
	
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
	
	public int agregarDescuento(DescuentoBoletoEstudiantil descuento) {
		int id = 0;
		try {
			iniciaOperacion();
			id = Integer.parseInt(session.save(descuento).toString());
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return id;
	}
	
	public void modificarDescuento(DescuentoBoletoEstudiantil descuento) {
		try {
			iniciaOperacion();
			session.update(descuento);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
	}
	
	public void eliminarDescuento(DescuentoBoletoEstudiantil descuento) {
		try {
			iniciaOperacion();
			session.delete(descuento);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
	}
	
	public DescuentoBoletoEstudiantil traerDescuento(int idDescuento) throws HibernateException {
		DescuentoBoletoEstudiantil descuento = null;
		try {
			iniciaOperacion();
			descuento = (DescuentoBoletoEstudiantil) session.get(DescuentoBoletoEstudiantil.class, idDescuento);
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return descuento;
	}
	
	@SuppressWarnings("unchecked")
	public List<DescuentoBoletoEstudiantil> traerDescuentos() throws HibernateException {
		List<DescuentoBoletoEstudiantil> lista = null;
		try {
			iniciaOperacion();
			lista = session.createQuery("from DescuentoBoletoEstudiantil d order by d.idDescuento asc").list();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return lista;
	}
}
