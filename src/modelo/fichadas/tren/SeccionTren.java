package modelo.fichadas.tren;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import util.IndexableSet;

public class SeccionTren {
	
	private int idSeccion;
	private String nombre;
	private BigDecimal importe;
	private LineaTren linea;
	private Set<ViajeTren> viajesTren;

	public SeccionTren() {}
	
	public SeccionTren(String nombre, BigDecimal importe, LineaTren linea) {
		super();
		this.nombre = nombre;
		this.importe = importe;
		this.linea = linea;
		this.viajesTren = new HashSet<ViajeTren>();

	}

	public int getIdSeccion() {
		return this.idSeccion;
	}
	
	protected void setIdSeccion(int idSeccion) {
		this.idSeccion = idSeccion;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public BigDecimal getImporte() {
		return importe;
	}
	
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	
	public LineaTren getLinea() {
		return this.linea;
	}

	public void setLinea(LineaTren linea) {
		this.linea = linea;
	}

	public Set<ViajeTren> getViajesTren() {
		return viajesTren;
	}

	public void setViajesTren(Set<ViajeTren> viajesTren) {
		this.viajesTren = viajesTren;
	}

	@Override
	public String toString() {
		return "SeccionTren [idSeccion=" + idSeccion + ", nombre=" + nombre + ", importe=" + importe + ", linea="
				+ linea + ", viajesTren=" + viajesTren + "]";
	}

	
}