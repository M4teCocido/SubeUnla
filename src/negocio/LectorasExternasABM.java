package negocio;

import dao.lectoras.LectoraExternaDao;
import modelo.TarjetaSube;
import modelo.lectoras.LectoraExterna;

public class LectorasExternasABM {
	LectoraExternaDao dao = new LectoraExternaDao();
	
	public LectoraExterna traerLectoraPorId(int idLectora)throws Exception {
		LectoraExterna lectora = dao.traerLectora(idLectora);
		if(lectora == null) throw new Exception("No existe una lectora con id: " + idLectora); 
		return lectora;
	}
	
	
}
