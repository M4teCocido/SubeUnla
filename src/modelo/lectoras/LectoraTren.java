package modelo.lectoras;

import modelo.fichadas.tren.EstacionTren;

public class LectoraTren extends Lectora{

	private EstacionTren estacion;
	private boolean esEntrada;
	
	public LectoraTren() {}
	
	public LectoraTren(int nroSerie, EstacionTren estacion, boolean esEntrada) {
		super(nroSerie);
		this.estacion = estacion;
		this.esEntrada = esEntrada;
	}
	
	public EstacionTren getEstacion() {
		return estacion;
	}
	
	public void setEstacion(EstacionTren estacion) {
		this.estacion = estacion;
	}

	public boolean isEsEntrada() {
		return esEntrada;
	}

	public void setEsEntrada(boolean esEntrada) {
		this.esEntrada = esEntrada;
	}

	@Override
	public String toString() {
		return "LectoraTren [estacion=" + estacion + ", esEntrada=" + esEntrada + "]";
	}
	
	
}
