package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.HibernateUtil;
import modelo.Usuario;

public class UsuarioDao {
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
	
	public int agregarUsuario(Usuario usuario) {
		int idUsuario = 0;
		try {
			iniciaOperacion();
			idUsuario = Integer.parseInt(session.save(usuario).toString());
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return idUsuario;
	}
	
	public void modificarUsuario(Usuario usuario) {
		try {
			iniciaOperacion();
			session.update(usuario);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
	}
	
	public void eliminarUsuario(Usuario usuario) {
		try {
			iniciaOperacion();
			session.delete(usuario);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
	}
	
	public Usuario traerUsuarioPorId(int idUsuario) throws HibernateException {
		Usuario usuario = null;
		try {
			iniciaOperacion();
			usuario = (Usuario) session.get(Usuario.class, idUsuario);
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return usuario;
	}
	
	public Usuario traerUsuarioPorNombre(String nombre) {
		Usuario usuario = null;
		try {
			iniciaOperacion();
			usuario = (Usuario) session.createQuery("from Usuario u where u.nombreUsuario = '" + nombre + "'").uniqueResult();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return usuario;
	}
	
	public Usuario traerUsuarioPorUsername(String username) {
		Usuario usuario = null;
		try {
			iniciaOperacion();
			Object result = (session.createQuery("from Usuario u where u.nombreUsuario = '" + username + "'").uniqueResult());
			if (result != null && result.getClass().isArray()) {
				Object [] arr = (Object[]) result;
				int i = 0;
				for (Object elt : arr) {
					System.out.println(i + "elt : " + elt.toString());
					i++;
				}
				usuario = ((Usuario) arr[0]);
				
			} else {
				usuario = ((Usuario) result);
			}
			//usuario = ((Usuario) result);
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return usuario;
	}
	
	public Usuario traerUsuarioPorDni(String dni) throws HibernateException {
		Usuario usuario = null;
		try {
			iniciaOperacion();
			Object result = (session.createQuery("from Usuario u inner join u.Persona p inner join p.documento d where d.numero='" + dni + "'").uniqueResult());
			if (result != null && result.getClass().isArray()) {
				Object [] arr = (Object[]) result;
				int i = 0;
				for (Object elt : arr) {
					System.out.println(i + "elt : " + elt.toString());
					i++;
				}
				usuario = ((Usuario) arr[0]);
				
			} else {
				usuario = ((Usuario) result);
			}
			
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return usuario;
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> traerUsuarios() throws HibernateException {
		List<Usuario> lista = null;
		try {
			iniciaOperacion();
			lista = session.createQuery("from Usuario u order by u.idUsuario asc").list();
		} catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		} finally {
			session.close();
		}
		return lista;
	}
}
