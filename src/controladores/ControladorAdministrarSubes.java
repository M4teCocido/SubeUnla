	package controladores;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TarjetaSubeDao;
import dao.descuentos.DescuentoRedSubeDao;
import modelo.Persona;
import modelo.TarjetaSube;
import modelo.Usuario;
import modelo.eGenero;
import modelo.Descuentos.DescuentoRedSube;
import negocio.PersonaABM;
import negocio.TarjetaSubeABM;
import negocio.TransaccionABM;
import negocio.UsuarioABM;

public class ControladorAdministrarSubes extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		procesarPeticion(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		procesarPeticion(request, response);
	}
	
	void procesarPeticionRegistracion(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		TarjetaSube.Resultado resultado = null;
		UsuarioABM usuarioABM = new UsuarioABM();
		PersonaABM personaABM = new PersonaABM();
		TarjetaSubeABM tarjetaSubeABM = new TarjetaSubeABM();
		TarjetaSube tarjeta = null;
		Usuario usuario = null;
		eGenero genero =null;
		String codigo = request.getParameter("codigo");
		try {
			tarjeta = tarjetaSubeABM.traerTarjetaPorCodigo(codigo);
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
	
	private void procesarAltaModificacionTarjeta(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		BigDecimal saldo = new BigDecimal(request.getParameter("saldo"));
		String nroTarjeta = request.getParameter("nroTarjeta");
		TarjetaSube tarjeta = this.obtenerTarjetaDesdeRequest(request);
		if (tarjeta != null) //Modificamos
			this.procesarPeticionModifTarjeta(tarjeta, saldo, request, response);
		else //Damos de Alta
			procesarPeticionAltaTarjeta(nroTarjeta, saldo, request, response);
	}	
	
	void procesarPeticionAltaTarjeta(String nroTarjeta, BigDecimal saldo, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TarjetaSube.Resultado resultado = null;
		TarjetaSubeABM tarjetaSubeABM = new TarjetaSubeABM();
		
		try{
			 tarjetaSubeABM.agregar(nroTarjeta, saldo); 
			 resultado = new TarjetaSube.Resultado(true, "Tarjeta dada de alta con exito!", null);
		} catch (Exception e) { 
			resultado = new TarjetaSube.Resultado(false, "Problema ingresar tarjeta al sistema", null);
			e.printStackTrace();
		}
		request.setAttribute("resultado", resultado);
		System.out.println("Resultado : " + resultado);
		request.getRequestDispatcher("views/respuestaABMSube.jsp").forward(request, response);
	}
	
	void procesarPeticionModifTarjeta(TarjetaSube tarjeta, BigDecimal saldo, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TarjetaSubeABM tarjetaSubeABM = new TarjetaSubeABM();
		TarjetaSube.Resultado resultado = null;
		if (tarjeta != null) {
			try {
				if (tarjeta.isActiva()) {
					tarjeta.setSaldo(saldo);
					tarjetaSubeABM.modificar(tarjeta);
					resultado = new TarjetaSube.Resultado(true, "Tarjeta modificada con exito!", null);
				} else {
					resultado = new TarjetaSube.Resultado(false, "Esa tarjeta esta dada de baja!", null);
				}
			} catch (Exception e) {
				resultado = new TarjetaSube.Resultado(false, "Problema al guardar la tarjeta modificada", null);
				e.printStackTrace();
			}
		}
		request.setAttribute("resultado", resultado);
		System.out.println("Resultado : " + resultado);
		request.getRequestDispatcher("views/respuestaABMSube.jsp").forward(request, response);
	}

	void procesarPeticionBaja(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TarjetaSubeABM tarjetaSubeABM = new TarjetaSubeABM();
		TarjetaSube tarjeta = null;
		TarjetaSube.Resultado resultado = null;
		try {
			tarjeta = tarjetaSubeABM.traerTarjetaPorCodigo(request.getParameter("nroTarjeta"));
		} catch (Exception e) {
			resultado = new TarjetaSube.Resultado(false, "Problema al traer tarjeta para dar de baja", null);
		}
		if (tarjeta != null) {
			try {
				if (tarjeta.isActiva()) {
					tarjeta.setActiva(false);
					tarjetaSubeABM.modificar(tarjeta);
					resultado = new TarjetaSube.Resultado(true, "Tarjeta dada de baja con exito!", null);
				} else {
					resultado = new TarjetaSube.Resultado(false, "Esa tarjeta ya esta dada de baja!", null);
				}
			} catch (Exception e) {
				resultado = new TarjetaSube.Resultado(false, "Problema al dar de baja la tarjeta", null);
			}
		}
		request.setAttribute("resultado", resultado);
		System.out.println("Resultado : " + resultado);
		request.getRequestDispatcher("views/respuestaABMSube.jsp").forward(request, response);
	}
	
	private void procesarPeticionSaldoTarjeta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TarjetaSube tarjeta = this.obtenerTarjetaDesdeRequest(request); 
		if(tarjeta == null) {
			response.getWriter().println(0);
		} else {
			if (tarjeta.isActiva())
				response.getWriter().println(tarjeta.getSaldo());
			else 
				response.getWriter().println(-666); //No esta activa
		}
	}
	
	private TarjetaSube obtenerTarjetaDesdeRequest(HttpServletRequest request) {
		String codigo = request.getParameter("nroTarjeta");

		System.out.println("Codigo : " + codigo);
		return obtenerTarjetaPorCodigo(codigo);
	}
	
	private TarjetaSube obtenerTarjetaPorCodigo(String codigo) {
		TarjetaSube tarjeta = null;
		if (codigo != "") {
			TarjetaSubeDao dao = new TarjetaSubeDao();
			tarjeta = dao.traerTarjeta(codigo);
			if (tarjeta != null) {
				DescuentoRedSubeDao daoDesc = new DescuentoRedSubeDao ();
				DescuentoRedSube desc = tarjeta.getDescuentoRedSube();
				desc = daoDesc.traerDescuento(desc.getIdDescuento());
				System.out.println("Descuento : " + desc);
				tarjeta.setDescuentoRedSube(desc);
				desc.setTarjeta(tarjeta);
				//tarjeta.getDescuentoRedSube().setLapsoDescuentoRedSube(daoLapso.traerLapsoPorDescuento(tarjeta.getDescuentoRedSube().getIdDescuento()));
			}
		}
		return tarjeta;
	}
	
	private void procesarPeticion(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		response.setContentType("text/html;	charset=UTF-8");
		try {
			String strNroValidacion= request.getParameter("nroValidacion");
			if (strNroValidacion == null)
				request.getRequestDispatcher("/AdministracionSUBE.jsp").forward(request, response);
			else {
				int nroValidacion = Integer.parseInt(strNroValidacion);
				System.out.println("Numero : " + nroValidacion);
				switch(nroValidacion) {
				case 1:
					this.procesarAltaModificacionTarjeta(request, response);
					break;
				/*
				case 1: //Procesar alta de  tarjeta sube en el sistema(todavia no  vinculada a  usaurio )
					this.procesarPeticionAltaTarjeta(request, response);
					break;*/
				case 2: //Procesar alta de  usuario en el sistema (Posiblmente  inecesario)
					this.procesarPeticionRegistracion(request, response);
					break;
				/*case 3: //modificacion, levantar tarjeta, buscar, cambiar y guardar
					procesarPeticionModificacion(request, response);
					break;*/
				case 3: //eliminar tarjeta logicamente, modificar atributo activa = false
					procesarPeticionBaja(request, response);
					break;
				case 10: //Traer saldo Tarjeta ingresada
					procesarPeticionSaldoTarjeta(request, response);
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
