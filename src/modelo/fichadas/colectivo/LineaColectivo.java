package modelo.fichadas.colectivo;

import java.util.Set;
import java.util.HashSet;

public class LineaColectivo {
	
	private int idLinea;
	private String nombre;
	private Set<TramoColectivo> tramosColectivo;
	private Set<InternoColectivo> internosColectivo;

	public LineaColectivo() {}
	
	public LineaColectivo(String nombre) {
		super();
		this.nombre = nombre;

		this.internosColectivo = new HashSet<InternoColectivo>();
		this.tramosColectivo = new HashSet<TramoColectivo>();

	}

	public int getIdLinea() {
		return this.idLinea;
	}
	
	protected void setIdLinea(int idLinea) {
		this.idLinea = idLinea;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	

	public Set<TramoColectivo> getTramosColectivo() {
		return tramosColectivo;
	}

	public void setTramosColectivo(Set<TramoColectivo> tramosColectivo) {
		this.tramosColectivo = tramosColectivo;
	}

	public Set<InternoColectivo> getInternosColectivo() {
		return internosColectivo;
	}

	public void setInternosColectivo(Set<InternoColectivo> internosColectivo) {
		this.internosColectivo = internosColectivo;
	}

	@Override
	public String toString() {
		return "LineaColectivo [Nombre=" + this.nombre + "]";
	}
}
