package controladores;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.fichadas.colectivo.LineaColectivoDao;
import modelo.fichadas.colectivo.InternoColectivo;
import modelo.fichadas.colectivo.LineaColectivo;
import modelo.fichadas.colectivo.TramoColectivo;
import modelo.fichadas.subte.EstacionSubte;
import modelo.fichadas.subte.LineaSubte;
import modelo.fichadas.tren.EstacionTren;
import modelo.fichadas.tren.LineaTren;
import modelo.lectoras.LectoraExterna;
import negocio.LineaColectivoABM;
import negocio.LineaSubteABM;
import negocio.LineaTrenABM;
import negocio.LectorasExternasABM;

public class ControladorIngresarFichada extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		procesarPeticion(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		procesarPeticion(request, response);
	}
	
	private void procesarPeticionTramosColectivo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int idLinea = Integer.parseInt(request.getParameter("idLinea"));
		LineaColectivoABM abm = new LineaColectivoABM();
		LineaColectivo linea = abm.traerLineaPorId(idLinea);
		List<TramoColectivo> tramos = new ArrayList<TramoColectivo>();
		tramos.addAll(linea.getTramosColectivo());
		request.setAttribute("lstTramos", tramos);
		request.getRequestDispatcher("views/listaTramosColectivo.jsp").forward(request, response);
	}
	
	private void procesarPeticionLectorasCarga(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LectorasExternasABM abm = new LectorasExternasABM();
		List<LectoraExterna> lstLectora = abm.traerLectoras();
		request.setAttribute("lstLectoras", lstLectora);
		request.getRequestDispatcher("views/listaLectorasExternas.jsp").forward(request, response);
	}
	
	private void procesarPeticionLineasColectivo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LineaColectivoABM abm = new LineaColectivoABM();
		List<LineaColectivo> lstLinea = abm.traerLineas(); 
		request.setAttribute( "lstLineas" , lstLinea );
		request.getRequestDispatcher( "views/listaLineasColectivo.jsp" ).forward( request , response );
		System.out.println("Cantidad Lineas Colectivo : " + lstLinea.size());
	}
	
	private void procesarPeticionLineasTren(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LineaTrenABM abm = new LineaTrenABM();
		List<LineaTren> lstLineas = abm.traerLineas();
		request.setAttribute("lstLineas", lstLineas);
		request.getRequestDispatcher("views/listaLineasTren.jsp").forward(request, response);
	}
	
	private void procesarPeticionLineasSubte(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LineaSubteABM abm = new LineaSubteABM();
		List<LineaSubte> lstLineas = abm.traerLineas();
		request.setAttribute("lstLineas", lstLineas);
		request.getRequestDispatcher("views/listaLineasSubte.jsp").forward(request, response);
	}
	
	private void procesarPeticionInternosColectivo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int idLinea = Integer.parseInt(request.getParameter("idLinea"));
		LineaColectivoABM abm = new LineaColectivoABM();
		List<InternoColectivo> lstInterno = abm.traerInternosPorIdLinea(idLinea);
		request.setAttribute("lstInternos", lstInterno);
		request.getRequestDispatcher("views/listaInternosColectivo.jsp").forward(request, response);
	}
	
	private void procesarPeticionEstacionesSubte(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int idLinea = Integer.parseInt(request.getParameter("idLinea"));
		LineaSubteABM abm = new LineaSubteABM();
		LineaSubte linea = abm.traerLineaPorId(idLinea);
		List<EstacionSubte> lstEstacion = new ArrayList<EstacionSubte>();
		lstEstacion.addAll(linea.getRecorridoSubte());
		request.setAttribute("lstEstaciones", lstEstacion);
		request.getRequestDispatcher("views/listaEstacionesSubte.jsp").forward(request, response);
	}
	
	private void procesarPeticionEstacionesTren(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int idLinea = Integer.parseInt(request.getParameter("idLinea"));
		LineaTrenABM abm = new LineaTrenABM();
		LineaTren linea = abm.traerLineaPorId(idLinea);
		List<EstacionTren> lstEstacion = new ArrayList<EstacionTren>();
		lstEstacion.addAll(linea.getEstaciones());
		request.setAttribute("lstEstaciones", lstEstacion);
		request.getRequestDispatcher("views/listaEstacionesTren.jsp").forward(request, response);
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
					this.procesarPeticionTramosColectivo(request, response);
					break;
				case 2: //Devolver lectoras de carga
					this.procesarPeticionLectorasCarga(request, response);
					break;
				case 3: //Devolver lineas de colectivo
					this.procesarPeticionLineasColectivo(request, response);
					break;
				case 4: //Devolver lineas de subte
					this.procesarPeticionLineasSubte(request, response);
					break;
				case 5: //Devolver lineas de tren
					this.procesarPeticionLineasTren(request, response);
					break;
				case 6: //Recibe linea de colectivo, devuelve internos de esa linea y tramos
					this.procesarPeticionInternosColectivo(request, response);
					break;
				case 7: //Recibe linea de subte, devuelve estaciones de esa linea
					this.procesarPeticionEstacionesSubte(request, response);
					break;
				case 8: //Recibe linea de tren, devuelve estaciones de esa linea
					this.procesarPeticionEstacionesTren(request, response);
					break;
				case 9: //Recibe idEstacionSubte, devuelve lectoras de esa estacion
					break;
				case 10: //Recibe idEstacionTren, devuelve lectoras de esa estacion
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
}
