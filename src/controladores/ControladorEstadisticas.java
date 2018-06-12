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

import modelo.fichadas.FichadaRecarga;
import modelo.fichadas.TransaccionSUBE;
import modelo.fichadas.colectivo.FichadaColectivo;
import modelo.fichadas.subte.EstacionSubte;
import modelo.fichadas.subte.FichadaSubte;
import modelo.fichadas.tren.FichadaTren;
import negocio.LineaSubteABM;
import negocio.TransaccionABM;

public class ControladorEstadisticas extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		procesarPeticion(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		procesarPeticion(request, response);
	}
	
	void generarEstadistica (HttpServletRequest request, HttpServletResponse response){
		TransaccionABM  transaccionABM = new TransaccionABM();
		
		
		
		List<TransaccionSUBE>transacciones=transaccionABM.traerTransacciones(parsearFecha(request));
		List<NodoEstadisticaSubte> nodosViajeSubte = new ArrayList<NodoEstadisticaSubte>();
		List<TransaccionSUBE> viajesColectivo = new ArrayList<TransaccionSUBE>();
		List<TransaccionSUBE> viajesTren = new ArrayList<TransaccionSUBE>();
		
		for (int i=0; i< transacciones.size();i++) {
			if (transacciones.get(i).getFichada() instanceof FichadaSubte) {
				procesarNodo = 
			
				
				
			
			}
			
			if (transacciones.get(i).getFichada() instanceof FichadaColectivo) {
				viajesColectivo.add(transacciones.get(i));
			}
			
			if (transacciones.get(i).getFichada() instanceof FichadaTren) {
				viajesTren.add(transacciones.get(i));
			}
		}
	
			
	
	
	}

	
	void procesarNodo(List<NodoEstadisticaSubte> nodosViajeSubte, TransaccionSUBE transaccion) {
		boolean encontrado = false;
		int i = 0;
		while ( i<nodosViajeSubte.size() && encontrado == false) {
			FichadaSubte fichadaSubte =  (FichadaSubte)transaccion.getFichada();	
			
			if(nodosViajeSubte.get(i).getEstacionSubte().equals(fichadaSubte.getEstacionSubte())){
				
				econtrado = true;
			}
			i++;
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
				
				case 1: //Devolver todos los viajes
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
	private class NodoEstadisticaSubte {
		private EstacionSubte estacionSubte; 
		private int cantidad;
		private BigDecimal monto; 
		public NodoEstadisticaSubte(EstacionSubte estacionSubte, int cantidad) {
			super();
			this.estacionSubte = estacionSubte;
			this.cantidad = cantidad;
			this.monto = new BigDecimal(0);
		}

		public EstacionSubte getEstacionSubte() {
			return estacionSubte;
		}

		public void setEstacionSubte(EstacionSubte estacionSubte) {
			this.estacionSubte = estacionSubte;
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
		
	}
	
}
