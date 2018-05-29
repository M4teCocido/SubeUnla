package modelo.Descuentos;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;

import modelo.TarjetaSube;
import modelo.fichadas.Fichada;

public class DescuentoRedSube extends DescuentoSube {
	
	private LapsoDescuentoRedSube lapsoDescuentoRedSube;
	private TarjetaSube tarjeta;
	
	public DescuentoRedSube() {
		
	}
	
	public DescuentoRedSube(TarjetaSube tarjeta) {
		super("Desc. RED SUBE");
		//this.lapsoDescuentoRedSube = this.crearNuevoLapso();
		this.tarjeta = tarjeta;
	}

	private LapsoDescuentoRedSube crearNuevoLapso(GregorianCalendar fechaFichada) {
		GregorianCalendar post2Horas = (GregorianCalendar) fechaFichada.clone();
		post2Horas.add(Calendar.HOUR_OF_DAY, 2);
		return new LapsoDescuentoRedSube(post2Horas, this);
	}
	
	public LapsoDescuentoRedSube getLapsoDescuentoRedSube() {
		return lapsoDescuentoRedSube;
	}
	
	public void setLapsoDescuentoRedSube(LapsoDescuentoRedSube lapsoDescuentoRedSube) {
		this.lapsoDescuentoRedSube = lapsoDescuentoRedSube;
	}

	public DescuentoRedSube(int prioridad, String nombre, LapsoDescuentoRedSube lapsoDescuentoRedSube) {
		super(nombre);
		this.lapsoDescuentoRedSube = lapsoDescuentoRedSube;
	}
	
	public TarjetaSube getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(TarjetaSube tarjeta) {
		this.tarjeta = tarjeta;
	}

	
	
	@Override
	public String toString() {
		String lapsoString = "NINGUNO";
		String stringTarjeta = "NINGUNA";
		if (this.lapsoDescuentoRedSube != null)
			lapsoString = "[Lapso Viajes= " + this.lapsoDescuentoRedSube.getCantidadViajes() + "]";
		if (this.tarjeta != null)
			stringTarjeta = this.tarjeta.getCodigo();
		return "DescuentoRedSube [lapsoDescuentoRedSube=" + lapsoString + ", tarjeta=" + stringTarjeta + "]";
	}

	public BigDecimal aplicarDescuento (BigDecimal importe, Fichada fichada) {
		//Falta chequear el caso de fichada tren.
		if (this.lapsoDescuentoRedSube == null || this.lapsoDescuentoRedSube.yaTermino(fichada.getFechaHora())) {
			this.lapsoDescuentoRedSube = this.crearNuevoLapso(fichada.getFechaHora());
		}
		return this.lapsoDescuentoRedSube.aplicarDescuento(importe, fichada);
	}
		
}
