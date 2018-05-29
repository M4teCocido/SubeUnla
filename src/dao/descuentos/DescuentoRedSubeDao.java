package dao.descuentos;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.HibernateUtil;
import modelo.Descuentos.DescuentoRedSube;

public class DescuentoRedSubeDao {
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
	
	public int agregarDescuento(DescuentoRedSube descuento) {
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
	
	public void modificarDescuento(DescuentoRedSube descuento) {
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
	
	public void eliminarDescuento(DescuentoRedSube descuento) {
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
	
	public DescuentoRedSube traerDescuento(int idDescuento) throws HibernateException {
		DescuentoRedSube descuento = null;
		try {
			iniciaOperacion();
			descuento = (DescuentoRedSube) session.get(DescuentoRedSube.class, idDescuento);
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return descuento;
	}
	
	@SuppressWarnings("unchecked")
	public List<DescuentoRedSube> traerDescuentos() throws HibernateException {
		List<DescuentoRedSube> lista = null;
		try {
			iniciaOperacion();
			lista = session.createQuery("from DescuentoRedSUBE d order by d.idDescuento asc").list();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return lista;
	}
}
