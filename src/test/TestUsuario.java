package test;

import java.util.GregorianCalendar;

import modelo.Documento;
import modelo.Persona;
import modelo.Usuario;
import modelo.eGenero;
import modelo.eTipoDocumento;
import negocio.PersonaABM;
import negocio.UsuarioABM;

public class TestUsuario {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		UsuarioABM abmUsuario = new UsuarioABM();
		PersonaABM abmPersona = new PersonaABM();
		
		Persona persona = null;
		
		try {
			persona = new Persona("Martin", "Rios", eGenero.M, new GregorianCalendar(),
					"asd@asd.com", "1553535353", "42424242");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Usuario usuario = new Usuario("Hola", "Chau", persona);
		
		Documento doc = new Documento("37762710", eTipoDocumento.DNI, persona);
		
		
		abmPersona.agregarPersona(persona);
		abmUsuario.agregarUsuario("Martin5", "Rios", persona);
		
		
	}

}
