package modelo.fichadas.colectivo;

import java.math.BigDecimal;
import java.util.GregorianCalendar;
import modelo.fichadas.Fichada;
import modelo.fichadas.colectivo.TramoColectivo;
import modelo.lectoras.LectoraColectivo;

public class FichadaColectivo extends Fichada {
	
	private TramoColectivo tramo;

	public FichadaColectivo() {}

	public FichadaColectivo(GregorianCalendar fechaHora, TramoColectivo tramo, LectoraColectivo lectora) {
		super(fechaHora, lectora);
		this.tramo = tramo;
	}

	public TramoColectivo getTramo() {
		return tramo;
	}

	public void setTramo(TramoColectivo tramo) {
		this.tramo = tramo;
	}
	
	public BigDecimal obtenerPrecio() {
		return this.tramo.getPrecio();
	}

	@Override
	public String toString() {
		return "FichadaColectivo [tramo=" + tramo + "]";
	}
}
