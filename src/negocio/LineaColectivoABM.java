package negocio;

import java.util.List;

import dao.fichadas.colectivo.InternoColectivoDao;
import dao.fichadas.colectivo.LineaColectivoDao;
import modelo.fichadas.colectivo.InternoColectivo;
import modelo.fichadas.colectivo.LineaColectivo;

public class LineaColectivoABM {
	LineaColectivoDao dao = new LineaColectivoDao();
	InternoColectivoDao daoInterno = new InternoColectivoDao();
	
	public LineaColectivo traerLineaPorId(int idLinea)throws Exception {
		LineaColectivo l = dao.traerLineaPorId(idLinea);
		if(l == null) throw new Exception("No existe una linea con id: " + idLinea); 
		return l;
	}
	
	public int agregar(String nombre) {
		//validar si existe una linea con ese nombre, si la hay tirar excepcion
		
		LineaColectivo l = new LineaColectivo(nombre);
		return dao.agregarLinea(l);
	}
	
	public void modificar(LineaColectivo l) {
		/* implementar antes de actualizar que no exista una linea
		con el mismo nombre a modificar
		y con el mismo id, lanzar la Exception */
		dao.modificarLinea(l);
	}
	
	public void eliminarPorId(int idLinea) {
		LineaColectivo l = dao.traerLineaPorId(idLinea);
		/*si es null arrojar exception
		 * tambien habria que chequear que esta linea no tenga ninguna dependencia 
		 * si la tienen tiramos exception o eliminamos las dependencias?*/
		dao.eliminarLinea(l);
	}
	
	public List<LineaColectivo> traerLineas(){
		return dao.traerLineas();
	}
	
	public List<InternoColectivo> traerInternosPorIdLinea(int idLinea){
		return daoInterno.traerInternosPorIdLinea(idLinea);
	}
}
