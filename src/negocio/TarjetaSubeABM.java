package negocio;

import dao.TarjetaSubeDao;
import java.math.BigDecimal;
import java.util.List;

import modelo.TarjetaSube;

public class TarjetaSubeABM {
TarjetaSubeDao dao = new TarjetaSubeDao();
	
	public TarjetaSube traerTarjetaPorId(int idTarjeta)throws Exception {
		TarjetaSube t = dao.traerTarjeta(idTarjeta);
		if(t == null) throw new Exception("No existe una tarjeta con id: " + idTarjeta); 
		return t;
	}
	
	public TarjetaSube traerTarjetaPorCodigo(String codigo) throws Exception {
		TarjetaSube t = dao.traerTarjeta(codigo);
		if(t == null) throw new Exception("No existe una tarjeta con codigo: " + codigo) ;
		return t;
	}
	
	public int agregar(String codigo, BigDecimal saldo) throws Exception {
		for (TarjetaSube tarjeta : dao.traerTarjetas()) {
			if (tarjeta.getCodigo().equalsIgnoreCase(codigo)) throw new Exception("La tarjeta de codigo " + codigo + " ya existe");
		}
		TarjetaSube t = new TarjetaSube(codigo, saldo);
		return dao.agregarTarjetaSube(t);
	}
	
	public void modificar(TarjetaSube t) throws Exception {
		for (TarjetaSube tarjeta : dao.traerTarjetas()) {
			if (tarjeta.getCodigo().equalsIgnoreCase(t.getCodigo())) throw new Exception("La tarjeta de codigo " + t.getCodigo() + " ya existe");
		}
		dao.modificarTarjetaSube(t);
	}
	
	public void eliminarPorId(int idTarjeta) throws Exception {
		TarjetaSube t = dao.traerTarjeta(idTarjeta);
		if (t == null) throw new Exception("La tarjeta de id: " + idTarjeta + " no existe");
		dao.eliminarTarjetaSube(t);
	}
	
	public List<TarjetaSube> traerTarjetas() {
		return dao.traerTarjetas();
	}
}
