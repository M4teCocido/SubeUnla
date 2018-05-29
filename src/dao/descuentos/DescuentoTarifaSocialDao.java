package dao.descuentos;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.HibernateUtil;
import modelo.Descuentos.DescuentoTarifaSocial;

public class DescuentoTarifaSocialDao {
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
	
	public int agregarDescuento(DescuentoTarifaSocial descuento) {
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
	
	public void modificarDescuento(DescuentoTarifaSocial descuento) {
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
	
	public void eliminarDescuento(DescuentoTarifaSocial descuento) {
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
	
	public DescuentoTarifaSocial traerDescuento(int idDescuento) throws HibernateException {
		DescuentoTarifaSocial descuento = null;
		try {
			iniciaOperacion();
			descuento = (DescuentoTarifaSocial) session.get(DescuentoTarifaSocial.class, idDescuento);
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return descuento;
	}
	
	@SuppressWarnings("unchecked")
	public List<DescuentoTarifaSocial> traerDescuentos() throws HibernateException {
		List<DescuentoTarifaSocial> lista = null;
		try {
			iniciaOperacion();
			lista = session.createQuery("from DescuentoTarifaSocial d order by d.idDescuento").list();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return lista;
	}
}
