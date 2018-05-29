package negocio;

import dao.TarjetaSubeDao;
import java.math.BigDecimal;
import modelo.TarjetaSube;

public class TarjetaSubeABM {
	TarjetaSubeDao dao = new TarjetaSubeDao();
	
	public TarjetaSube traerTarjetaPorId(int idTarjeta)throws Exception {
		TarjetaSube t = dao.traerTarjeta(idTarjeta);
		if(t == null) throw new Exception("No existe una tarjeta con id: " + idTarjeta); 
		return t;
	}
	
	public TarjetaSube traerTarjetaPorCodigo(String codigo) throws Exception{
		TarjetaSube t = dao.traerTarjeta(codigo);
		if(t == null) throw new Exception("No existe una tarjeta con codigo: " + codigo) ;
		return t;
	}
	
	public int agregar(String codigo, BigDecimal saldo) {
		//validar si existe una tarjeta con ese codigo, si la hay tirar excepcion
		
		TarjetaSube t = new TarjetaSube(codigo, saldo);
		return dao.agregarTarjetaSube(t);
	}
	
	public void modificar(TarjetaSube t) {
		/* implementar antes de actualizar que no exista una linea
		con el mismo nombre a modificar
		y con el mismo id, lanzar la Exception */
		dao.modificarTarjetaSube(t);
	}
	
	public void eliminarPorId(int idTarjeta) {
		TarjetaSube t = dao.traerTarjeta(idTarjeta);
		//si es null arrojar exception
		dao.eliminarTarjetaSube(t);
	}
}
