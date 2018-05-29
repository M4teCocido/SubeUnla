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
				
			}
			//Devolver lineas de colectivo
			if(nroValidacion == 3) {
				
			}
			//Devolver lineas de subte
			if(nroValidacion == 4) {
				
			}
			//Devolver lineas de tren
			if(nroValidacion == 5) {
				
			}
			//Recibe linea de colectivo, devuelve internos de esa linea y tramos
			if(nroValidacion == 6) {
				
			}
			//Recibe linea de subte, devuelve estaciones de esa linea
			if(nroValidacion == 7) {
				
			}
			//Recibe linea de tren, devuelve estaciones de esa linea
			if(nroValidacion == 8) {
				
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
