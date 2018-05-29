package modelo.fichadas.colectivo;

import modelo.fichadas.colectivo.LineaColectivo;
import modelo.lectoras.LectoraColectivo;

public class InternoColectivo {
	
	private int idInterno;
	private LineaColectivo lineaColectivo ;
	private String nombre;
	private LectoraColectivo lectora;
	
	public InternoColectivo() {}
	
	public InternoColectivo(LineaColectivo lineaColectivo, String nombre) {
		super();
		this.lineaColectivo = lineaColectivo;
		this.nombre = nombre;
	}
	
	public int getIdInterno() {
		return idInterno;
	}

	protected void setIdInterno(int idInterno) {
		this.idInterno = idInterno;
	}
	
	public LineaColectivo getLineaColectivo() {
		return lineaColectivo;
	}
	
	public void setLineaColectivo(LineaColectivo lineaColectivo) {
		this.lineaColectivo = lineaColectivo;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	
	@Override
	public String toString() {
		String lectoraString;
		if (this.lectora == null)
			lectoraString = "NINGUNA";
		else
			lectoraString = this.lectora.toString();
		String resultado = "InternoColectivo [idInterno=" + idInterno + ", lineaColectivo=" + lineaColectivo + ", nombre=" + nombre + ", lectora=" + lectoraString + "]"; 
		return resultado; 
				
	}

	public LectoraColectivo getLectora() {
		return lectora;
	}

	public void setLectora(LectoraColectivo lectora) {
		this.lectora = lectora;
	}
}
