package negocio;

import java.util.List;

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
	
	public int agregar(int nroSerie ,String ubicacion) {
		//validar si existe una linea con ese nombre, si la hay tirar excepcion
		
		LectoraExterna l = new LectoraExterna(nroSerie, ubicacion);
		return dao.agregarLectora(l);
	}
	
	public void modificar(LectoraExterna l) {
		/* implementar antes de actualizar que no exista una linea
		con el mismo nombre a modificar
		y con el mismo id, lanzar la Exception */
		dao.modificarLectora(l);
	}
	
	public void eliminarLectora(int idLectora) {
		LectoraExterna l = dao.traerLectora(idLectora);
		/*si es null arrojar exception
		 * tambien habria que chequear que esta linea no tsenga ninguna dependencia 
		 * si la tienen tiramos exception o eliminamos las dependencias?*/
		dao.elimninarLectora(l);
	}
	
	public List<LectoraExterna> traerLectoras(){
		return dao.traerLectoras();
	}
	
}
