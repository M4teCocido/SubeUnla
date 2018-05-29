package modelo.fichadas;

import modelo.fichadas.Fichada;
import modelo.lectoras.Lectora;

import java.math.BigDecimal;
import java.util.GregorianCalendar;

public class FichadaRecarga extends Fichada {
	
	private BigDecimal monto;
	
	public FichadaRecarga() {}
	
	public FichadaRecarga(GregorianCalendar fechaHora, BigDecimal monto, Lectora lectora) {
		super(fechaHora, lectora);
		this.monto = monto;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}
}