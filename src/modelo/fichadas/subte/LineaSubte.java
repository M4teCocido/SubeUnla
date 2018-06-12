package modelo.fichadas.subte;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class LineaSubte {
	
	private int idLinea;
	private String nombre;
	private Set<EstacionSubte> recorridoSubte;
	private BigDecimal precioViaje;
	
	public LineaSubte() {}
	
	public LineaSubte(String nombre, BigDecimal precioViaje) {
		super();
		this.nombre = nombre;
		this.precioViaje = precioViaje;
		this.recorridoSubte = new HashSet<EstacionSubte>();
	}
	
	public int getIdLinea() {
		return this.idLinea;
	}
	
	protected void setIdLinea(int idLinea) {
		this.idLinea = idLinea;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Set<EstacionSubte> getRecorridoSubte() {
		return recorridoSubte;
	}
	
	public void setRecorridoSubte(Set<EstacionSubte> recorridoSubte) {
		this.recorridoSubte = recorridoSubte;
	}
	
	public BigDecimal getPrecioViaje() {
		return precioViaje;
	}
	
	public void setPrecioViaje(BigDecimal precioViaje) {
		this.precioViaje = precioViaje;
	}

	@Override
	public String toString() {
		return getNombre();
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
		LineaSubte other = (LineaSubte) obj;
		if (idLinea != other.idLinea)
			return false;
		return true;
	}
	
	

}
