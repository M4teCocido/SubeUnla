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
	
	public int agregarLinea(String nombre) {
		//validar si existe una tarjeta con ese nombre, si la hay tirar excepcion
		
		LineaTren l = new LineaTren(nombre);
		return dao.agregarLinea(l);
	}
	
	public void modificarLinea(LineaTren l) {
		/* implementar antes de actualizar que no exista un cliente
		con el mismo documento a modificar
		y con el mismo id, lanzar la Exception */
		dao.modificarLinea(l);
	}
	
	public void eliminarLineaPorId(int idLinea) {
		LineaTren l = dao.traerLineaPorId(idLinea);
		/*si es null arrojar exception
		 * tambien habria que chequear que esta linea no tenga ninguna dependencia 
		 * si la tienen tiramos exception o eliminamos las dependencias?*/
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
