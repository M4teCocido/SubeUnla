package modelo.Descuentos;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dao.fichadas.FichadaDao;
import modelo.fichadas.Fichada;
import modelo.fichadas.tren.FichadaTren;
import util.IndexableSet;

public class LapsoDescuentoRedSube {
	
	private int idLapso;
	private DescuentoRedSube descuento;
	private static BigDecimal descuento50 = new BigDecimal(0.5);
	private static BigDecimal descuento75 = new BigDecimal(0.25);
	private GregorianCalendar fechaHoraVencimiento;
	private Set<Fichada> viajesRealizados;

	public LapsoDescuentoRedSube() {}
	
	public LapsoDescuentoRedSube(GregorianCalendar fechaHoraVencimiento, DescuentoRedSube descuento) {
		super();
		this.fechaHoraVencimiento = fechaHoraVencimiento;
		this.viajesRealizados = new HashSet<Fichada>();
		this.descuento = descuento;
	}

	public void reiniciar(GregorianCalendar fechaHoraFichada) {
		this.fechaHoraVencimiento = (GregorianCalendar) fechaHoraFichada.clone();
		this.fechaHoraVencimiento.add(Calendar.HOUR_OF_DAY, 2);
		for (Fichada f : this.viajesRealizados) {
			f.setLapso(null);
			FichadaDao daoFichada = new FichadaDao();
		}
		this.viajesRealizados.clear();
	}
	
	public boolean yaTermino(GregorianCalendar horaFichada) {
		System.out.println("horaFichada : " + horaFichada);
		System.out.println("horaVencimiento : " + this.fechaHoraVencimiento);
		return (this.getCantidadViajes() >= 5 || horaFichada.after(this.fechaHoraVencimiento));
	}
	
	public int getIdLapso() {
		return idLapso;
	}
	
	protected void setIdLapso(int idLapso) {
		this.idLapso = idLapso;
	}
	
	public GregorianCalendar getFechaHoraVencimiento() {
		return fechaHoraVencimiento;
	}

	public void setFechaHoraVencimiento(GregorianCalendar fechaHoraVencimiento) {
		this.fechaHoraVencimiento = fechaHoraVencimiento;
	}

	public Set<Fichada> getViajesRealizados() {
		return viajesRealizados;
	}

	public void setViajesRealizados(Set<Fichada> viajesRealizados) {
		this.viajesRealizados = viajesRealizados;
	}
	
	public int getCantidadViajes() {
		return this.viajesRealizados.size();
	}
	
	public DescuentoRedSube getDescuento() {
		return descuento;
	}

	public void setDescuento(DescuentoRedSube descuento) {
		this.descuento = descuento;
	}

	private boolean esFichadaDescontable(Fichada fichada) {
		return !(fichada instanceof FichadaTren && ((FichadaTren) fichada).esSalida());
	}
	
	public BigDecimal aplicarDescuento(BigDecimal importe, Fichada fichada) {
		
		BigDecimal importeFinal = new BigDecimal(importe.doubleValue());
		if (this.esFichadaDescontable(fichada))
			this.viajesRealizados.add(fichada);
		int cantidadViajes = this.getCantidadViajes();
		
		if (cantidadViajes == 2) //Segundo Viaje
			importeFinal = importe.multiply(descuento50);
		else if (cantidadViajes > 2)
			importeFinal = importe.multiply(descuento75);
		
		return importeFinal;
		
	}
}
