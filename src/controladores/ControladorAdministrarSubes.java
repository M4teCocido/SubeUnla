	package controladores;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Persona;
import modelo.TarjetaSube;
import modelo.Usuario;
import modelo.eGenero;
import negocio.PersonaABM;
import negocio.TarjetaSubeABM;
import negocio.UsuarioABM;

public class ControladorAdministrarSubes extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		procesarPeticion(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		procesarPeticion(request, response);
	}
	
	void procesarPeticionAltaTarjeta(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		TarjetaSube.Resultado resultado = null;
		UsuarioABM usuarioABM = new UsuarioABM();
		TarjetaSubeABM tarjetaSubeABM = new TarjetaSubeABM();
		Usuario usuario = new Usuario();
		try{
			 tarjetaSubeABM.agregar(request.getParameter("codigo"), new BigDecimal(request.getParameter("saldo"))); 
		} catch (Exception e) { 
			resultado = new TarjetaSube.Resultado(false, "Problema ingresar tarjeta al sistema", null);
		}
		request.setAttribute("resultado", resultado);
        System.out.println("Resultado : " + resultado);
        request.getRequestDispatcher("views/respuestaAltaTarjeta.jsp").forward(request, response);
	
	}
	
	void procesarPeticionRegistracion(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		TarjetaSube.Resultado resultado = null;
		UsuarioABM usuarioABM = new UsuarioABM();
		PersonaABM personaABM = new PersonaABM();
		TarjetaSubeABM tarjetaSubeABM = new TarjetaSubeABM();
		TarjetaSube tarjeta = null;
		Usuario usuario = null;
		eGenero genero =null;
		try {
			tarjeta = tarjetaSubeABM.traerTarjetaPorCodigo(request.getParameter("codigo"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			resultado = new TarjetaSube.Resultado(false, "Problema traer tarjeta para asociar", null);
			}
		
		   if(tarjeta != null) {
			usuario = usuarioABM.comprobarExistenciaUsuario(request.getParameter("dni"));
			
			if(usuario == null) { //Si la tarjeta es null, de entrada va al final y envia  resultado
				if(request.getParameter("genero")=="m") {// cambiar por numero que corresponda a genero
					 genero = eGenero.M  ;
				}else { genero = eGenero.F;}
				
				GregorianCalendar fechaNacimiento = parsearFecha( request);
				
				Persona persona;
				try {
					persona = new Persona (request.getParameter("nombre"),
											request.getParameter("apellido"),genero,  fechaNacimiento,  request.getParameter("email"), 
												request.getParameter("celular"), request.getParameter("celular"));
						
					personaABM.agregarPersona(persona);
					usuarioABM.agregarUsuario(request.getParameter("dni"), request.getParameter("password"), persona);
					persona.asociarTarjeta(tarjeta);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					resultado = new TarjetaSube.Resultado(false, "Problem para generar usuario y asignar tarjeta", null);
				} 
				
				
			} else
				try {
					usuario.getPersona().asociarTarjeta(tarjeta);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					resultado = new TarjetaSube.Resultado(false, "Problema asignar tarjeta a persona existente", null);
				}
		   }
			
			request.setAttribute("resultado", resultado);
		        System.out.println("Resultado : " + resultado);
		        request.getRequestDispatcher("views/respuestaRegistracion.jsp").forward(request, response);
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
				case 1: //Procesar alta de  tarjeta sube en el sistema(todavia no  vinculada a  usaurio )
					this.procesarPeticionAltaTarjeta(request, response);
					break;
				case 2: //Procesar alta de  usuario en el sistema (Posiblmente  inecesario)
					this.procesarPeticionRegistracion(request, response);
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
	private GregorianCalendar parsearFecha(HttpServletRequest request) {
		
		
    	int anio = Integer.parseInt(request.getParameter("anio"));
    	int mes  = Integer.parseInt(request.getParameter("mes"));
    	int dia  = Integer.parseInt(request.getParameter("dia"));
    	int hora = Integer.parseInt(request.getParameter("hora"));
    	int min  = Integer.parseInt(request.getParameter("min"));
    	
    	
    	GregorianCalendar fecha = new GregorianCalendar  (anio, mes - 1, dia, hora , min, 0);
    	
    	return  fecha;
	}
}
