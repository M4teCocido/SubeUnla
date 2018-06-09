package modelo.fichadas.tren;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import modelo.fichadas.tren.ViajeTren;
import util.IndexableSet;
import modelo.fichadas.tren.SeccionTren;
import modelo.fichadas.tren.EstacionTren;

public class LineaTren {

	private int idLinea;
	private String nombre;

	private Set<SeccionTren> secciones;
	private Set<EstacionTren> estaciones;
	private Set<ViajeTren> viajes;

	public LineaTren() {}
	
	public LineaTren(String nombre) {
		super();
		this.nombre = nombre;
		this.secciones = new HashSet<SeccionTren>();
		this.estaciones = new HashSet<EstacionTren>();
		this.viajes = new HashSet<ViajeTren>();
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

	public Set<SeccionTren> getSecciones() {
		return secciones;
	}

	public void setSecciones(Set<SeccionTren> secciones) {
		this.secciones = secciones;
	}

	public Set<EstacionTren> getEstaciones() {
		return estaciones;
	}

	public void setEstaciones(Set<EstacionTren> estaciones) {
		this.estaciones = estaciones;
	}

	public Set<ViajeTren> getViajes() {
		return viajes;
	}

	public void setViajes(Set<ViajeTren> viajes) {
		this.viajes = viajes;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<SeccionTren> getSeccionesTren() {
		return secciones;
	}

	public void setSeccionesTren(Set<SeccionTren> seccionesTren) {
		this.secciones = seccionesTren;
	}

	public LineaTren agregarSeccion(SeccionTren seccion) {
		this.secciones.add(seccion);
		return this;
	}
	
	public BigDecimal obtenerMayorSeccion() {
		BigDecimal montoMayor = new BigDecimal (0);
		
		for (int i = 0; i < this.secciones.size(); i++) {		
			if (montoMayor.compareTo(IndexableSet.get(this.secciones,i).getImporte())==-1){
				montoMayor = IndexableSet.get(this.secciones,i).getImporte(); 			
			}
		}
		return montoMayor;
	}
	
	public ViajeTren obtenerViaje (EstacionTren estacionOrigen, EstacionTren estacionDestino) {
		ViajeTren viajeTren = null;
		for(int i = 0; i < this.viajes.size(); i++) {
			if(IndexableSet.get(this.viajes, i).getEstacionOrigen().getIdEstacion() == estacionOrigen.getIdEstacion() 
					&& IndexableSet.get(this.viajes, i).getEstacionDestino().getIdEstacion() == estacionDestino.getIdEstacion()) {
		        viajeTren = IndexableSet.get(this.viajes, i); 
		    }
		}
		return viajeTren;
	}

	@Override
	public String toString() {
		return "LineaTren [idLinea=" + idLinea + ", nombre=" + nombre + ", secciones=" + secciones + ", estaciones="
				+ estaciones + ", viajes=" + viajes + "]";
	}
	
	
} 
