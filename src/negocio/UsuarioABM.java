package negocio;

import modelo.Persona;
import modelo.Usuario;

import java.util.List;

import dao.UsuarioDao;

public class UsuarioABM {
UsuarioDao dao = new UsuarioDao();
	
	public Usuario traerUsuarioPorId(int idUsuario)throws Exception {
		Usuario u = dao.traerUsuarioPorId(idUsuario);
		if(u == null) throw new Exception("No existe un permiso con id: " + idUsuario); 
		return u;
	}
	
	public int agregarUsuario(String nombreUsuario, String password, Persona persona) throws Exception {
		for (Usuario usuario : dao.traerUsuarios()) {
			if (usuario.getNombreUsuario().equalsIgnoreCase(nombreUsuario) || usuario.getPersona().equals(persona)) throw new Exception("Ya existe un usuario con el nombre: " + nombreUsuario + " o persona: " + persona);
		}
		Usuario u = new Usuario(nombreUsuario, password, persona);
		return dao.agregarUsuario(u);
	}
	
	public void modificarUsuario(Usuario u) throws Exception {
		for (Usuario usuario : dao.traerUsuarios()) {
			if (usuario.getNombreUsuario().equalsIgnoreCase(u.getNombreUsuario())) throw new Exception("Ya existe un usuario con el nombre: " + u.getNombreUsuario());
		}
		dao.modificarUsuario(u);
	}
	
	public void eliminarPermisoPorId(int idUsuario) throws Exception {
		Usuario u = dao.traerUsuarioPorId(idUsuario);
		if (u == null) throw new Exception("El usuario de id: " + idUsuario + " ya existe");
		dao.eliminarUsuario(u);
	}
	
	public List<Usuario> traerUsuarios() {
		return dao.traerUsuarios();
	}
}
