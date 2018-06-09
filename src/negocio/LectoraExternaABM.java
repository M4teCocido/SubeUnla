package negocio;

import java.util.List;

import dao.lectoras.LectoraExternaDao;
import modelo.TarjetaSube;
import modelo.fichadas.colectivo.LineaColectivo;
import modelo.lectoras.LectoraExterna;

public class LectoraExternaABM {
	LectoraExternaDao dao = new LectoraExternaDao();
	
	public LectoraExterna traerLectoraPorId(int idLectora)throws Exception {
		LectoraExterna lectora = dao.traerLectora(idLectora);
		if(lectora == null) throw new Exception("No existe una lectora con id: " + idLectora); 
		return lectora;
	}
	
	public int agregar(int nroSerie ,String ubicacion) throws Exception {
		for (LectoraExterna lectora : dao.traerLectoras()) {
			if (lectora.getNroSerie() == nroSerie && lectora.getUbicacion().equalsIgnoreCase(ubicacion)) throw new Exception("La lectora de nro: " + nroSerie + " y ubicacion: " + ubicacion + " ya existe");
		}
		LectoraExterna l = new LectoraExterna(nroSerie, ubicacion);
		return dao.agregarLectora(l);
	}
	
	public void modificar(LectoraExterna l) throws Exception {
		for (LectoraExterna lectora : dao.traerLectoras()) {
			if (lectora.equals(l)) throw new Exception("");
		}
		dao.modificarLectora(l);
	}
	
	public void eliminarLectora(int idLectora) throws Exception {
		LectoraExterna l = dao.traerLectora(idLectora);
		if (l == null) throw new Exception("La lectora de id: " + idLectora + " no existe");
		dao.elimninarLectora(l);
	}
	
	public List<LectoraExterna> traerLectoras(){
		return dao.traerLectoras();
	}
}
