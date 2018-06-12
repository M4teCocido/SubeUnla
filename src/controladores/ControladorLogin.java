package controladores;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.Usuario;
import negocio.UsuarioABM;

public class ControladorLogin extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		procesarPeticion(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		procesarPeticion(request, response);
	}
	
	private void procesarPeticion(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		response .setContentType( "text/html;charset=UTF-8" );
		try {
			String dni = request.getParameter("nroDocumento");
			String pass = request.getParameter("pass");
			UsuarioABM abm = new UsuarioABM();
			abm.comprobarPassword(dni, pass);
			request.setAttribute("dni", dni);
			request.getRequestDispatcher("/panelDeControl.jsp").forward(request, response );
		}catch (Exception e ) {
			response .sendError(500, "El usuario o contraseña ingresados son incorrectos" );
		}
	}
}
