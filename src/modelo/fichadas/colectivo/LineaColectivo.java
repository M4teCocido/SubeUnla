package modelo.fichadas.colectivo;

import java.util.Set;

import util.INombrable;

import java.util.HashSet;

public class LineaColectivo implements INombrable {
	
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idLinea;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LineaColectivo other = (LineaColectivo) obj;
		if (idLinea != other.idLinea)
			return false;
		return true;
	}
	
	
	
}
