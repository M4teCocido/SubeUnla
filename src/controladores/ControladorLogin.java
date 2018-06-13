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
			String pass = request.getParameter("pass");
			
			UsuarioABM abm = new UsuarioABM();
			PermisoABM abmPermiso = new PermisoABM();
			
			abm.comprobarPassword(dni, pass);
			Usuario usuario = abm.traerUsuarioPorNombre(dni);
			Set<Permiso> permisos = usuario.getPermisos();
			request.setAttribute("usuario", usuario);
			if(permisos.size() == 1 && permisos.contains(abmPermiso.traerPermisoPorCodigo("CONSULTARTARJETA")) ) {
				request.getRequestDispatcher("/misube.jsp").forward(request, response );
			} else {
				request.setAttribute("permisos", permisos);
				request.getRequestDispatcher("/paneldecontrol.jsp").forward(request, response );
			}
		} catch (Exception e ) {
			response .sendError(500, "El usuario o contraseña ingresados son incorrectos" );
		}
	}
}
