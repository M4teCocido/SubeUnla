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
import modelo.fichadas.colectivo.LineaColectivo;

public class ControladorIngresarFichada extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		procesarPeticion(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		procesarPeticion(request, response);
	}
	
	private void procesarPeticionLineasColectivo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LineaColectivoDao daoLineaColectivo = new LineaColectivoDao();
		List<LineaColectivo> lineas = daoLineaColectivo.traerLineas(); 
		request.setAttribute( "lineas" , lineas );
		request.getRequestDispatcher( "/listaLineasColectivo.jsp" ).forward( request , response );
	}
	
	private void procesarPeticion(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		response.setContentType("text/html;	charset=UTF-8");
		try {
			int nroValidacion = Integer.parseInt(request.getParameter("nroValidacion"));
			switch(nroValidacion) {
			case 1: //Devolver tramos de colectivo
				break;
			case 2: //Devolver lectoras de carga
				break;
			case 3: //Devolver lineas de colectivo
				this.procesarPeticionLineasColectivo(request, response);
				break;
			case 4: //Devolver lineas de subte
				break;
			case 5: //Devolver lineas de tren
				break;
			case 6: //Recibe linea de colectivo, devuelve internos de esa linea y tramos
				break;
			case 7: //Recibe linea de subte, devuelve estaciones de esa linea
				break;
			case 8: //Recibe linea de tren, devuelve estaciones de esa linea
				break;
			case 9: //Recibe idEstacionSubte, devuelve lectoras de esa estacion
				break;
			case 10: //Recibe idEstacionTren, devuelve lectoras de esa estacion
				break;
			default:
				break;
			}
		}catch(Exception e) {
			response.sendError(500, "El numero de tarjeta no existe en la base de datos");
		}
	}
}
