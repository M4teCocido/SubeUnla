package negocio;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import dao.TarjetaSubeDao;
import dao.fichadas.TransaccionSUBEDao;
import modelo.TarjetaSube;
import modelo.fichadas.TransaccionSUBE;

public class TransaccionABM {
	TransaccionSUBEDao transaccionDao = new TransaccionSUBEDao();
	TarjetaSubeDao tarjetaDao = new TarjetaSubeDao();
	
	public List<TransaccionSUBE> traerTransacciones (){
		
			return transaccionDao.traerTransacciones();
	}
	
	public List<TransaccionSUBE> traerViajes(List<GregorianCalendar> periodo){
		
		List <TarjetaSube> tarjetas = tarjetaDao.traerTarjetas();
		List <TransaccionSUBE> viajes = new ArrayList<TransaccionSUBE>();
		for (TarjetaSube tarjeta : tarjetas) {
			for (TransaccionSUBE t : tarjeta.getTransacciones()) {
				if(!t.getFichada().getFechaHora().before(periodo.get(0)) && 
						!t.getFichada().getFechaHora().after(periodo.get(1)) &&
						t.getFichada().esViaje()) {
					viajes.add(t);
				}
			}
		}
		
		return viajes;
	}
	
	/*
	public List<TransaccionSUBE> traerViajes(List<GregorianCalendar> periodo, int idEstacion){
	
		List<TransaccionSUBE> viajes = this.traerViajes(periodo);
		List<TransaccionSUBE> viajesFiltrados = new ArrayList<TransaccionSUBE>();
		for (TransaccionSUBE viaje : viajes) {
			if (viaje.)
		}
	
	}*/
	/*
	public List<TransaccionSUBE> traerViajes(List<GregorianCalendar> periodo){
		List <TransaccionSUBE> transacciones = transaccionDao.traerTransacciones();
		//List <TransaccionSUBE> transaccionesPeriodo = null;
		List <TransaccionSUBE> transaccionesPeriodo = new ArrayList<TransaccionSUBE>();
		TransaccionSUBE t;
		for (int i=0; i< transacciones.size(); i++) {
			t = transacciones.get(i);
			if(!t.getFichada().getFechaHora().before(periodo.get(0)) && !t.getFichada().getFechaHora().after(periodo.get(1))) {
				if (t.getFichada().esViaje())
					transaccionesPeriodo.add(t);
			}		
			
		}
		
		return transaccionesPeriodo;
	}*/
	
	public List<TransaccionSUBE> traerTransacciones(List<GregorianCalendar> periodo){
		List <TransaccionSUBE> transacciones = transaccionDao.traerTransacciones();
		//List <TransaccionSUBE> transaccionesPeriodo = null;
		List <TransaccionSUBE> transaccionesPeriodo = new ArrayList<TransaccionSUBE>();
		for (int i=0; i< transacciones.size(); i++) {
			if(!transacciones.get(i).getFichada().getFechaHora().before(periodo.get(0)) && !transacciones.get(i).getFichada().getFechaHora().after(periodo.get(1))) {
				transaccionesPeriodo.add(transacciones.get(i));
			}		
			
		}
		
		return transaccionesPeriodo;
	}
}
