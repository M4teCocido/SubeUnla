package negocio;

import modelo.fichadas.subte.EstacionSubte;
import modelo.fichadas.subte.LineaSubte;
import modelo.lectoras.LectoraSubte;
import dao.fichadas.subte.EstacionSubteDao;
import dao.fichadas.subte.LineaSubteDao;
import dao.lectoras.LectoraSubteDao;

import java.math.BigDecimal;
import java.util.List;

public class LineaSubteABM {
	
	LineaSubteDao dao = new LineaSubteDao();
	EstacionSubteDao daoEstacion = new EstacionSubteDao();
	LectoraSubteDao daoLectora = new LectoraSubteDao();
	
	public LineaSubte traerLineaPorId(int idLinea)throws Exception {
		LineaSubte l = dao.traerLinea(idLinea);
		if(l == null) throw new Exception("No existe una linea con id: " + idLinea); 
		return l;
	}
	
	public int agregarLinea(String nombre, BigDecimal precio) {
		//validar si existe una linea con ese nombre, si la hay tirar excepcion
		
		LineaSubte l = new LineaSubte(nombre, precio);
		return dao.agregarLinea(l);
	}
	
	public void modificarLinea(LineaSubte l) {
		// implementar antes de actualizar que no exista una linea con el mismo nombre a modificar lanzar la Exception 
		dao.modificarLinea(l);
	}
	
	public void eliminarLineaPorId(int idLinea) {
		LineaSubte l = dao.traerLinea(idLinea);
		/*si es null arrojar exception
		 * tambien habria que chequear que esta linea no tenga ninguna dependencia 
		 * si la tienen tiramos exception o eliminamos las dependencias?*/
		dao.eliminarLinea(l);
	}
	
	public List<LineaSubte> traerLineas(){
		return dao.traerLineas();
	}
	
	public List<EstacionSubte> traerEstacionesPorIdLinea(int idLinea){
		return daoEstacion.traerEstacionesPorIdLinea(idLinea);
	}
	
	public List<LectoraSubte> traerLectorasPorIdEstacion(int idEstacion){
		return daoLectora.traerLectorasPorIdEstacion(idEstacion);
	}
}
