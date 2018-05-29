package negocio;

import modelo.Permiso;
import dao.PermisoDao;

public class PermisoABM {
	PermisoDao dao = new PermisoDao();
	
	public Permiso traerPermisoPorId(int idPermiso)throws Exception {
		Permiso l = dao.traerPermisoPorId(idPermiso);
		if(l == null) throw new Exception("No existe un permiso con id: " + idPermiso); 
		return l;
	}
	
	public int agregarPermiso(String nombre, String descripcion, String codigo) {
		//validar si existe un permiso con ese nombre y/o codigo, si la hay tirar excepcion
		
		Permiso p = new Permiso(nombre, descripcion, codigo);
		return dao.agregarPermiso(p);
	}
	
	public void modificarPermiso(Permiso p) {
		// implementar antes de actualizar que no exista un permiso con el mismo nombre a modificar lanzar la Exception 
		dao.modificarPermiso(p);
	}
	
	public void eliminarPermisoPorId(int idPermiso) {
		Permiso l = dao.traerPermisoPorId(idPermiso);
		//si es null arrojar exception tambien habria que chequear que este permiso no tenga ninguna dependencia si la tienen tiramos exception o eliminamos las dependencias? 
		dao.eliminarPermiso(l);
	}
}
