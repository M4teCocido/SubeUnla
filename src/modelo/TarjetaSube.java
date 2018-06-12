package modelo;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import modelo.Descuentos.*;

import modelo.Persona;
import modelo.fichadas.*;
import modelo.fichadas.colectivo.FichadaColectivo;
import modelo.fichadas.subte.FichadaSubte;
import modelo.fichadas.tren.FichadaTren;
import modelo.fichadas.tren.ViajeEfectivoTren;
import modelo.fichadas.tren.eTipoFichadaTren;
import modelo.fichadas.tren.ViajeTren;
import util.FuncionesGregorian;
import util.IndexableSet;

public class TarjetaSube {
	
	private int idTarjeta;
	private String codigo;
	private Persona propietario;
	private Set<TransaccionSUBE> transacciones;
	private DescuentoRedSube descuentoRedSube;
	private BigDecimal saldo;
	private boolean activa;
	private final int saldoMinimo = -19;
	private final int saldoMaximo = 600;
	
	public TarjetaSube() {}
	
	public TarjetaSube(String codigo, int saldo) {
		this(codigo, new BigDecimal(saldo));
	}
	
	public TarjetaSube(String codigo, BigDecimal saldo) {
		super();
		this.codigo = codigo;
		this.saldo = saldo;
		this.transacciones = new LinkedHashSet<TransaccionSUBE>();
		this.descuentoRedSube = new DescuentoRedSube(this);
		this.activa = true;
	}
	
	protected void setIdTarjeta(int idTarjeta) {
		this.idTarjeta = idTarjeta;
	}
	
	public int getIdTarjeta() {
		return this.idTarjeta;
	}
	
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public Persona getPropietario() {
		return this.propietario;
	}

	public void setPropietario(Persona propietario) {
		this.propietario = propietario;
	}

	public DescuentoRedSube getDescuentoRedSube() {
		return descuentoRedSube;
	}

	public void setDescuentoRedSube(DescuentoRedSube descuentoRedSube) {
		this.descuentoRedSube = descuentoRedSube;
	}
	
	public Set<TransaccionSUBE> getTransacciones() {
		return transacciones;
	}

	public void setTransacciones(Set<TransaccionSUBE> transacciones) {
		this.transacciones = transacciones;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	
	public boolean isActiva() {
		return activa;
	}
	
	public void setActiva(boolean activa) {
		this.activa = activa;
	}
	
	public TarjetaSube SetActivaC(boolean activa) {
		this.activa = activa;
		return this;
	}	

	public List<ViajeEfectivoTren> obtenerViajesTren(GregorianCalendar desde, GregorianCalendar hasta){
		List<TransaccionSUBE> viajesFiltrados = new ArrayList<TransaccionSUBE>();
		for (TransaccionSUBE t : this.transacciones) {
			if (FuncionesGregorian.estaEntreFechas(t.getFichada().getFechaHora(), desde, hasta)) {
				viajesFiltrados.add(t);
			}
		}
		List<ViajeEfectivoTren> viajes = obtenerViajesTrenDesdeTransacciones(this, viajesFiltrados);
		
		return viajes;
	}
	
	public static List<ViajeEfectivoTren> obtenerViajesTrenDesdeTransacciones(TarjetaSube tarjeta, Iterable<TransaccionSUBE> transacciones){
		TransaccionSUBE txAnterior = null;
		FichadaTren f;
		List<ViajeEfectivoTren> viajes = new ArrayList<ViajeEfectivoTren>();
		for (TransaccionSUBE t : transacciones) {
			if (t.getFichada() instanceof FichadaTren) { //Vemos que sea de Tren
				f = ((FichadaTren) t.getFichada());
				if (f.esEntrada()) { //Actual es de entrada
					if (txAnterior != null) { //Implica que es de entrada, jamas ponemos una de salida como anterior.
							viajes.add(new ViajeEfectivoTren(tarjeta,
									txAnterior, 
									null));
					}
					txAnterior = t;
				} else { //Actual es de salida
					if (txAnterior != null) { //Implica que es de entrada, jamas ponemos una de salida como anterior. 
						viajes.add(new ViajeEfectivoTren(tarjeta,
								txAnterior, 
								t));
						txAnterior = null;
					} else {
						viajes.add(new ViajeEfectivoTren(tarjeta,
								null, 
								t));
					}
				}
			} else {
				if (txAnterior != null) { //Es de entrada
					viajes.add(new ViajeEfectivoTren(tarjeta,
							txAnterior, 
							null));
				}
			}
		}
		return viajes;
	}
	
	public Resultado procesarFichada(FichadaColectivo fichadaColectivo) {
		BigDecimal monto = procesarDescuento(fichadaColectivo.obtenerPrecio(), fichadaColectivo).setScale(2, RoundingMode.HALF_UP);
		
		TransaccionSUBE transaccion = null; 
		Resultado resultado = null;
		
		if (comprobarSaldoSuficiente(monto) == true ) {
			transaccion = this.procesarTransaccion(fichadaColectivo, monto); 
			this.transacciones.add(transaccion);
			resultado = generarResultadoTransaccionExitosa(" -$" + transaccion.getImporte().toString(), transaccion );
		} else {
			resultado = generarResultadoSaldoInsuficiente(null, monto);
		}
		
		return resultado;
	}
	
	private Resultado generarResultadoTransaccionExitosa(String extraText, TransaccionSUBE tx) {
		return new Resultado(true, "Transaccion Exitosa! : " + extraText + " - Saldo actual : " + this.saldo, tx);
	}
	
	private Resultado generarResultadoSaldoInsuficiente(TransaccionSUBE tx, BigDecimal monto) {
		return new Resultado (false, "Saldo Insuficiente - Coste Transaccion : $" + monto +  " - Saldo Actual : $" + this.saldo, tx);
	}
	
	public Resultado procesarFichada(FichadaTren fichadaActual) { //Se procesa fichada tren. -----------
		
		TransaccionSUBE transaccion = null;
		Resultado resultado = null;
		
		if (fichadaActual.getTipoFichada().equals(eTipoFichadaTren.ENTRADA)) {
			System.out.println("Fichada Tren : Actual es de ENTRADA");
			
			resultado = procesarImporteMaximoTren (fichadaActual );
			
		} else {		
			
			Fichada ultimaFichada = this.getUltimaFichada();
			System.out.println(ultimaFichada);
			System.out.println("Fichada Tren : Actual es de SALIDA");
			
			if (ultimaFichada != null) {
				if (ultimaFichada instanceof FichadaTren) {
					FichadaTren fichadaAnterior =  (FichadaTren) getUltimaFichada();
					System.out.println("Fichada Tren : Anterior es de tren");
					
					if (fichadaAnterior.getTipoFichada().equals(eTipoFichadaTren.ENTRADA)){
						System.out.println("Fichada Tren : Anterior es de ENTRADA");
						
						GregorianCalendar fechaMenos2 = (GregorianCalendar) fichadaActual.getFechaHora().clone();
						fechaMenos2.add(Calendar.HOUR, -2);
						System.out.println("FechaMenos2 : " + fechaMenos2);
						System.out.println("HoraAnterior : " + fichadaAnterior.getFechaHora());
						
						if (fichadaAnterior.getFechaHora().after(fechaMenos2)) {
						//if((fichadaActual.getFechaHora().get(GregorianCalendar.HOUR_OF_DAY) - fichadaAnterior.getFechaHora().get(GregorianCalendar.HOUR_OF_DAY)) < 2){
							System.out.println("Fichada Tren : ES menor a 2 horas");
							
							ViajeTren viajeAux = fichadaActual.getEstacion().getLinea().obtenerViaje(fichadaAnterior.getEstacion(), fichadaActual.getEstacion());
						    BigDecimal bonificacion = new BigDecimal(0);
						    BigDecimal viajeDescontado;
						    
						    
						    System.out.println("Estacion Anterior : " + fichadaAnterior.getEstacion());
						    System.out.println("Estacion Actual : " + fichadaActual.getEstacion());
						    System.out.println("Linea : " + fichadaActual.getEstacion().getLinea());
						    System.out.println("Viajes : " + fichadaActual.getEstacion().getLinea().getViajes());
						    
						    if (viajeAux != null) {
						    	viajeDescontado = procesarDescuento(viajeAux.getSeccionTren().getImporte(), fichadaActual).setScale(2, RoundingMode.HALF_UP);
						    	System.out.println("viaje descontado : " + viajeDescontado.toString());
						    	System.out.println("Importe Viaje : " + viajeAux.getSeccionTren().getImporte().toString());
						    } else {
						    	viajeDescontado = getUltimaTransaccion().getImporte().add(BigDecimal.ZERO);
						    	System.out.println("viaje es NULO!");
						    }
						    
					     	bonificacion = getUltimaTransaccion().getImporte().subtract(viajeDescontado).setScale(2, RoundingMode.HALF_UP);
				
				    		
					     	transaccion = procesarTransaccion (fichadaActual, bonificacion.negate());
	
				    		resultado = generarResultadoTransaccionExitosa(" +$" + bonificacion.toString(), transaccion );
				    		
						    System.out.println("Bonificacion : " + bonificacion.toString());
					    	this.transacciones.add(transaccion);
						
						
						} else {
							System.out.println("Fichada Tren : NO Es menor a 2 horas");
	
							transaccion = new  TransaccionSUBE ( new BigDecimal (0),this, fichadaActual );
							this.transacciones.add(transaccion);
							resultado =  generarResultadoTransaccionExitosa("+ $0.00", transaccion );
						} 	
					} else {	
						System.out.println("Fichada Tren : Anterior NO es de entrada");
						resultado = procesarImporteMaximoTren (fichadaActual);
					}
				
				} else {	
					System.out.println("Fichada Tren : Anterior NO es de tren");
					resultado = procesarImporteMaximoTren (fichadaActual);
				}
			} else {
				System.out.println("Fichada Tren : No hay fichada anterior");
				resultado = procesarImporteMaximoTren (fichadaActual);
			}
		}
		return  resultado;
	}
	
	
	
	
	
	
	
	public Resultado procesarFichada (FichadaSubte fichadaSubte) { //procesa fichada subte------------
		BigDecimal monto = procesarDescuento (fichadaSubte.obtenerPrecio(), fichadaSubte);
		Resultado resultado = null;
		TransaccionSUBE transaccion = null; 	
		
		
		if (comprobarSaldoSuficiente(monto) == true ) {
			transaccion = this.procesarTransaccion(fichadaSubte, monto); 
			//System.out.println(transaccion.getImporte().toString());
			this.transacciones.add(transaccion);
			resultado = this.generarResultadoTransaccionExitosa(" -$" + monto , transaccion);
		} else {
			resultado = this.generarResultadoSaldoInsuficiente(null, monto);
		}
		
		
		return  resultado ;
	}
	
	public Resultado procesarFichada (FichadaRecarga fichadaCarga) {
		
		TransaccionSUBE transaccion = null;
		Resultado resultado = null;
		BigDecimal saldoDespuesCarga = this.saldo.add(fichadaCarga.getMonto());
		
		if (saldoDespuesCarga.compareTo(new BigDecimal (saldoMinimo))==1) {
			if (saldoDespuesCarga.compareTo(new BigDecimal (saldoMaximo))!=1) {
					this.saldo = saldoDespuesCarga;
					transaccion =  new TransaccionSUBE ( fichadaCarga.getMonto().negate(),this, fichadaCarga );
					this.transacciones.add(transaccion);
					resultado = generarResultadoTransaccionExitosa ("Carga Exitosa : +$" + fichadaCarga.getMonto(), transaccion );
			} else {resultado = new Resultado (false, "Carga supera maximo", transaccion);}
		}else { resultado =  new Resultado (false, "Carga no supera minimo", transaccion );}
	
		
		return resultado;
	}

	public List<Fichada> obtenerViajesRealizados(GregorianCalendar fechaInicio , GregorianCalendar fechaFin ) {
		List<Fichada> fichadas = new ArrayList<Fichada>();
	
		return fichadas;
	}
	
	public List<TransaccionSUBE> obtenerViajesRealizadosColectivo (GregorianCalendar fechaInicio, GregorianCalendar fechaFinal){
		List<TransaccionSUBE>transacciones = new ArrayList<TransaccionSUBE>();
	
		return transacciones;
	}

	public List<TransaccionSUBE> obtenerViajesRealizadosTren (GregorianCalendar fechaInicio, GregorianCalendar fechaFinal){
		List<TransaccionSUBE> transacciones  = new ArrayList <TransaccionSUBE>();
		return transacciones;
	}
	
	public List<TransaccionSUBE> obtenerViajesRealizadosSubte (GregorianCalendar fechaInicio, GregorianCalendar fechaFinal){
		List<TransaccionSUBE>transacciones = new ArrayList<TransaccionSUBE>();
		return transacciones;
	}


	public BigDecimal procesarDescuento (BigDecimal monto, Fichada fichada) {//Interface para todo proceso de descuento---------
		BigDecimal montoFinal = monto.add(BigDecimal.ZERO).setScale(2, RoundingMode.HALF_UP);
		System.out.println("Monto Inicial : " + montoFinal);
		if (this.propietario != null) {
			//Aplica descuentos
			if (this.propietario.getDescuentoBoletoEstudiantil() != null && this.propietario.getDescuentoBoletoEstudiantil().leQuedanCargas()) { //Como es del 100%, si existe ignoramos los otros.
				montoFinal=this.propietario.getDescuentoBoletoEstudiantil().aplicarDescuento(montoFinal, fichada);
			}
			if (this.propietario.getDescuentoTarifaSocial() != null) {
				montoFinal=this.propietario.getDescuentoTarifaSocial().aplicarDescuento(montoFinal, fichada);
			}
			
		}
		
		if (this.descuentoRedSube != null) {
			montoFinal = this.descuentoRedSube.aplicarDescuento(montoFinal, fichada);
		}
		
		return montoFinal;
	}

	private TransaccionSUBE procesarTransaccion (Fichada fichada, BigDecimal monto) {
		//Descuenta saldo y crea  transaccion
		BigDecimal montoFinal = monto.add(BigDecimal.ZERO); //Creamos uno nuevo
		montoFinal = montoFinal.setScale(2, BigDecimal.ROUND_HALF_UP);
		this.saldo = this.saldo.subtract(montoFinal);
		return new TransaccionSUBE (montoFinal, this, fichada);
	}

	
	
	@Override
	public String toString() {
		String propietarioString = "NINGUNO";
		if (this.propietario != null)
			propietarioString = this.propietario.getNombre();
		return "TarjetaSube [idTarjeta=" + idTarjeta + ", codigo=" + codigo + ", propietario=" + propietarioString
				+ ", transacciones=" + transacciones + ", descuentoRedSube=" + descuentoRedSube + ", saldo=" + saldo
				+ ", activa=" + activa + "]";
	}

	public Resultado procesarImporteMaximoTren (FichadaTren fichadaTren) {
		TransaccionSUBE transaccion = null;
		BigDecimal monto = fichadaTren.getEstacion().getLinea().obtenerMayorSeccion();
		System.out.println("Monto maximo de sexion?"+ monto.toString());
		Resultado resultado = null;
		if(comprobarSaldoSuficiente (monto)){
			
			monto = procesarDescuento (monto, fichadaTren);
			
			transaccion = procesarTransaccion (fichadaTren, monto);
			
			this.transacciones.add(transaccion);
			resultado = generarResultadoTransaccionExitosa("-$" + transaccion.getImporte().toString(), transaccion );
			
		} else {
			resultado = generarResultadoSaldoInsuficiente(transaccion, monto);
		}
	
		
		return resultado;
	}
	
	public boolean comprobarSaldoSuficiente (BigDecimal monto ) {//Comprueba saldo suficiente ---------------------
		
		boolean aprobado = true;
		BigDecimal montoAux = new BigDecimal (0);
		
		montoAux = this.saldo.subtract(monto);
		
		if( montoAux.compareTo(new BigDecimal (saldoMinimo))==-1) {
			aprobado = false;
		}
		return aprobado;
	}

	private TransaccionSUBE getUltimaTransaccion() {// Obtiene la ultima transaccion dentro de lista de trasacciones----------
		if (this.transacciones.size() > 0)
			return IndexableSet.get(this.transacciones, this.transacciones.size() - 1);		
		else
			return null;
	}

	private Fichada getUltimaFichada() {// obtiene la  ultima  fichada dentro de la ultima transaccion---------
		TransaccionSUBE tx = this.getUltimaTransaccion();
		if (tx != null)
			return tx.getFichada();
		else
			return null;
	}
		
	public void asignarDescuento(DescuentoRedSube descuento) {
		
	}

	public static class Resultado{
		 private boolean aprobado;
		 private String mensaje;
		 private TransaccionSUBE transaccion;
		 
		 public Resultado(boolean aprobado, String mensaje, TransaccionSUBE transaccion) {
			super();
			this.aprobado = aprobado;
			this.mensaje = mensaje;
			this.transaccion = transaccion;
		 }
		 
		public Resultado() {
			super();
		}
		public boolean isAprobado() {
			return aprobado;
		}
		public void setAprobado(boolean aprobado) {
			this.aprobado = aprobado;
		}
		public String getMensaje() {
			return mensaje;
		}
		public void setMensaje(String mensaje) {
			this.mensaje = mensaje;
		}
		public TransaccionSUBE getTransaccion() {
			return this.transaccion;
		}
		public void setTransaccion(TransaccionSUBE transaccion) {
			this.transaccion = transaccion;
		}
		
		@Override
		public String toString() {
			return "Resultado [mensaje=" + mensaje + "]";
		}	 
	 
		
	
	}
}

