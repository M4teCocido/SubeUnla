package modelo.fichadas.colectivo;

import java.math.BigDecimal;

public class TramoColectivo {

	private int idTramo;
	private String nombre;
	private BigDecimal precio;
	private LineaColectivo lineaColectivo;

	public TramoColectivo() {
	
	}
	
	public TramoColectivo(String nombre, BigDecimal precio, LineaColectivo lineaColectivo) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.lineaColectivo = lineaColectivo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public LineaColectivo getLineaColectivo() {
		return lineaColectivo;
	}

	public void setLineaColectivo(LineaColectivo lineaColectivo) {
		this.lineaColectivo = lineaColectivo;
	}

	public int getIdTramo() {
		return idTramo;
	}

	public void setIdTramo(int idTramo) {
		this.idTramo = idTramo;
	}

	@Override
	public String toString() {
		return "TramoColectivo [idTramo=" + idTramo + ", nombre=" + nombre + ", precio=" + precio + ", lineaColectivo="
				+ lineaColectivo + "]";
	}

	
	
}