package negocio;

import java.util.ArrayList;
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
	
	public int agregar(String nombre) throws Exception {
		for (LineaColectivo linea : dao.traerLineas()) {
			if (linea.getNombre().equalsIgnoreCase(nombre)) throw new Exception("La linea con nombre " + nombre + " ya existe");
		}
		LineaColectivo l = new LineaColectivo(nombre);
		return dao.agregarLinea(l);
	}
	
	public void modificar(LineaColectivo l) throws Exception {
		for (LineaColectivo linea : dao.traerLineas()) {
			if (linea.getNombre().equalsIgnoreCase(l.getNombre())) throw new Exception("La linea con nombre " + l + " ya existe");
		}
		dao.modificarLinea(l);
	}
	
	public void eliminarPorId(int idLinea) throws Exception {
		LineaColectivo l = dao.traerLineaPorId(idLinea);
		if (l == null) throw new Exception("La linea de id: " + idLinea + " no existe");
		dao.eliminarLinea(l);
	}
	
	public List<LineaColectivo> traerLineas(){
		return dao.traerLineas();
	}

	public List<InternoColectivo> traerInternosPorIdLinea(int idLinea) throws Exception{
		List <InternoColectivo> internos = new ArrayList<InternoColectivo>();
		internos.addAll(traerLineaPorId(idLinea).getInternosColectivo());
		return internos;
	}
}
