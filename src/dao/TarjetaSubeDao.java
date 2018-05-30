package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.HibernateUtil;
import modelo.TarjetaSube;

public class TarjetaSubeDao {
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
	
	public int agregarTarjetaSube(TarjetaSube tarjeta) {
		int idTarjeta = 0;
		try {
			iniciaOperacion();
			idTarjeta = Integer.parseInt(session.save(tarjeta).toString());
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return idTarjeta;
	}
	
	public void modificarTarjetaSube(TarjetaSube tarjeta) {
		try {
			iniciaOperacion();
			session.update(tarjeta);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
	}
	
	public void eliminarTarjetaSube(TarjetaSube tarjeta) {
		try {
			iniciaOperacion();
			session.delete(tarjeta);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
	}
	
	public TarjetaSube traerTarjeta(int idTarjetaSube) throws HibernateException {
		TarjetaSube tarjeta = null;
		try {
			iniciaOperacion();
			tarjeta = (TarjetaSube) session.get(TarjetaSube.class, idTarjetaSube);
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return tarjeta;
	}
	
	public TarjetaSube traerTarjeta(String codigo) throws HibernateException {
		TarjetaSube tarjeta = null;
		try {
			iniciaOperacion();
			tarjeta = (TarjetaSube) session.createQuery("from TarjetaSube t where t.codigo = " + codigo).uniqueResult();
			//tarjeta = (TarjetaSube) session.get(TarjetaSube.class, codigo);
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return tarjeta;
	}
	
	@SuppressWarnings("unchecked")
	public List<TarjetaSube> traerTarjetas() throws HibernateException {
		List<TarjetaSube> lista = null;
		try {
			iniciaOperacion();
			lista = session.createQuery("from TarjetaSUBE t order by t.idTarjetaSube asc").list();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return lista;
	}
}
