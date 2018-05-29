package modelo.fichadas.tren;
import java.util.GregorianCalendar;

import modelo.fichadas.Fichada;
import modelo.fichadas.tren.EstacionTren;
import modelo.fichadas.tren.eTipoFichadaTren;
import modelo.lectoras.LectoraTren;

public  class FichadaTren extends Fichada {
	
	private EstacionTren estacion;
	private eTipoFichadaTren tipoFichada;
	
	public FichadaTren() {}
	
	public FichadaTren(GregorianCalendar fechaHora, EstacionTren estacion, eTipoFichadaTren tipoFichada, LectoraTren lectora) {
		super(fechaHora, lectora);
		this.estacion = estacion;
		this.tipoFichada = tipoFichada;
	}

	public eTipoFichadaTren getTipoFichada() {
		return tipoFichada;
	}

	public void setTipoFichada(eTipoFichadaTren tipoFichada) {
		this.tipoFichada = tipoFichada;
	}

	public EstacionTren getEstacion() {
		return estacion;
	}
	
	public void setEstacion(EstacionTren estacion) {
		this.estacion = estacion;
	}

	public boolean esSalida() {
		return (this.tipoFichada == eTipoFichadaTren.SALIDA);
	}
	
	public boolean esEntrada() {
		return !(this.esSalida());
	}
	
	@Override
	public String toString() {
		return "FichadaTren [estacion=" + estacion + ", tipoFichada=" + tipoFichada + "]";
	}
	
}
