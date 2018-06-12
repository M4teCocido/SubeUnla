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
		int idLinea = Integer.parseInt(request.getParameter("idLinea"));
		
		if (idLinea == TODAS) {
			return this.generarEstadisticaLineasColectivo(desde, hasta, request, response);
		} else {
			return this.generarEstadisticaTramosColectivo(desde, hasta, idLinea, request, response);
		}
	}
	
	private String procesarPeticionEstadisticaSubte(GregorianCalendar desde, GregorianCalendar hasta, HttpServletRequest request, HttpServletResponse response) {
		return this.generarEstadisticaLineasSubte(desde, hasta, request, response);
	}

	private String procesarPeticionEstadisticaTren(GregorianCalendar desde, GregorianCalendar hasta, HttpServletRequest request, HttpServletResponse response) throws Exception {
		int idLinea = Integer.parseInt(request.getParameter("idLinea"));
		if (idLinea == TODAS) {
			return this.generarEstadisticaLineasTren(desde, hasta, request, response);
		} else {
			return this.generarEstadisticaSeccionesTren(desde, hasta, idLinea, request, response);
		}
	}
	
	private String generarEstadisticaLineasColectivo(GregorianCalendar desde, GregorianCalendar hasta, HttpServletRequest request, HttpServletResponse response) {
		Estadisticas.Estadistica<LineaColectivo> estadistica = e.generarEstadisticaLineasColectivo(desde, hasta);
		return estadistica.toJSON();
		
	}
	
	private String generarEstadisticaTramosColectivo(GregorianCalendar desde, GregorianCalendar hasta, int idLinea, HttpServletRequest request, HttpServletResponse response) throws Exception {
		LineaColectivoABM abmLineaColectivo = new LineaColectivoABM();
		LineaColectivo linea = abmLineaColectivo.traerLineaPorId(idLinea);
		Estadisticas.Estadistica<TramoColectivo> estadistica = e.generarEstadisticaTramosColectivo(linea, desde, hasta);
		return estadistica.toJSON();
	}
	
	private String generarEstadisticaLineasSubte(GregorianCalendar desde, GregorianCalendar hasta, HttpServletRequest request, HttpServletResponse response) {
		Estadisticas.Estadistica<LineaSubte> estadistica = e.generarEstadisticaLineasSubte(desde, hasta);
		return estadistica.toJSON();
	}
	
	private String generarEstadisticaLineasTren(GregorianCalendar desde, GregorianCalendar hasta, HttpServletRequest request, HttpServletResponse response) {
		
		Estadisticas.Estadistica<LineaTren> estadistica = e.generarEstadisticaLineasTren(desde, hasta);
		return estadistica.toJSON();
	}
	
	private String generarEstadisticaSeccionesTren(GregorianCalendar desde, GregorianCalendar hasta, int idLinea, HttpServletRequest request, HttpServletResponse response) throws Exception {
		LineaTrenABM abmLineaTren = new LineaTrenABM();
		LineaTren linea = abmLineaTren.traerLineaPorId(idLinea);
		Estadisticas.Estadistica<SeccionTren> estadistica = e.generarEstadisticaSeccionesTren(linea, desde, hasta);
		return estadistica.toJSON();
	}

	private void procesarPeticion(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		//response.setContentType("text/html;	charset=UTF-8");
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();	
		try {
			String strNroValidacion= request.getParameter("nroValidacion");
			if (strNroValidacion == null)
				request.getRequestDispatcher("/listados.jsp").forward(request, response);
			else {
				int nroValidacion = Integer.parseInt(strNroValidacion);
				System.out.println("Numero : " + nroValidacion);
				
				List<GregorianCalendar> fechas = parsearFecha(request);
				
				switch(nroValidacion) {
				
				case 1: //Devolver estadisticas
					out.print(this.procesarPeticionEstadisticaColectivo(fechas.get(0), fechas.get(1), request, response));
					break;
				case 2: //Devolver estadisticas
					this.procesarPeticionEstadisticaSubte(fechas.get(0), fechas.get(1), request, response);
					break;
				case 3: //Devolver estadisticas
					this.procesarPeticionEstadisticaTren (fechas.get(0), fechas.get(1), request, response);
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
	
	
	
}
