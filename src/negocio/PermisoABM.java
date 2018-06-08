package negocio;

import modelo.Permiso;

import java.util.List;

import dao.PermisoDao;

public class PermisoABM {
	PermisoDao dao = new PermisoDao();
	
	public Permiso traerPermisoPorId(int idPermiso)throws Exception {
		Permiso l = dao.traerPermisoPorId(idPermiso);
		if(l == null) throw new Exception("No existe un permiso con id: " + idPermiso); 
		return l;
	}
	
	public int agregarPermiso(String nombre, String descripcion, String codigo) throws Exception {
		for (Permiso permiso : dao.traerPermisos()) {
			if (permiso.getNombre().equalsIgnoreCase(nombre) || permiso.getCodigo().equalsIgnoreCase(codigo)) throw new Exception("El permiso de nombre; " + nombre + " o codigo: " + codigo + " ya existe");
		}
		Permiso p = new Permiso(nombre, descripcion, codigo);
		return dao.agregarPermiso(p);
	}
	
	public void modificarPermiso(Permiso p) throws Exception {
		for (Permiso permiso : dao.traerPermisos()) {
			if (permiso.equals(p)) throw new Exception("El permiso " + p + " ya existe");
		}
		dao.modificarPermiso(p);
	}
	
	public void eliminarPermisoPorId(int idPermiso) throws Exception {
		Permiso l = dao.traerPermisoPorId(idPermiso);
		if (l == null) throw new Exception("El permiso de id: " + idPermiso + " no existe");
		dao.eliminarPermiso(l);
	}
	
	public List<Permiso> traerPermisos() {
		return dao.traerPermisos();
	}
}
