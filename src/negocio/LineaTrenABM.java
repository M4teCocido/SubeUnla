package negocio;

import modelo.fichadas.tren.EstacionTren;
import modelo.fichadas.tren.LineaTren;
import modelo.lectoras.LectoraTren;

import java.util.List;

import dao.fichadas.tren.EstacionTrenDao;
import dao.fichadas.tren.LineaTrenDao;
import dao.lectoras.LectoraTrenDao;

public class LineaTrenABM {
	
	LineaTrenDao dao = new LineaTrenDao();
	EstacionTrenDao daoEstacion = new EstacionTrenDao();
	LectoraTrenDao daoLectora = new LectoraTrenDao();
	
	public LineaTren traerLineaPorId(int idLinea)throws Exception {
		LineaTren l = dao.traerLineaPorId(idLinea);
		if(l == null) throw new Exception("No existe una linea con id: " + idLinea); 
		return l;
	}
	
	public int agregarLinea(String nombre) throws Exception {
		for (LineaTren linea : dao.traerLineas()) {
			if (linea.getNombre().equalsIgnoreCase(nombre)) throw new Exception("La linea " + nombre + " ya existe");
		}
		LineaTren l = new LineaTren(nombre);
		return dao.agregarLinea(l);
	}
	
	public void modificarLinea(LineaTren l) throws Exception {
		for (LineaTren linea : dao.traerLineas()) {
			if (linea.getNombre().equalsIgnoreCase(l.getNombre())) throw new Exception("La linea " + l + " ya existe");
		}
		dao.modificarLinea(l);
	}
	
	public void eliminarLineaPorId(int idLinea) throws Exception {
		LineaTren l = dao.traerLineaPorId(idLinea);
		if (l == null) throw new Exception("La linea de id " + idLinea + " no existe");
		dao.eliminarLinea(l);
	}
	
	public List<LineaTren> traerLineas(){
		return dao.traerLineas();
	}
	
	public List<EstacionTren> traerEstacionesPorIdLinea(int idLinea){
		return daoEstacion.traerEstacionPorIdLinea(idLinea);
	}
	
	public List<LectoraTren> traerLectorasPorIdEstacion(int idEstacion){	
		return daoLectora.traerLectorasPorIdEstacion(idEstacion);
	}
}
