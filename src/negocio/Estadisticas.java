package negocio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import modelo.TarjetaSube;
import modelo.fichadas.Fichada;
import modelo.fichadas.TransaccionSUBE;
import modelo.fichadas.colectivo.FichadaColectivo;
import modelo.fichadas.colectivo.LineaColectivo;
import modelo.fichadas.colectivo.TramoColectivo;
import modelo.fichadas.subte.FichadaSubte;
import modelo.fichadas.subte.LineaSubte;
import modelo.fichadas.tren.LineaTren;
import modelo.fichadas.tren.SeccionTren;
import modelo.fichadas.tren.ViajeEfectivoTren;
import util.FuncionesGregorian;
import util.INombrable;

public class Estadisticas {
	
	private TransaccionABM abmTx = new TransaccionABM(); 
	
	public Estadistica<LineaColectivo> generarEstadisticaLineasColectivo(GregorianCalendar desde, GregorianCalendar hasta){
		
		Estadistica <LineaColectivo> estadistica = new Estadistica <LineaColectivo>(); 
		System.out.println("");
		List<TransaccionSUBE> txs = abmTx.traerTransacciones();
		LineaColectivo linea;
		FichadaColectivo f;
		for (TransaccionSUBE tx : txs) {
			if (FuncionesGregorian.estaEntreFechas(tx.getFichada().getFechaHora(), desde, hasta)) {
				if (tx.getFichada() instanceof FichadaColectivo) {
					f = (FichadaColectivo) tx.getFichada();
					linea = f.getInterno().getLineaColectivo();
					estadistica.agregarNodo(new NodoEstadistica<LineaColectivo>(linea, tx.getImporte()));
				}
			}
		}
		
		return estadistica;
	}

	public Estadistica<LineaSubte> generarEstadisticaLineasSubte(GregorianCalendar desde, GregorianCalendar hasta){
		
		Estadistica <LineaSubte> estadistica = new Estadistica <LineaSubte>(); 
		
		List<TransaccionSUBE> txs = abmTx.traerTransacciones();
		LineaSubte linea;
		FichadaSubte f;
		for (TransaccionSUBE tx : txs) {
			if (FuncionesGregorian.estaEntreFechas(tx.getFichada().getFechaHora(), desde, hasta)) {
				if (tx.getFichada() instanceof FichadaSubte) {
					f = (FichadaSubte) tx.getFichada();
					linea = f.getEstacionSubte().getLineaSubte();
					estadistica.agregarNodo(new NodoEstadistica<LineaSubte>(linea, tx.getImporte()));
				}
			}
		}
		
		return estadistica;
	}
	
	public Estadistica<LineaTren> generarEstadisticaLineasTren(GregorianCalendar desde, GregorianCalendar hasta){
		
		TarjetaSubeABM tarjetaABM = new TarjetaSubeABM();
		List<TarjetaSube> tarjetas = tarjetaABM.traerTarjetas();
		List<ViajeEfectivoTren> viajes = new ArrayList<ViajeEfectivoTren>();
		
		for (TarjetaSube t : tarjetas) {
		    for (ViajeEfectivoTren v : t.obtenerViajesTren(desde, hasta)) {
		    	viajes.add(v);
		    }
		}   
		//Traemos todos los viajes.
		Estadistica <LineaTren> estadistica = new Estadistica <LineaTren>(); 		
		LineaTren linea;
		
		for (ViajeEfectivoTren viaje : viajes) {
			linea = viaje.getLinea();
			estadistica.agregarNodo(new NodoEstadistica<LineaTren>(linea, viaje.getImporte()));
		}
		
		return estadistica;
	}
	
	public Estadistica<SeccionTren> generarEstadisticaSeccionesTren(LineaTren linea, GregorianCalendar desde, GregorianCalendar hasta){
		
		TarjetaSubeABM tarjetaABM = new TarjetaSubeABM();
		List<TarjetaSube> tarjetas = tarjetaABM.traerTarjetas();
		List<ViajeEfectivoTren> viajes = new ArrayList<ViajeEfectivoTren>();
		
		for (TarjetaSube t : tarjetas) {
		    for (ViajeEfectivoTren v : t.obtenerViajesTren(desde, hasta)) {
		    	if (v.getLinea().equals(linea))
		    		viajes.add(v);
		    }
		}   
		//Traemos todos los viajes.
		Estadistica <SeccionTren> estadistica = new Estadistica <SeccionTren>(); 		
		SeccionTren seccion;
		
		for (ViajeEfectivoTren viaje : viajes) {
			//TODO : Ver que hacer con Seccion
			//seccion = viaje.getSeccion();
			seccion = null;
			estadistica.agregarNodo(new NodoEstadistica<SeccionTren>(seccion, viaje.getImporte()));
		}
		
		return estadistica;
	}
	
	public Estadistica<TramoColectivo> generarEstadisticaTramosColectivo(LineaColectivo linea, GregorianCalendar desde, GregorianCalendar hasta){
		
		Estadistica <TramoColectivo> estadistica = new Estadistica <TramoColectivo>(); 
		
		List<TransaccionSUBE> txs = abmTx.traerTransacciones();
		TramoColectivo tramo;
		FichadaColectivo f;
		
		for (TransaccionSUBE tx : txs) {
			if (FuncionesGregorian.estaEntreFechas(tx.getFichada().getFechaHora(), desde, hasta)) {
				if (tx.getFichada() instanceof FichadaColectivo) {
					f = (FichadaColectivo) tx.getFichada();
					if (f.getInterno().getLineaColectivo().equals(linea)) { 
						tramo = f.getTramo();
						estadistica.agregarNodo(new NodoEstadistica<TramoColectivo>(tramo, tx.getImporte()));
					}
				}
			}
		}
		
		return estadistica;
		
	}
	
	//	Comienzo de declarion de  clases internas para los nodos
	
	public class Estadistica <T extends INombrable> {
		private List<NodoEstadistica<T>> nodos;
		
		public Estadistica (){
			this.nodos = new ArrayList<NodoEstadistica<T>>();
		}
		
		public void agregarNodo(NodoEstadistica<T> nodo) {
			for (NodoEstadistica<T> n : this.nodos) {
				if (n.getObjeto().equals(nodo.getObjeto())) {
					n.agregarNodo(nodo);
					return;
				}
			}
			this.nodos.add(nodo);
	
		}

		public List<NodoEstadistica<T>> getNodos() {
			return nodos;
		}

		public void setNodos(List<NodoEstadistica<T>> nodos) {
			this.nodos = nodos;
		}
	
		public String toJSON() throws JSONException {
			String jsonText  = "{ ";
			List <String> nombres = new ArrayList<String>();
			List <Integer> viajes = new ArrayList<Integer>();
			List <BigDecimal> montos = new ArrayList<BigDecimal>();
			System.out.println("Size :  " + this.nodos.size());
			for (NodoEstadistica<T> nodo : this.nodos) {
				nombres.add(nodo.getObjeto().getNombre());
				viajes.add(nodo.getViajes());
				montos.add(nodo.getMonto());
			}
			
			JSONObject json = new JSONObject();
			JSONArray jsonNombres = new JSONArray();
			JSONArray jsonViajes = new JSONArray();
			JSONArray jsonMontos = new JSONArray();
			
			for (String n : nombres) {
				jsonNombres.put(n);
			}
			
			for (int v : viajes) {
				jsonViajes.put(v);
			}

			for (BigDecimal m : montos) {
				jsonMontos.put(m.floatValue());
			}
			
			System.out.println("Total Viajes : " + jsonViajes.length());
			System.out.println("Total Nombres : " + jsonNombres.length());
			System.out.println("Total Montos : " + jsonMontos.length());
			
			
			//json.append("labels", jsonNombres);
			json.put("labels", jsonNombres);
			json.put("viajes", jsonViajes);
			json.put("montos", jsonMontos);
			
			/*
			json.append("labels", jsonNombres);
			json.append("viajes", jsonViajes);
			json.append("montos", jsonMontos);
			
			//NOMBRES
			String strNombres = "'labels' : {";
			for (int i = 0; i < nombres.size(); i++) {
				String n = nombres.get(i);
				if (i > 0) {
					strNombres += ", ";
				}
				strNombres += "'" + n.toString() +"'";
			}
			strNombres += "}";
			
			//VIAJES
			String strViajes = "viajes : {";
			for (int i = 0; i < viajes.size(); i++) {
				Integer n = viajes.get(i);
				if (i > 0) {
					strViajes += ", ";
				}
				strViajes += n.toString();
			}
			strViajes += "}";
			
			//MONTOS
			String strMontos = "'montos' : {";
			for (int i = 0; i < montos.size(); i++) {
				BigDecimal n = montos.get(i);
				if (i > 0) {
					strMontos += ", ";
				}
				strMontos += n.toString();
			}
			strMontos += "}";
			
			//UNIMOS
			
			jsonText += strNombres + ", " + strViajes + ", " + strMontos + "}";
			
			System.out.println(jsonText);*/
			System.out.println(json);
			return json.toString();
		}
		
	}
	
	public class NodoEstadistica <T extends INombrable>  {
		
		private T objeto;
		private int viajes;
		private BigDecimal monto;
		
		public NodoEstadistica(T objeto, BigDecimal montoInicial) {
			super();
			this.objeto = objeto;
			this.monto = montoInicial;
			this.viajes = 1;
		}
		public T getObjeto() {
			return objeto;
		}
		public void setObjeto(T objeto) {
			this.objeto = objeto;
		}
		public int getViajes() {
			return viajes;
		}
		public void setViajes(int viajes) {
			this.viajes = viajes;
		}
		public BigDecimal getMonto() {
			return monto;
		}
		public void setMonto(BigDecimal monto) {
			this.monto = monto;
		}
		
		public void actualizarNodo (TransaccionSUBE  transaccionSUBE) {
			this.viajes++;
			this.monto = this.monto.add(transaccionSUBE.getImporte());
		}
		
		public void actualizarNodo(ViajeEfectivoTren viaje) {
			this.viajes++;
			this.monto = this.monto.add(viaje.getImporte());
		}
		
		public void agregarNodo(NodoEstadistica<T> otroNodo) {
			this.viajes += otroNodo.getViajes();
			this.monto.add(otroNodo.getMonto());
		}
		
	}
	
}
