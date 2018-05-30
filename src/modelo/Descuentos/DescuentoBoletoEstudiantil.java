package modelo.Descuentos;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;

import modelo.Persona;
import modelo.Descuentos.eTipoBoletoEstudiantil;
import modelo.fichadas.colectivo.FichadaColectivo;

import modelo.fichadas.Fichada;

public class DescuentoBoletoEstudiantil extends DescuentoSube {
	
	// 5.00h y las 23.00h.s
	
	private static int horaInicio = 5;
	private static int horaFin = 23;
	
	private BigDecimal porcentajeDescuento;
	private int viajesRestantes;
	private eTipoBoletoEstudiantil tipoBoletoEstudiantil;
	private Persona persona;
	
	//Los viajes no los recargamos : Le asignamos un nuevo descuento y listo.
	
	public DescuentoBoletoEstudiantil() {}
	
	public DescuentoBoletoEstudiantil(eTipoBoletoEstudiantil tipoBoletoEstudiantil, Persona persona) {
		super("Boleto Estudiantil");
		if (tipoBoletoEstudiantil == eTipoBoletoEstudiantil.ESCOLAR) {
			this.porcentajeDescuento = new BigDecimal(0);
			this.viajesRestantes = 50;	
		} else {
			this.porcentajeDescuento = new BigDecimal(0);
			this.viajesRestantes = 45;	
			}
			this.persona = persona;
			this.tipoBoletoEstudiantil = tipoBoletoEstudiantil;
		}

	public boolean leQuedanCargas() {
		return (this.viajesRestantes > 0);
	}
	
	private void consumirViaje() {
		this.viajesRestantes--;
	}
	
	@Override
	public BigDecimal aplicarDescuento (BigDecimal importe, Fichada fichada) {
		BigDecimal importeFinal = new BigDecimal(importe.doubleValue());
		if (fichada instanceof FichadaColectivo ) { //Es de Colectivo
			if (this.viajesRestantes > 0) {
				if (fichada.getFechaHora().get(Calendar.HOUR_OF_DAY) >= horaInicio && fichada.getFechaHora().get(Calendar.HOUR_OF_DAY) <= horaFin) {
					this.consumirViaje();
					importeFinal = importeFinal.multiply(this.porcentajeDescuento);
				}
			}
		}
		return importeFinal;
	}
	
	public BigDecimal getPorcentajeDescuento() {
		return porcentajeDescuento;
	}

	public void setPorcentajeDescuento(BigDecimal porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
	}

	public int getViajesRestantes() {
		return viajesRestantes;
	}

	public void setViajesRestantes(int viajesRestantes) {
		this.viajesRestantes = viajesRestantes;
	}
	
	public eTipoBoletoEstudiantil getTipoBoletoEstudiantil() {
		return tipoBoletoEstudiantil;
	}

	public void setTipoBoletoEstudiantil(eTipoBoletoEstudiantil tipoBoletoEstudiantil) {
		this.tipoBoletoEstudiantil = tipoBoletoEstudiantil;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	
}
