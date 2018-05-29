package modelo.fichadas.subte;

import java.util.HashSet;
import java.util.Set;
import modelo.fichadas.subte.LineaSubte;
import modelo.lectoras.LectoraSubte;

public class EstacionSubte {
	
	private int idEstacion;
	private String nombre;
	private LineaSubte lineaSubte;
	private Set<LectoraSubte> lectoras;
	
	public EstacionSubte() {}
	
	public EstacionSubte(String nombre, LineaSubte lineaSubte) {
		super();
		this.nombre = nombre;
		this.lineaSubte = lineaSubte;
		this.lectoras = new HashSet<LectoraSubte>();
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
	
	public LineaSubte getLineaSubte() {
		return lineaSubte;
	}
	
	public void setLineaSubte(LineaSubte lineaSubte) {
		this.lineaSubte = lineaSubte;
	}

	public Set<LectoraSubte> getLectoras() {
		return lectoras;
	}

	public void setLectoras(Set<LectoraSubte> lectoras) {
		this.lectoras = lectoras;
	}
	
	@Override
	public String toString() {
		return nombre + ", linea " + lineaSubte;
	}

}
