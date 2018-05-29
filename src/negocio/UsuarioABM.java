package negocio;

import modelo.Persona;
import modelo.Usuario;
import dao.UsuarioDao;

public class UsuarioABM {
	UsuarioDao dao = new UsuarioDao();
	
	public Usuario traerUsuarioPorId(int idUsuario)throws Exception {
		Usuario u = dao.traerUsuarioPorId(idUsuario);
		if(u == null) throw new Exception("No existe un permiso con id: " + idUsuario); 
		return u;
	}
	
	public int agregarPermiso(String nombreUsuario, String password, Persona persona) {
		//validar si existe un usuario con ese nombre y/o Persona, si la hay tirar excepcion
		
		Usuario u = new Usuario(nombreUsuario, password, persona);
		return dao.agregarUsuario(u);
	}
	
	public void modificarUsuario(Usuario u) {
		/* implementar antes de actualizar que no exista un user
		con el mismo nombre a modificar
		y con el mismo id, lanzar la Exception */
		dao.modificarUsuario(u);
	}
	
	public void eliminarPermisoPorId(int idUsuario) {
		Usuario u = dao.traerUsuarioPorId(idUsuario);
		/*si es null arrojar exception
		 * tambien habria que chequear que el user no tenga ninguna dependencia 
		 * si la tienen tiramos exception o eliminamos las dependencias?*/
		dao.eliminarUsuario(u);
	}
}
