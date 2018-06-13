package controladores;

import javax.servlet.http.HttpServlet;

import java.io.IOException;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Permiso;
import modelo.Usuario;
import negocio.PermisoABM;
import negocio.PersonaABM;
import negocio.UsuarioABM;

public class ControladorLogin extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		procesarPeticion(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		procesarPeticion(request, response);
	}
	
	private void procesarPeticion(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		response.setContentType( "text/html;charset=UTF-8" );
		try {
			String dni = request.getParameter("nroDocumento");
			String pass = request.getParameter("password");
			
			System.out.println("Nro Doc : " + dni + " - Pass : " + pass);
			
			UsuarioABM abm = new UsuarioABM();
			PermisoABM abmPermiso = new PermisoABM();
			PersonaABM abmPersona = new PersonaABM();
			
			abm.comprobarPassword(dni, pass);
			Usuario usuario = abm.traerUsuarioPorNombre(dni);
			Set<Permiso> permisos = usuario.getPermisos();
			if( permisos.size() == 0 || (permisos.size() == 1 && permisos.contains(abmPermiso.traerPermisoPorCodigo("CONSULTARTARJETA")))) {
				usuario.setPersona(abmPersona.traerPersona(usuario.getPersona().getIdPersona()));
				request.setAttribute("usuario", usuario);
				request.getRequestDispatcher("/miSUBE.jsp").forward(request, response );
			} else {
				request.setAttribute("permisos", permisos);
				request.getRequestDispatcher("/paneldecontrol.jsp").forward(request, response);
			}
		} catch (Exception e ) {
			e.printStackTrace();
			response.sendError(500, "El usuario o contraseña ingresados son incorrectos. Error : " + e.getMessage());
		}
	}
}
