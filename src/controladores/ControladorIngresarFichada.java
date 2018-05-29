package controladores;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.HibernateException;

import dao.fichadas.colectivo.LineaColectivoDao;
import modelo.fichadas.colectivo.InternoColectivo;
import modelo.fichadas.colectivo.LineaColectivo;
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
	
	private void procesarPeticionTramosColectivo(HttpServletRequest request, HttpServletResponse response) {
		
	}
	
	private void procesarPeticionLectorasCarga(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LectorasExternasABM abm = new LectorasExternasABM();
		List<LectoraExterna> lstLectora = abm.traerLectoras();
		request.setAttribute("lstLectora", lstLectora);
		request.getRequestDispatcher("/lstLectoras.jsp").forward(request, response);
	}
	
	private void procesarPeticionLineasColectivo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LineaColectivoABM abm = new LineaColectivoABM();
		List<LineaColectivo> lstLinea = abm.traerLineas();
		//List<LineaColectivo> lineas = daoLineaColectivo.traerLineas(); 
		request.setAttribute( "lstLinea" , lstLinea );
		request.getRequestDispatcher( "/listaLineasColectivo.jsp" ).forward( request , response );
	}
	
	private void procesarPeticionLineasTren(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LineaTrenABM abm = new LineaTrenABM();
		List<LineaTren> lstLineas = abm.traerLineas();
		request.setAttribute("lstLineas", lstLineas);
		request.getRequestDispatcher("/listaLineasTren.jsp").forward(request, response);
	}
	
	private void procesarPeticionLineasSubte(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LineaSubteABM abm = new LineaSubteABM();
		List<LineaSubte> lstLineas = abm.traerLineas();
		request.setAttribute("lstLineas", lstLineas);
		request.getRequestDispatcher("/listaLineasSubte.jsp").forward(request, response);
	}
	
	private void procesarPeticionInternosColectivo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idLinea = Integer.parseInt(request.getParameter("idLinea"));
		LineaColectivoABM abm = new LineaColectivoABM();
		List<InternoColectivo> lstInterno = abm.traerInternosPorIdLinea(idLinea);
		request.setAttribute("lstInternos", lstInterno);
		request.getRequestDispatcher("/listaInternosColectivo.jsp").forward(request, response);
	}
	
	private void procesarPeticionEstacionesSubte(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idLinea = Integer.parseInt(request.getParameter("idLinea"));
		LineaSubteABM abm = new LineaSubteABM();
		List<EstacionSubte> lstEstacion = abm.traerEstacionesPorIdLinea(idLinea);
		request.setAttribute("lstEstaciones", lstEstacion);
		request.getRequestDispatcher("/listaEstacionesSubte.jsp").forward(request, response);
	}
	
	private void procesarPeticionEstacionesTren(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idLinea = Integer.parseInt(request.getParameter("idLinea"));
		LineaTrenABM abm = new LineaTrenABM();
		List<EstacionTren> lstEstacion = abm.traerEstacionesPorIdLinea(idLinea);
		request.setAttribute("lstEstaciones", lstEstacion);
		request.getRequestDispatcher("/listaEstacionesTren.jsp").forward(request, response);
	}
	
	private void procesarPeticion(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		response.setContentType("text/html;	charset=UTF-8");
		try {
			int nroValidacion = Integer.parseInt(request.getParameter("nroValidacion"));

			switch(nroValidacion) {
			case 1: //Devolver tramos de colectivo
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
		} catch(Exception e) {
			response.sendError(500, "El numero de tarjeta no existe en la base de datos");
		}
	}
}
