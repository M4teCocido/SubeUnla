package modelo.fichadas.tren;
import java.util.HashSet;
import java.util.Set;

import modelo.fichadas.tren.LineaTren;
import modelo.lectoras.LectoraTren;

public class EstacionTren {
	
	private int idEstacion;
	private String nombre;
	private LineaTren linea;
	private Set<LectoraTren> lectoras;

	public EstacionTren() {}
	
	public EstacionTren(String nombre, LineaTren linea) {
		super();
		this.nombre = nombre;
		this.linea = linea;
		this.lectoras = new HashSet<LectoraTren>();
	}

	public int getIdEstacion() {
		return this.idEstacion;
	}
	
	protected void setIdEstacion(int idEstacion) {
		this.idEstacion = idEstacion;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public LineaTren getLinea() {
		return linea;
	}
	
	public void setLinea(LineaTren linea) {
		this.linea = linea;
	}

	public Set<LectoraTren> getLectoras() {
		return lectoras;
	}

	public void setLectoras(Set<LectoraTren> lectoras) {
		this.lectoras = lectoras;
	}
	
	@Override
	public String toString() {
		return  nombre;
	}
	
}

