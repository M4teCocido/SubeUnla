package controladores;

import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.TarjetaSube;
import modelo.fichadas.TransaccionSUBE;
import modelo.fichadas.colectivo.FichadaColectivo;
import modelo.fichadas.colectivo.LineaColectivo;
import modelo.fichadas.subte.FichadaSubte;
import modelo.fichadas.subte.LineaSubte;
import modelo.fichadas.tren.FichadaTren;
import modelo.fichadas.tren.LineaTren;
import modelo.fichadas.tren.ViajeEfectivoTren;
import negocio.LineaColectivoABM;
import negocio.LineaSubteABM;
import negocio.LineaTrenABM;
import negocio.TarjetaSubeABM;
import negocio.TransaccionABM;

public class ControladorListados extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		procesarPeticion(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		procesarPeticion(request, response);
	}
	
	private void procesarPeticionLineasColectivo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LineaColectivoABM abm = new LineaColectivoABM();
		List<LineaColectivo> lstLinea = abm.traerLineas(); 
		request.setAttribute( "lstLineas" , lstLinea );
		request.getRequestDispatcher( "views/listados/listaLineasColectivo.jsp" ).forward( request , response );
		System.out.println("Cantidad Lineas Colectivo : " + lstLinea.size());
	}
	
	private void procesarPeticionLineasTren(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LineaTrenABM abm = new LineaTrenABM();
		List<LineaTren> lstLineas = abm.traerLineas();
		request.setAttribute("lstLineas", lstLineas);
		request.getRequestDispatcher("views/listados/listaLineasTren.jsp").forward(request, response);
	}
	
	private void procesarPeticionLineasSubte(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LineaSubteABM abm = new LineaSubteABM();
		List<LineaSubte> lstLineas = abm.traerLineas();
		request.setAttribute("lstLineas", lstLineas);
		request.getRequestDispatcher("views/listados/listaLineasSubte.jsp").forward(request, response);
	}
	
	private void procesarPeticionListadoViajesBasico(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TransaccionABM transaccionABM = new TransaccionABM();
		
		List<TransaccionSUBE> viajes = transaccionABM.traerViajes(parsearFecha(request));  
	
		request.setAttribute("transacciones", viajes);
	    request.getRequestDispatcher("views/listados/listadoBasico.jsp").forward(request, response);
	}
	
	private void procesarPeticionListadoViajesColectivo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TransaccionABM transaccionABM = new TransaccionABM();
		
		List<TransaccionSUBE> transacciones = transaccionABM.traerViajes(parsearFecha(request));
		List<TransaccionSUBE> viajes = new ArrayList<TransaccionSUBE>();
		
		for (int i = 0 ; i<transacciones.size(); i++) {
		    if (transacciones.get(i).getFichada() instanceof FichadaColectivo) {
		    	viajes.add(transacciones.get(i));
		    }
		}    
	
		request.setAttribute("transacciones", viajes);
	    request.getRequestDispatcher("views/listados/listaViajesColectivo.jsp").forward(request, response);
	    
	}
	
	private void procesarPeticionListadoViajesSubte(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TransaccionABM transaccionABM = new TransaccionABM();
		
		List<TransaccionSUBE> transacciones = transaccionABM.traerViajes(parsearFecha(request));
		List<TransaccionSUBE> viajes = new ArrayList<TransaccionSUBE>();
		
		for (int i = 0 ; i<transacciones.size(); i++) {
		    if (transacciones.get(i).getFichada() instanceof FichadaSubte) {
		    	viajes.add(transacciones.get(i));
		    }
		}    
	
		request.setAttribute("transacciones", viajes);
	    request.getRequestDispatcher("views/listados/listaViajesSubte.jsp").forward(request, response);
	    
	}
	
	private void procesarPeticionListadoViajesTren(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<GregorianCalendar> fechas = parsearFecha(request);
		List<ViajeEfectivoTren> viajes = obtenerViajesTren(fechas.get(0), fechas.get(1));
	
		request.setAttribute("viajes", viajes);
	    request.getRequestDispatcher("views/listados/listaViajesTren.jsp").forward(request, response);
	    
	}
	
	private void procesarPeticionListadoViajesColectivoPorLinea(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LineaColectivoABM lineaABM = new LineaColectivoABM();
		int idLinea = Integer.parseInt(request.getParameter("idLinea"));
		LineaColectivo linea = lineaABM.traerLineaPorId(idLinea);
		TransaccionABM transaccionABM = new TransaccionABM();
		
		List<TransaccionSUBE> transacciones = transaccionABM.traerViajes(parsearFecha(request));
		List<TransaccionSUBE> viajes = new ArrayList<TransaccionSUBE>();
		
		for (int i = 0 ; i<transacciones.size(); i++) {
		    if (transacciones.get(i).getFichada() instanceof FichadaColectivo) {
		    	FichadaColectivo f = ((FichadaColectivo) transacciones.get(i).getFichada());
		    	if (f.getInterno().getLineaColectivo().equals(linea))
		    		viajes.add(transacciones.get(i));
		    }
		}    
	
		request.setAttribute("transacciones", viajes);
	    request.getRequestDispatcher("views/listados/listaViajesColectivoPorLinea.jsp").forward(request, response);
	}
	
	private void procesarPeticionListadoViajesTrenPorLinea(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LineaTrenABM lineaABM = new LineaTrenABM();
		int idLinea = Integer.parseInt(request.getParameter("idLinea"));
		LineaTren linea = lineaABM.traerLineaPorId(idLinea);
		List<GregorianCalendar> fechas = parsearFecha(request);
		List<ViajeEfectivoTren> viajes = obtenerViajesTren(fechas.get(0), fechas.get(1));
		List<ViajeEfectivoTren> viajesFiltrados = new ArrayList<ViajeEfectivoTren>();
		
		for (ViajeEfectivoTren v : viajes) {
			if (v.getLinea().equals(linea)) {
				viajesFiltrados.add(v);
			}
		}
		
		request.setAttribute("viajes", viajesFiltrados);
		request.getRequestDispatcher("views/listados/listaViajesTrenPorLinea.jsp").forward(request, response);
	}
	
	private void procesarPeticionListadoViajesSubtePorLinea(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LineaSubteABM lineaABM = new LineaSubteABM();
		int idLinea = Integer.parseInt(request.getParameter("idLinea"));
		LineaSubte linea = lineaABM.traerLineaPorId(idLinea);
		TransaccionABM transaccionABM = new TransaccionABM();
		
		List<TransaccionSUBE> transacciones = transaccionABM.traerViajes(parsearFecha(request));
		List<TransaccionSUBE> viajes = new ArrayList<TransaccionSUBE>();
		
		for (int i = 0 ; i<transacciones.size(); i++) {
		    if (transacciones.get(i).getFichada() instanceof FichadaSubte) {
		    	FichadaSubte f = ((FichadaSubte) transacciones.get(i).getFichada());
		    	if (f.getEstacionSubte().getLineaSubte().equals(linea))
		    		viajes.add(transacciones.get(i));
		    }
		}    
	
		request.setAttribute("transacciones", viajes);
	    request.getRequestDispatcher("views/listados/listaViajesSubtePorLinea.jsp").forward(request, response);
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
		response.setContentType("text/html;	charset=UTF-8");
		try {
			String strNroValidacion= request.getParameter("nroValidacion");
			if (strNroValidacion == null)
				request.getRequestDispatcher("/listados.jsp").forward(request, response);
			else {
				int nroValidacion = Integer.parseInt(strNroValidacion);
				System.out.println("Numero : " + nroValidacion);
				switch(nroValidacion) {
				
				case 1: //Devolver todos los viajes
					this.procesarPeticionListadoViajesBasico(request, response);
					break;
				case 2: //Devolver Todos los viajes de colectivo
					this.procesarPeticionListadoViajesColectivo(request, response);
					break;
				case 3: //Devolver todos los viajes de subte
					this.procesarPeticionListadoViajesSubte(request, response);
					break;
				case 4: //Devolver todos los viajes de tren
					this.procesarPeticionListadoViajesTren(request, response);
					break;
				case 5: //Devolver Todos los viajes de colectivo por linea
					this.procesarPeticionListadoViajesColectivoPorLinea(request, response);
					break;
				case 6: //Devolver todos los viajes de subte por linea
					this.procesarPeticionListadoViajesSubtePorLinea(request, response);
					break;
				case 7: //Devolver todos los viajes de tren por linea
					this.procesarPeticionListadoViajesTrenPorLinea(request, response);
					break;
				case 10: //Devolver lineas de colectivo
					this.procesarPeticionLineasColectivo(request, response);
					break;
				case 11: //Devolver lineas de subte
					this.procesarPeticionLineasSubte(request, response);
					break;
				case 12: //Devolver lineas de tren
					this.procesarPeticionLineasTren(request, response);
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
}