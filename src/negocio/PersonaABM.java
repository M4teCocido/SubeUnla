package negocio;

import java.util.List;

import dao.PersonaDao;
import modelo.Persona;

public class PersonaABM {
	PersonaDao dao = new PersonaDao();
	
	public int agregarPersona(Persona persona) throws Exception {
		for (Persona personaAux : dao.traerPersonas()) {
			if (personaAux.equals(persona)) throw new Exception(persona + " ya existe");
		}
		return dao.agregarPersona(persona);
	}
	
	public void modificarPersona(Persona persona) throws Exception {
		for (Persona personaAux : dao.traerPersonas()) {
			if (personaAux.equals(persona)) throw new Exception(persona + " ya existe");
		}
		dao.modificarPersona(persona);
	}
	
	public void eliminarPersona(Persona persona) throws Exception {
		if (dao.traerPersona(persona.getIdPersona()) == null) throw new Exception("La persona solicitada no existe");
		dao.eliminarPersona(persona);
	}
	
	public Persona traerPersona(int idPersona) throws Exception {
		Persona persona = dao.traerPersona(idPersona);
		if (persona == null) throw new Exception("La persona de id " + idPersona + " no existe");
		return persona;
	}
	
	public Persona traerPersona(String documento) throws Exception {
		Persona persona = dao.traerPersona(documento);
		if (persona == null) throw new Exception("La persona de documento " + documento + " no existe");
		return persona;
	}
	
	public List<Persona> traerPersonas() throws Exception {
		return dao.traerPersonas();
	}
}
