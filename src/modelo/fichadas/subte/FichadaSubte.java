package modelo.fichadas.subte;

import java.math.BigDecimal;
import java.util.GregorianCalendar;

import modelo.fichadas.Fichada;
import modelo.lectoras.LectoraSubte;
import util.FuncionesGregorian;

public class FichadaSubte extends Fichada {
	
	private EstacionSubte estacionSubte;
	
	public FichadaSubte() {}
	
	public FichadaSubte(GregorianCalendar fechaHora, LectoraSubte lectora, EstacionSubte estacionSubte) {
		super(fechaHora, lectora);
		this.setEstacionSubte(estacionSubte);
	}
	
	public EstacionSubte getEstacionSubte() {
		return estacionSubte;
	}

	public void setEstacionSubte(EstacionSubte estacionSubte) {
		this.estacionSubte = estacionSubte;
	}
	
	public BigDecimal obtenerPrecio() {
		return this.estacionSubte.getLineaSubte().getPrecioViaje();
	}
	
	@Override
	public boolean esViaje() {
		return true;
	}

	@Override
	public boolean esRecarga() {
		return false;
	}
	
	@Override
	public String toString() {
		return "Estacion " + estacionSubte + ", a las: " + FuncionesGregorian.traerFechaCortaHora(getFechaHora()); 
	} 
	
}
