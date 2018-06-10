package controladores;

import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.TarjetaSube;
import modelo.fichadas.TransaccionSUBE;
import modelo.fichadas.colectivo.FichadaColectivo;
import modelo.fichadas.subte.FichadaSubte;
import modelo.fichadas.tren.FichadaTren;
import negocio.TarjetaSubeABM;
import negocio.TransaccionABM;

public class ControladorGenerarLista extends HttpServlet {
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		procesarPeticion(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		procesarPeticion(request, response);
	}
	
	
	private void procesarPeticionListadoViajesColectivo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TransaccionABM transaccionABM = new TransaccionABM();
		
		List<TransaccionSUBE> transacciones = transaccionABM.traerTransacciones(parsearFecha(request));
		List<TransaccionSUBE> lstViajesColectivo = null;
		
		for (int i = 0 ; i<transacciones.size(); i++) {
		    if (transacciones.get(i).getFichada() instanceof FichadaColectivo) {
		    	lstViajesColectivo.add(transacciones.get(i));
		    
		    }
		}    
	
		request.setAttribute("transacciones", lstViajesColectivo);
	    request.getRequestDispatcher("views/listaViajesColectivo.jsp").forward(request, response);
	    
	}
	
	
	private void procesarPeticionListadoViajesSubte(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TransaccionABM transaccionABM = new TransaccionABM();
		List<TransaccionSUBE> transacciones = transaccionABM.traerTransacciones(parsearFecha(request));
		List<TransaccionSUBE> lstViajesSubte = null;
		for (int i = 0 ; i<transacciones.size(); i++) {
		    if (transacciones.get(i).getFichada() instanceof FichadaSubte) {
		    	lstViajesSubte.add(transacciones.get(i));
		    }
		}    
	
		request.setAttribute("transacciones", lstViajesSubte);
	    request.getRequestDispatcher("views/listaViajesSubte.jsp").forward(request, response);
	    
	}
	
	
	
	private void procesarPeticionListadoViajesTren(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TransaccionABM transaccionABM = new TransaccionABM();
		List<TransaccionSUBE> transacciones = transaccionABM.traerTransacciones(parsearFecha(request));
		List<TransaccionSUBE> lstViajesTren = null;
		for (int i = 0 ; i<transacciones.size(); i++) {
		    if (transacciones.get(i).getFichada() instanceof FichadaTren) {
		    	lstViajesTren.add(transacciones.get(i));
		    }
		}    
	
		request.setAttribute("transacciones", lstViajesTren);
	    request.getRequestDispatcher("views/listaViajesTren.jsp").forward(request, response);
	    
	}
	
	
	
	private void procesarPeticion(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		response.setContentType("text/html;	charset=UTF-8");
		try {
			String strNroValidacion= request.getParameter("nroValidacion");
			if (strNroValidacion == null)
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			else {
				int nroValidacion = Integer.parseInt(strNroValidacion);
				System.out.println("Numero : " + nroValidacion);
				switch(nroValidacion) {
				case 1: //Devolver tramos de colectivo
					this.procesarPeticionListadoViajesColectivo(request, response);
					break;
				case 2: //Devolver tramos de colectivo
					this.procesarPeticionListadoViajesSubte(request, response);
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
    	
    	
    	List<GregorianCalendar> periodo = null;
    	periodo.add(new GregorianCalendar  (anioInicial, mesInicial - 1, diaInicial, horaInicial , minInicial, 0));
    	periodo.add(new GregorianCalendar  (anioFinal, mesFinal - 1, diaFinal, horaFinal , minFinal, 0));
    	
    	return  periodo;
	}
}
	
	


