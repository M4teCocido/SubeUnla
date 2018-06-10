package negocio;

import java.util.GregorianCalendar;
import java.util.List;

import dao.fichadas.TransaccionSUBEDao;
import modelo.TarjetaSube;
import modelo.fichadas.TransaccionSUBE;

public class TransaccionABM {
	TransaccionSUBEDao transaccionDao = new TransaccionSUBEDao();
	
	
	public List<TransaccionSUBE> traerTransacciones (){
		
			return transaccionDao.traerTransacciones();
	}

	public List<TransaccionSUBE> traerTransacciones(List<GregorianCalendar> periodo){
		List <TransaccionSUBE> transacciones = transaccionDao.traerTransacciones();
		List <TransaccionSUBE> transaccionesPeriodo =null;
		for (int i=0; i< transacciones.size(); i++) {
			if(!transacciones.get(i).getFichada().getFechaHora().before(periodo.get(0)) && !transacciones.get(i).getFichada().getFechaHora().after(periodo.get(1))) {
				transaccionesPeriodo.add(transacciones.get(i));
			}		
			
		}
		
		return transaccionesPeriodo;
	}
}
