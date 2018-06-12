package controladores;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.TarjetaSube;
import modelo.fichadas.FichadaRecarga;
import modelo.fichadas.TransaccionSUBE;
import modelo.fichadas.colectivo.FichadaColectivo;
import modelo.fichadas.colectivo.LineaColectivo;
import modelo.fichadas.colectivo.TramoColectivo;
import modelo.fichadas.subte.EstacionSubte;
import modelo.fichadas.subte.FichadaSubte;
import modelo.fichadas.subte.LineaSubte;
import modelo.fichadas.tren.FichadaTren;
import modelo.fichadas.tren.LineaTren;
import modelo.fichadas.tren.ViajeEfectivoTren;
import negocio.LineaSubteABM;
import negocio.TarjetaSubeABM;
import negocio.TransaccionABM;

public class ControladorEstadisticas extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		procesarPeticion(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		procesarPeticion(request, response);
	}
	
	void generarEstadistica (HttpServletRequest request, HttpServletResponse response){//Metodo maestro q genera las estadisticas
		
		TransaccionABM  transaccionABM = new TransaccionABM();
		List<GregorianCalendar> periodo = parsearFecha(request);
		List<TransaccionSUBE>transacciones=transaccionABM.traerTransacciones(periodo);
		List<ViajeEfectivoTren>viajesEfectivosTren = obtenerViajesTren(periodo.get(0),periodo.get(1));
		
		//Declaracion de Listas que contienen los nodos para las  estadisticas.
		List<NodoEstadisticaSubte> nodosViajeSubte = new ArrayList<NodoEstadisticaSubte>();//Acumula los  viajes por linea subte(cada nodo es una categoria linea)
		List<NodoEstadisticasColectivoTramo> nodosViajeColectivoXTramo = new ArrayList<NodoEstadisticasColectivoTramo>();//Acumula los viajes por tramo de colectivo (cada nodo es una  categoria de tramo )
		List<NodoEstadisticasTrenXLinea> nodosViajeTrenXLinea = new ArrayList<NodoEstadisticasTrenXLinea>();
		List<NodoEstadisticasLineaColectivo> nodosViajeColectivoXLinea = new ArrayList<NodoEstadisticasLineaColectivo>();
		
		
	
		for (int i=0; i< transacciones.size();i++) { //avanza entre la lista de transacciones
			
			if (transacciones.get(i).getFichada() instanceof FichadaSubte) {
				procesarNodoSubte(nodosViajeSubte,transacciones.get(i));
				//se cargan los nodos que contienen la estadistica separa
				//por estacio de subte
			}
			
			if (transacciones.get(i).getFichada() instanceof FichadaColectivo) {
				procesarNodoColectivo(nodosViajeColectivoXTramo, transacciones.get(i));
				procesarNodoColectivoXLinea(nodosViajeColectivoXLinea, transacciones.get(i));
			
			}
		}
	
		
		for(int i=0; i<viajesEfectivosTren.size();i++) {
			//Recorremos la lista de viajes efectivos y agrupamos segun criterio de linea y de ??tramo??
			procesarNodoTren( nodosViajeTrenXLinea, viajesEfectivosTren.get(i));
		}
		
	
			//Aca  tendriamos que empaquetar todo y mandarlo al jsp para  q lo maneje. 			
	}

	
	
	void procesarNodoColectivoXLinea(List<NodoEstadisticasLineaColectivo> nodosViajeColectivoXLinea, TransaccionSUBE transaccion) {
		boolean encontrado = false;
		int i = 0;
		FichadaColectivo fichadaColectivo=  (FichadaColectivo)transaccion.getFichada();	

		if (nodosViajeColectivoXLinea.size() != 0) {
			while (i< nodosViajeColectivoXLinea.size() && encontrado == false ) {
				if (nodosViajeColectivoXLinea.get(i).getLineaColectivo().equals(fichadaColectivo.getInterno().getLineaColectivo())){
					nodosViajeColectivoXLinea.get(i).actualizarNodo(transaccion);
				}
			}
		
			if (encontrado == false) {
				NodoEstadisticasLineaColectivo nodoEstadisticaLineaColectivo = new NodoEstadisticasLineaColectivo(fichadaColectivo.getInterno().getLineaColectivo());
				nodoEstadisticaLineaColectivo.actualizarNodo(transaccion);
				nodosViajeColectivoXLinea.add(nodoEstadisticaLineaColectivo);
			}
		}else {
			NodoEstadisticasLineaColectivo nodoEstadisticaLineaColectivo = new NodoEstadisticasLineaColectivo(fichadaColectivo.getInterno().getLineaColectivo());
			nodoEstadisticaLineaColectivo.actualizarNodo(transaccion);
			nodosViajeColectivoXLinea.add(nodoEstadisticaLineaColectivo);
		}
	}
	
	
	
	//procesa nodos de lista de  viaje colectivo
	void procesarNodoColectivo(List<NodoEstadisticasColectivoTramo> nodosViajeColectivoXTramo, TransaccionSUBE transaccion) {
		boolean encontrado = false;
		int i = 0;
		FichadaColectivo fichadaColectivo=  (FichadaColectivo)transaccion.getFichada();	

		if(nodosViajeColectivoXTramo.size()!=0){
			while ( i<nodosViajeColectivoXTramo.size() && encontrado==false) {
				
				//recorrre los nodos  si existe tiene q  sumar  un viaje y sumar el monto del viaje
				if(nodosViajeColectivoXTramo.get(i).getTramoColectivo().equals(fichadaColectivo.getTramo())){
					nodosViajeColectivoXTramo.get(i).actualizarNodo(transaccion);
					encontrado = true;
				
				}
				i++;
			}
		
			if (encontrado == false) {
				//Crea nodo si no existe  creado por q no encontro nada en el while 
				//(buscar reutilizar codigo)
				
				NodoEstadisticasColectivoTramo nodo = new NodoEstadisticasColectivoTramo(fichadaColectivo.getTramo());
				nodo.actualizarNodo(transaccion);
				nodosViajeColectivoXTramo.add(nodo);
			}
		}else {// crea nodo si no existe ninguno creado
			
			NodoEstadisticasColectivoTramo nodo = new NodoEstadisticasColectivoTramo(fichadaColectivo.getTramo());
			nodo.actualizarNodo(transaccion);
			nodosViajeColectivoXTramo.add(nodo);
				
		}
	}	
	
	
	//procesa nodos de  lista de  viajes  subte
	void procesarNodoSubte(List<NodoEstadisticaSubte> nodosViajeSubte, TransaccionSUBE transaccion) {
		boolean encontrado = false;
		int i = 0;
		FichadaSubte fichadaSubte =  (FichadaSubte)transaccion.getFichada();	
		if(nodosViajeSubte.size()!=0){
			while ( i<nodosViajeSubte.size() && encontrado==false) {
		
				//recorrre los nodos  si existe tiene q  sumar  un viaje y sumar el monto del viaje
				if(nodosViajeSubte.get(i).getLineaSubte().equals( (fichadaSubte.getEstacionSubte().getLineaSubte()))){
					nodosViajeSubte.get(i).actualizarNodo(transaccion);
					encontrado = true;
				
				}
				i++;
			}
		
			if (encontrado == false) {
				//Crea nodo si no existe  creado por q no encontro nada en el while 
				//(buscar reutilizar codigo)
		
				NodoEstadisticaSubte nodo = new NodoEstadisticaSubte(fichadaSubte.getEstacionSubte().getLineaSubte());
				nodo.actualizarNodo(transaccion);
				nodosViajeSubte.add(nodo);
			}
		}else {// crea nodo si no existe ninguno creado
		
			NodoEstadisticaSubte nodo = new NodoEstadisticaSubte(fichadaSubte.getEstacionSubte().getLineaSubte());
			nodo.actualizarNodo(transaccion);
			nodosViajeSubte.add(nodo);
				
		}
	}	
	
	
	
	void procesarNodoTren (List<NodoEstadisticasTrenXLinea> nodosViajeTrenXLinea, ViajeEfectivoTren viajeEfectivo) {
		
		boolean encontrado = false;
		int i = 0;
		if (nodosViajeTrenXLinea.size()!=0) {
			while ( i<nodosViajeTrenXLinea.size() && encontrado==false) {
				if (nodosViajeTrenXLinea.get(i).getLineaTren().equals(viajeEfectivo.getLinea())) {
					nodosViajeTrenXLinea.get(i).actualizarNodo(viajeEfectivo);
					encontrado = true;
				}
				
				if (encontrado = false) {
					
					NodoEstadisticasTrenXLinea nodoViajeTrenXLinea  = new NodoEstadisticasTrenXLinea(viajeEfectivo.getLinea());
					nodoViajeTrenXLinea.actualizarNodo(viajeEfectivo);
					nodosViajeTrenXLinea.add(nodoViajeTrenXLinea);
					
				}
			}
			
		}else {
			NodoEstadisticasTrenXLinea nodoViajeTrenXLinea  = new NodoEstadisticasTrenXLinea(viajeEfectivo.getLinea());
			nodoViajeTrenXLinea.actualizarNodo(viajeEfectivo);
			nodosViajeTrenXLinea.add(nodoViajeTrenXLinea);
			
		}
	}
	
	
	
	
	
	private void procesarPeticion(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		response.setContentType("text/html;	charset=UTF-8");
		try {
			String strNroValidacion= request.getParameter("nroValidacion");
			if (strNroValidacion == null)
				request.getRequestDispatcher("/listados.jsp").forward(request, response);
			else {
				int nroValidacion = Integer.parseInt(strNroValidacion);
				System.out.println("Numero : " + nroValidacion);
				switch(nroValidacion) {
				
				case 1: //Devolver estadisticas
					this.generarEstadistica(request, response);
					break;
			
				default:
					break;
				}
			}
		} catch(Exception e) {
			System.out.println("Excepction agarrada : " + e.getMessage());
			e.printStackTrace();
			if (!response.isCommitted())
				response.sendError(500, e.getMessage());
		}
	}
	
	private List<GregorianCalendar> parsearFecha (HttpServletRequest request) {
		//MetodoPAraPara parsear periodo desde  request
		
    	int anioInicial = Integer.parseInt(request.getParameter("anioInicial"));
    	int mesInicial  = Integer.parseInt(request.getParameter("mesInicial"));
    	int diaInicial  = Integer.parseInt(request.getParameter("diaInicial"));
    	int horaInicial = Integer.parseInt(request.getParameter("horaInicial"));
    	int minInicial  = Integer.parseInt(request.getParameter("minInicial"));
    	
    	int anioFinal = Integer.parseInt(request.getParameter("anioFinal"));
    	int mesFinal  = Integer.parseInt(request.getParameter("mesFinal"));
    	int diaFinal  = Integer.parseInt(request.getParameter("diaFinal"));
    	int horaFinal = Integer.parseInt(request.getParameter("horaFinal"));
    	int minFinal  = Integer.parseInt(request.getParameter("minFinal"));
    	
    	List<GregorianCalendar> periodo = new ArrayList<GregorianCalendar>();
    	periodo.add(new GregorianCalendar  (anioInicial, mesInicial - 1, diaInicial, horaInicial , minInicial, 0));
    	periodo.add(new GregorianCalendar  (anioFinal, mesFinal - 1, diaFinal, horaFinal , minFinal, 0));
    	
    	return  periodo;
	}
	
	
	private List<ViajeEfectivoTren> obtenerViajesTren(GregorianCalendar desde, GregorianCalendar hasta){
		TarjetaSubeABM tarjetaABM = new TarjetaSubeABM();
		List<TarjetaSube> tarjetas = tarjetaABM.traerTarjetas();
		List<ViajeEfectivoTren> viajes = new ArrayList<ViajeEfectivoTren>();
		
		for (TarjetaSube t : tarjetas) {
		    for (ViajeEfectivoTren v : t.obtenerViajesTren(desde, hasta)) {
		    	viajes.add(v);
		    }
		}   
		return viajes;
	}
	
	//Comienzo de declarion de  clases internas para los nodos
	private class NodoEstadisticaSubte {
		private LineaSubte lineaSubte; 
		private int cantidad;
		private BigDecimal monto; 
		
		public NodoEstadisticaSubte(LineaSubte lineaSubte) {
			super();
			this.lineaSubte = lineaSubte;
			this.cantidad = 0;
			this.monto = new BigDecimal(0);
		}
		public LineaSubte getLineaSubte() {
			return lineaSubte;
		}
		public void setLineaSubte(LineaSubte lineaSubte) {
			this.lineaSubte = lineaSubte;
		}
		public int getCantidad() {
			return cantidad;
		}
		public void setCantidad(int cantidad) {
			this.cantidad = cantidad;
		}
		public BigDecimal getMonto() {
			return monto;
		}
		public void setMonto(BigDecimal monto) {
			this.monto = monto;
		}
		
		public void actualizarNodo (TransaccionSUBE  transaccionSUBE) {
			this.cantidad = this.cantidad++;
			this.monto = this.monto.add(transaccionSUBE.getImporte());
		}
		
		
	}
	
	private class NodoEstadisticasColectivoTramo{
		private TramoColectivo tramoColectivo;
		private int cantidad;
		private BigDecimal monto;
		public NodoEstadisticasColectivoTramo(TramoColectivo tramoColectivo) {
		    super();
		    this.tramoColectivo = tramoColectivo;
		    this.cantidad = 0;
		    this.monto = new BigDecimal(0);
		}

		public TramoColectivo getTramoColectivo() {
		    return tramoColectivo;
		}
		public int getCantidad() {
		    return cantidad;
		}
		public BigDecimal getMonto() {
		    return monto;
		}
		public void setTramoColectivo(TramoColectivo tramoColectivo) {
		    this.tramoColectivo = tramoColectivo;
		}
		public void setCantidad(int cantidad) {
		    this.cantidad = cantidad;
		}
		public void setMonto(BigDecimal monto) {
		    this.monto = monto;
		}
		public void actualizarNodo (TransaccionSUBE  transaccionSUBE) {
			this.cantidad = this.cantidad++;
			this.monto = this.monto.add(transaccionSUBE.getImporte());
		}

	}
	
	private class NodoEstadisticasTrenXLinea{
		private LineaTren lineaTren;
		private int cantidad;
		private BigDecimal monto;
		public NodoEstadisticasTrenXLinea(LineaTren lineaTren) {
		    super();
		    this.lineaTren = lineaTren;
		    this.cantidad = 0;
		    this.monto = new BigDecimal(0);
		}
		
		public LineaTren getLineaTren() {
			return lineaTren;
		}

		public void setLineaTren(LineaTren lineaTren) {
			this.lineaTren = lineaTren;
		}

		public int getCantidad() {
			return cantidad;
		}

		public void setCantidad(int cantidad) {
			this.cantidad = cantidad;
		}

		public BigDecimal getMonto() {
			return monto;
		}

		public void setMonto(BigDecimal monto) {
			this.monto = monto;
		}

		public void actualizarNodo (ViajeEfectivoTren  viaje) {
			this.cantidad = this.cantidad++;
			this.monto = this.monto.add(viaje.getImporte());
		}

	}
	
	
	private class NodoEstadisticasLineaColectivo{
		private LineaColectivo lineaColectivo;
		private int cantidad;
		private BigDecimal monto;
		
		public NodoEstadisticasLineaColectivo(LineaColectivo lineaColectivo) {
			super();
			this.lineaColectivo = lineaColectivo;
			this.cantidad = 0;
			this.monto = new BigDecimal(0);
		}
	
		public LineaColectivo getLineaColectivo() {
			return lineaColectivo;
		}
		public int getCantidad() {
			return cantidad;
		}
		public BigDecimal getMonto() {
			return monto;
		}
		public void setLineaColectivo(LineaColectivo lineaColectivo) {
			this.lineaColectivo = lineaColectivo;
		}
		public void setCantidad(int cantidad) {
			this.cantidad = cantidad;
		}
		public void setMonto(BigDecimal monto) {
			this.monto = monto;
		}
	
		public void actualizarNodo (TransaccionSUBE  transaccionSUBE) {
			this.cantidad = this.cantidad++;
			this.monto = this.monto.add(transaccionSUBE.getImporte());
		}

	}
	
}
