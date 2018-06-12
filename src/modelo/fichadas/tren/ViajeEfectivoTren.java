package modelo.fichadas.tren;

import java.math.BigDecimal;
import java.util.GregorianCalendar;

import modelo.TarjetaSube;
import modelo.fichadas.TransaccionSUBE;

public class ViajeEfectivoTren {
	
	private TarjetaSube tarjeta;
	private TransaccionSUBE entrada;
	private TransaccionSUBE salida;
	
	public ViajeEfectivoTren(TarjetaSube tarjeta, TransaccionSUBE entrada, TransaccionSUBE salida) {
		super();
		this.tarjeta = tarjeta;
		this.entrada = entrada;
		this.salida = salida;
	}
	
	public TarjetaSube getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(TarjetaSube tarjeta) {
		this.tarjeta = tarjeta;
	}

	public TransaccionSUBE getEntrada() {
		return entrada;
	}
	public void setEntrada(TransaccionSUBE entrada) {
		this.entrada = entrada;
	}

	public TransaccionSUBE getSalida() {
		return salida;
	}

	public void setSalida(TransaccionSUBE salida) {
		this.salida = salida;
	}
	
	public BigDecimal getImporte() {
		
		if (this.entrada != null && this.salida != null) {
			return this.entrada.getImporte().add(this.salida.getImporte());
		} else if (this.entrada != null) {
			return this.entrada.getImporte();
		} else {
			return this.salida.getImporte();
		}
	}
	
	public GregorianCalendar getFechaHora() {
		if (this.salida != null) {
			return this.salida.getFichada().getFechaHora();
		} else {
			return this.entrada.getFichada().getFechaHora();
		}
	}
	
	public LineaTren getLinea() {
		if (this.salida != null) {
			return ((FichadaTren) this.salida.getFichada()).getEstacion().getLinea();
		} else {
			return ((FichadaTren) this.entrada.getFichada()).getEstacion().getLinea();
		}
	}
	
}
