package controladores;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.HibernateException;

public class ControladorIngresarFichada extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		procesarPeticion(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		procesarPeticion(request, response);
	}
	
	private void procesarPeticion(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		response.setContentType("text/html;	charset=UTF-8");
		try {
			int nroValidacion = Integer.parseInt(request.getParameter("nroValidacion"));
			//Devolver tramos de colectivo
			if(nroValidacion == 1){
				
			}
			//Devolver lectoras de carga
			if(nroValidacion == 2){
				LectoraExternaABM abm = new LectoraExternaABM();
				List<LectoraExterna> lstLectora = abm.traerLectoras();
				request.setAttribute('lstLectora', lstLectora);
				request.getRequestDispatcher("/lstLectoras.jsp").forward(request, response);
				
			}
			//Devolver lineas de colectivo
			if(nroValidacion == 3) {
				LineaColectivoABM abm = new LineaColectivoABM();
				List<LineaColectivo> lstLinea = abm.traerLineas();
				request.setAttribute('lstLineas', lstLineas);
				request.getRequestDispatcher('/lstLineasColectivo.jsp').forward(request, response);
			}
			//Devolver lineas de subte
			if(nroValidacion == 4) {
				LineaSubteABM abm = new LineaSubteABM();
				List<LineaSubte> lstSubte = abm.traerLineas();
				request.setAttribute('lstLineas', lstLineas);
				request.getRequestDispatcher('/lstLineasSubte.jsp').forward(request, response);
			}
			//Devolver lineas de tren
			if(nroValidacion == 5) {
				LineaTrenABM abm = new LineaTrenABM();
				List<LineaTren> lstTren = abm.traerLineas();
				request.setAttribute('lstTren', lstTren);
				request.getRequestDispatcher('/lstLineaTren.jsp').forward(request, response);
			}
			//Recibe linea de colectivo, devuelve internos de esa linea
			if(nroValidacion == 6) {
				int idLinea = Integer.parseInt(request.getParameter('idLinea'));
				LineaColectivoABM abm = new LineaColectivoABM();
				List<InternoColectivo> lstInterno = abm.traerInternosPorIdLinea(idLinea);
				request.setAttribute('lstInterno', lstInterno);
				request.getRequestDispatcher('/lstInternoColectivo.jsp').forward(request, response);
			}
			//Recibe linea de subte, devuelve estaciones de esa linea
			if(nroValidacion == 7) {
				int idLinea = Integer.parseInt(request.getParameter('idLinea'))
				LineaSubteABM abm = new LineaSubteABM();
				List<EstacionSubte> lstEstacion = abm.traerEstacionesPorIdLinea(idLinea);
				request.setAttribute('lstEstacion', lstEstacion);
				request.getRequestDispatcher('/lstEstacionSubte.jsp').forward(request, response);
			}
			//Recibe linea de tren, devuelve estaciones de esa linea
			if(nroValidacion == 8) {
				int idLinea = Integer.parseInt(request.getParameter('idLinea'))
				LineaTrenABM abm = new LineaTrenABM();
				List<EstacionTren> lstEstacion = abm.traerEstacionesPorIdLinea(idLinea);
				request.setAttribute('lstEstacion', lstEstacion);
				request.getRequestDispatcher('/lstEstacionTren.jsp').forward(request, response); 
			}
			//Recibe idEstacionSubte, devuelve lectoras de esa estacion
			if(nroValidacion == 9) {
				
			}
			//Recibe idEstacionTren, devuelve lectoras de esa estacion
			if(nroValidacion == 10) {
				
			}
		}catch(Exception e) {
			response.sendError(500, "El numero de tarjeta no existe en la base de datos");
		}
	}
}
