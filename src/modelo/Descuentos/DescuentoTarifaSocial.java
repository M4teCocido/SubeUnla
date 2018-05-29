package modelo.Descuentos;
import java.math.BigDecimal;
import java.math.RoundingMode;

import modelo.Persona;
import modelo.Descuentos.DescuentoSube;
import modelo.fichadas.Fichada;
import modelo.fichadas.subte.FichadaSubte;
import modelo.fichadas.tren.FichadaTren;

public class DescuentoTarifaSocial extends DescuentoSube{
	
	private BigDecimal porcentajeDescuento = new BigDecimal(0.45).setScale(4, RoundingMode.HALF_UP);
	private Persona persona;
	
	public DescuentoTarifaSocial() {}
	
	public DescuentoTarifaSocial(Persona persona) {
		super("Tarifa Social");
		this.persona = persona;
		//this.porcentajeDescuento = porcentajeDescuento;
	}
	
	public BigDecimal getPorcentajeDescuento() {
		return porcentajeDescuento;
	}
	
	public void setPorcentajeDescuento(BigDecimal porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
	}
	
	private boolean esFichadaDescontable(Fichada fichada) {
		return !(fichada instanceof FichadaSubte);
	}
	
	@Override
	public BigDecimal aplicarDescuento (BigDecimal importe, Fichada fichada) {//Toma el importe de la fichada y devuelve el importe aplicado el descuento
		if (this.esFichadaDescontable(fichada))
			return importe.multiply(this.porcentajeDescuento);
		else
			return importe;
	}
	
	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	@Override
	public String toString() {
		return "DescuentoTarifaSocial [porcentajeDescuento=" + porcentajeDescuento.toString() + "]";
	}
	
}
