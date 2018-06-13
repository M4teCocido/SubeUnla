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
	
	public Usuario traerUsuarioPorDni(String dni) throws Exception {
		Usuario usuario = dao.traerUsuarioPorDni(dni);
		if (usuario == null) throw new Exception("El usuario de dni " + dni + " no existe");
		return usuario;
	}
	
	public int agregarUsuario(String nombreUsuario, String password, Persona persona) throws Exception {
		for (Usuario usuario : dao.traerUsuarios()) {
			if (usuario.getNombreUsuario().equalsIgnoreCase(nombreUsuario) || usuario.getPersona().equals(persona)) throw new Exception("Ya existe un usuario con el nombre: " + nombreUsuario);
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
	
	public void comprobarPassword(String dni, String pass) throws Exception {
		Usuario usuario = dao.traerUsuarioPorDni(dni);
		if(usuario == null || !usuario.getPassword().equals(pass)) {
			throw new Exception("Error al comprobar el usuario.");
		} else if (pass.length() != 4 || !pass.matches("^[0-9]*$")) {
			throw new Exception("La contraseña está mal ingresada, deben ser solo 4 numeros");
		}
	}
	
	public List<Usuario> traerUsuarios() {
		return dao.traerUsuarios();
	}
	
	public Usuario comprobarExistenciaUsuario(String dni){
		return dao.traerUsuarioPorDni(dni);
	}
}
