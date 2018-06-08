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
	
	public int agregarLinea(String nombre, BigDecimal precio) throws Exception {
		for (LineaSubte linea : dao.traerLineas()) {
			if (linea.getNombre().equalsIgnoreCase(nombre)) throw new Exception("La linea de nombre " + nombre + " ya existe");
		}
		LineaSubte l = new LineaSubte(nombre, precio);
		return dao.agregarLinea(l);
	}
	
	public void modificarLinea(LineaSubte l) throws Exception {
		for (LineaSubte linea : dao.traerLineas()) {
			if (linea.equals(l)) throw new Exception("La linea " + l + " ya existe");
		}
		dao.modificarLinea(l);
	}
	
	public void eliminarLineaPorId(int idLinea) throws Exception {
		LineaSubte l = dao.traerLinea(idLinea);
		if (l == null) throw new Exception("La linea de id " + idLinea + " no existe");
		dao.eliminarLinea(l);
	}
	
	public List<LineaSubte> traerLineas(){
		return dao.traerLineas();
	}
	
	public List<EstacionSubte> traerEstacionesPorIdLinea(int idLinea) {
		return daoEstacion.traerEstacionesPorIdLinea(idLinea);
	}
	
	public List<LectoraSubte> traerLectorasPorIdEstacion(int idEstacion) {
		return daoLectora.traerLectorasPorIdEstacion(idEstacion);
	}
}
