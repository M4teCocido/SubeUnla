package controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

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
import modelo.fichadas.tren.SeccionTren;
import modelo.fichadas.tren.ViajeEfectivoTren;
import negocio.Estadisticas;
import negocio.LineaColectivoABM;
import negocio.LineaSubteABM;
import negocio.LineaTrenABM;
import negocio.TarjetaSubeABM;
import negocio.TransaccionABM;

public class ControladorEstadisticas extends HttpServlet {
	
	private static final int TODAS = 0;
	private Estadisticas e = new Estadisticas();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		procesarPeticion(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		procesarPeticion(request, response);
	}
	
	private String procesarPeticionEstadisticaColectivo(GregorianCalendar desde, GregorianCalendar hasta,HttpServletRequest request, HttpServletResponse response) throws Exception {
		int idLinea = Integer.parseInt(request.getParameter("linea"));
		
		if (idLinea == TODAS) {
			return this.generarEstadisticaLineasColectivo(desde, hasta, request, response);
		} else {
			return this.generarEstadisticaTramosColectivo(desde, hasta, idLinea, request, response);
		}
	}
	
	private String procesarPeticionEstadisticaSubte(GregorianCalendar desde, GregorianCalendar hasta, HttpServletRequest request, HttpServletResponse response) throws JSONException {
		return this.generarEstadisticaLineasSubte(desde, hasta, request, response);
	}

	private String procesarPeticionEstadisticaTren(GregorianCalendar desde, GregorianCalendar hasta, HttpServletRequest request, HttpServletResponse response) throws Exception {
		int idLinea = Integer.parseInt(request.getParameter("linea"));
		if (idLinea == TODAS) {
			return this.generarEstadisticaLineasTren(desde, hasta, request, response);
		} else {
			return this.generarEstadisticaSeccionesTren(desde, hasta, idLinea, request, response);
		}
	}
	
	private String generarEstadisticaLineasColectivo(GregorianCalendar desde, GregorianCalendar hasta, HttpServletRequest request, HttpServletResponse response) throws JSONException {
		Estadisticas.Estadistica<LineaColectivo> estadistica = e.generarEstadisticaLineasColectivo(desde, hasta);
		return estadistica.toJSON();
		
	}
	
	private String generarEstadisticaTramosColectivo(GregorianCalendar desde, GregorianCalendar hasta, int idLinea, HttpServletRequest request, HttpServletResponse response) throws Exception {
		LineaColectivoABM abmLineaColectivo = new LineaColectivoABM();
		LineaColectivo linea = abmLineaColectivo.traerLineaPorId(idLinea);
		Estadisticas.Estadistica<TramoColectivo> estadistica = e.generarEstadisticaTramosColectivo(linea, desde, hasta);
		return estadistica.toJSON();
	}
	
	private String generarEstadisticaLineasSubte(GregorianCalendar desde, GregorianCalendar hasta, HttpServletRequest request, HttpServletResponse response) throws JSONException {
		Estadisticas.Estadistica<LineaSubte> estadistica = e.generarEstadisticaLineasSubte(desde, hasta);
		return estadistica.toJSON();
	}
	
	private String generarEstadisticaLineasTren(GregorianCalendar desde, GregorianCalendar hasta, HttpServletRequest request, HttpServletResponse response) throws JSONException {
		Estadisticas.Estadistica<LineaTren> estadistica = e.generarEstadisticaLineasTren(desde, hasta);
		return estadistica.toJSON();
	}
	
	private String generarEstadisticaSeccionesTren(GregorianCalendar desde, GregorianCalendar hasta, int idLinea, HttpServletRequest request, HttpServletResponse response) throws Exception {
		LineaTrenABM abmLineaTren = new LineaTrenABM();
		LineaTren linea = abmLineaTren.traerLineaPorId(idLinea);
		Estadisticas.Estadistica<SeccionTren> estadistica = e.generarEstadisticaSeccionesTren(linea, desde, hasta);
		return estadistica.toJSON();
	}


	private List<GregorianCalendar> parsearFecha (HttpServletRequest request) {
		//MetodoPAraPara parsear periodo desde  request
		
    	int anioDesde = Integer.parseInt(request.getParameter("anioDesde"));
    	int mesDesde  = Integer.parseInt(request.getParameter("mesDesde"));
    	int diaDesde  = Integer.parseInt(request.getParameter("diaDesde"));
    	int horaDesde = Integer.parseInt(request.getParameter("horaDesde"));
    	int minDesde  = Integer.parseInt(request.getParameter("minDesde"));
    	
    	int anioHasta = Integer.parseInt(request.getParameter("anioHasta"));
    	int mesHasta  = Integer.parseInt(request.getParameter("mesHasta"));
    	int diaHasta  = Integer.parseInt(request.getParameter("diaHasta"));
    	int horaHasta = Integer.parseInt(request.getParameter("horaHasta"));
    	int minHasta  = Integer.parseInt(request.getParameter("minHasta"));
    	
    	List<GregorianCalendar> periodo = new ArrayList<GregorianCalendar>();
    	periodo.add(new GregorianCalendar  (anioDesde, mesDesde - 1, diaDesde, horaDesde , minDesde, 0));
    	periodo.add(new GregorianCalendar  (anioHasta, mesHasta - 1, diaHasta, horaHasta , minHasta, 0));
    	
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
	
	private void procesarPeticion(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		//response.setContentType("text/html;	charset=UTF-8");
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();	
		try {
			String strNroValidacion= request.getParameter("nroValidacion");
			if (strNroValidacion == null)
				request.getRequestDispatcher("/estadisticas.jsp").forward(request, response);
			else {
				int nroValidacion = Integer.parseInt(strNroValidacion);
				System.out.println("Numero : " + nroValidacion);
				String resultado = "";
				List<GregorianCalendar> fechas = parsearFecha(request);
				
				switch(nroValidacion) {
				
				case 1: //Devolver estadisticas
					resultado = this.procesarPeticionEstadisticaColectivo(fechas.get(0), fechas.get(1), request, response);
					break;
				case 2: //Devolver estadisticas
					resultado = this.procesarPeticionEstadisticaSubte(fechas.get(0), fechas.get(1), request, response);
					break;
				case 3: //Devolver estadisticas
					resultado = this.procesarPeticionEstadisticaTren (fechas.get(0), fechas.get(1), request, response);
					break;
				default:
					break;
				}
				response.setStatus(200);
				out.print(resultado);
			}
			
			
		} catch(Exception e) {
			System.out.println("Excepction agarrada : " + e.getMessage());
			e.printStackTrace();
			if (!response.isCommitted())
				response.sendError(500, e.getMessage());
		}
	}
	
	
	
	
}
