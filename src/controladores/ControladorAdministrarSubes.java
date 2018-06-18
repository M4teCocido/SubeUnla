	package controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.GregorianCalendar;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TarjetaSubeDao;
import dao.descuentos.DescuentoRedSubeDao;
import modelo.Documento;
import modelo.Persona;
import modelo.TarjetaSube;
import modelo.Usuario;
import modelo.eGenero;
import modelo.eTipoDocumento;
import modelo.Descuentos.DescuentoBoletoEstudiantil;
import modelo.Descuentos.DescuentoRedSube;
import modelo.Descuentos.DescuentoTarifaSocial;
import modelo.Descuentos.eTipoBoletoEstudiantil;
import negocio.PersonaABM;
import negocio.TarjetaSubeABM;
import negocio.TransaccionABM;
import negocio.UsuarioABM;

public class ControladorAdministrarSubes extends HttpServlet {

	private final int F = 1;
	private final int M = 2;
	
	private final int DNI = 1;
	private final int LE = 2;
	
	private final int ESCOLAR = 1;
	private final int UNIVERSITARIO = 2;
	
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
		
		String codigo = request.getParameter("nroTarjeta");
		
		try {
		
			tarjeta = tarjetaSubeABM.traerTarjetaPorCodigo(codigo);
			
			if (tarjeta == null) //Si la tarjeta no existe, devolvemos error.
				resultado = new TarjetaSube.Resultado(false, "No existe la tarjeta con el codigo ingresado", null);
			else { //Si existe, procedemos a chequear usuario y persona.
				//Validar si la tarjeta ya esta asociada
				if (tarjeta.getPropietario() == null) { //No esta asociada
					String nroDoc = request.getParameter("nroDocumento");
					
					usuario = usuarioABM.comprobarExistenciaUsuario(nroDoc);
					
					if(usuario == null) { //Si el usuario es Null, lo creamos.
						
	
						
						String nombre = request.getParameter("nombre");
						String apellido = request.getParameter("apellido");
						String email = request.getParameter("email");
						String telefono = request.getParameter("telefono");
						String celular = request.getParameter("celular");
						String password = request.getParameter("password");
						
						int tipoBoletoEst = Integer.parseInt(request.getParameter("descEstudiantil"));
						boolean tieneTarifaSocial = (Integer.parseInt(request.getParameter("tarifaSocial")) == 1); 
						eTipoDocumento tipoDoc = (Integer.parseInt(request.getParameter("tipoDoc")) == DNI 
								? eTipoDocumento.DNI
								: eTipoDocumento.LIBRETA_ENROLAMIENTO);
						GregorianCalendar fechaNacimiento = parsearFecha(request);
						eGenero genero = (Integer.parseInt(request.getParameter("genero")) == F 
								? eGenero.F
								: eGenero.M);
	
						
						
						
						Persona persona;
						
						//Creamos la nueva Persona
						persona = new Persona (nombre, apellido, genero, 
								fechaNacimiento,  email, celular, telefono);
						//Asignamos Descuentos si corresponde
						//Boleto Estudiantil
						if (tipoBoletoEst == ESCOLAR) {
							persona.asignarDescuentoBoletoEstudiantil(
									new DescuentoBoletoEstudiantil(eTipoBoletoEstudiantil.ESCOLAR, persona));
						} else if (tipoBoletoEst == UNIVERSITARIO) {
							persona.asignarDescuentoBoletoEstudiantil(
									new DescuentoBoletoEstudiantil(eTipoBoletoEstudiantil.UNIVERSITARIO, persona));
						}
						//Tarifa Sociales
						if (tieneTarifaSocial) {
							persona.asignarDescuentoTarifaSocial(new DescuentoTarifaSocial(persona));
						}
						
						//Creamos y asociamos documento
						persona.setDocumento(new Documento(nroDoc, tipoDoc, persona));
						//Asociamos Tarjeta a Persona
						persona.asociarTarjeta(tarjeta);
						//Persistimos persona
						personaABM.agregarPersona(persona);
						//Persistimos la Tarjeta
						tarjetaSubeABM.modificar(tarjeta);
						//Creamos el usuario nuevo con la nueva persona y lo persistimos
						usuarioABM.agregarUsuario(nroDoc, password, persona);
						resultado = new TarjetaSube.Resultado(true, "Tarjeta asociada con exito. Se creo un nuevo usuario para usted. Las credenciales corresponden a su numero de Documento", null);
					} else { //Usuario existe, simplement le asociamos una tarjeta a su Persona
						boolean tarjetaActiva = false;
						Set <TarjetaSube> tarjetasAsociadas=usuario.getPersona().getTarjetasAsociadas();
						for(TarjetaSube i : tarjetasAsociadas) {
							if(i.isActiva()==true) {
								tarjetaActiva = true;
							}
						}
						
						
						if (tarjetaActiva != true) {
						
						try {
							usuario.getPersona().asociarTarjeta(tarjeta);
							personaABM.modificarPersona(usuario.getPersona());
							tarjetaSubeABM.modificar(tarjeta);
							resultado = new TarjetaSube.Resultado(true, "Tarjeta asociada a usuario '" + usuario.getNombreUsuario() + "' con exito.", null);
							
						
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							resultado = new TarjetaSube.Resultado(false, "Problema asignar tarjeta a persona existente. Error : " + e.getMessage(), null);
						}
						}else {//Ya tiene tarjeta activa
							resultado = new TarjetaSube.Resultado(false, "La  persona ingresada ya esta asociada a una tarjeta activa !", null);}
					}
				} else { //Ya esta asociada a una persona.
					resultado = new TarjetaSube.Resultado(false, "La tarjeta ingresada ya esta asociada a una persona!", null);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			resultado = new TarjetaSube.Resultado(false, "Alguno de los datos ingresados es incorrecto. Error : " + e.getMessage(), null);
		}
		request.setAttribute("resultado", resultado);
        System.out.println("Resultado : " + resultado);
        request.getRequestDispatcher("views/respuestaABMSube.jsp").forward(request, response);
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
		if (nroTarjeta.length() != 16) {
			resultado = new TarjetaSube.Resultado(false, "El codigo debe ser de 16 digitos", null);
		} else {
			try{
				 tarjetaSubeABM.agregar(nroTarjeta, saldo); 
				 resultado = new TarjetaSube.Resultado(true, "Tarjeta dada de alta con exito!", null);
			} catch (Exception e) { 
				resultado = new TarjetaSube.Resultado(false, "Problema ingresar tarjeta al sistema. Error : " + e.getMessage(), null);
				e.printStackTrace();
			}
			request.setAttribute("resultado", resultado);
			System.out.println("Resultado : " + resultado);
			request.getRequestDispatcher("views/respuestaABMSube.jsp").forward(request, response);
		}
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
				resultado = new TarjetaSube.Resultado(false, "Problema al guardar la tarjeta modificada. Error : " + e.getMessage(), null);
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
				resultado = new TarjetaSube.Resultado(false, "Problema al dar de baja la tarjeta. Error : " + e.getMessage(), null);
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
				response.getWriter().println("$ " + tarjeta.getSaldo());
			else
				response.getWriter().println("$ " + -666); //No esta activa
		}
	}
	
	private void procesarPeticionTerminalAutonoma(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter salida = response.getWriter();
		TarjetaSube tarjeta = this.obtenerTarjetaDesdeRequest(request);
		PersonaABM abm = new PersonaABM();
		try {
			Persona persona = abm.traerPersona(tarjeta.getPropietario().getIdPersona());
			if(tarjeta != null) {
				if (tarjeta.isActiva()) {
					salida.println("Saldo: $ " + tarjeta.getSaldo());
					if(persona != null) {
						DescuentoBoletoEstudiantil descuentoEstudiantil = persona.getDescuentoBoletoEstudiantil();
						DescuentoTarifaSocial descuentoSocial = persona.getDescuentoTarifaSocial();
						
						if(descuentoEstudiantil != null) {
							salida.println("<p>Boleto Estudiantil (Viajes Restantes): " + descuentoEstudiantil.getViajesRestantes() + "</p>");
						}else {
							salida.println("<p>Usted no posee un Boleto Estudiantil activo!</p>");
						}
						
						if(descuentoSocial != null) {
							salida.println("<p>El descuento asignado por su tarifa social es de: " + descuentoSocial.getPorcentajeDescuento().multiply(new BigDecimal(100)).intValue() + "%</p>") ;
						}else {
							salida.println("<p>Usted no posee un descuento por tarifa social.</p>");
						}
					}
				}else {
					response.getWriter().println("Saldo: $ " + -666 + ". La Tarjeta ingresada no esta activa."); //No esta activa
				}
			}
		}catch(Exception e) {
			System.out.println("Excepction agarrada : " + e.getMessage());
			e.printStackTrace();
			if (!response.isCommitted())
				response.sendError(500, e.getMessage());
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
				case 11:
					procesarPeticionTerminalAutonoma(request, response);
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
    	
    	//int hora = Integer.parseInt(request.getParameter("hora"));
    	//int min  = Integer.parseInt(request.getParameter("min"));
    	GregorianCalendar fecha = new GregorianCalendar  (anio, mes - 1, dia);
    	
    	return  fecha;
	}
}
