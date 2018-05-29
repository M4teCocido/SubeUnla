package test;

import java.math.BigDecimal;
import java.util.GregorianCalendar;

import modelo.Persona;
import modelo.TarjetaSube;
import modelo.Descuentos.DescuentoTarifaSocial;
import modelo.fichadas.FichadaRecarga;
import modelo.fichadas.colectivo.FichadaColectivo;
import modelo.fichadas.colectivo.InternoColectivo;
import modelo.fichadas.colectivo.LineaColectivo;
import modelo.fichadas.colectivo.TramoColectivo;
import modelo.lectoras.LectoraColectivo;
import modelo.lectoras.LectoraExterna;

public class TestSoloColectivo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Persona persona = new Persona();
		persona.asignarDescuentoTarifaSocial(new DescuentoTarifaSocial (persona));
		
		LectoraExterna lectoraExterna = new LectoraExterna (123123123, "Kiosco : El pelado Hernandez");
		
		TarjetaSube tarjeta = new TarjetaSube("9999",new BigDecimal (-10));
		FichadaRecarga fichadaCarga = new FichadaRecarga (new GregorianCalendar(2018, 3, 6, 13,10,00), new BigDecimal (100), lectoraExterna); 
		
		tarjeta.setPropietario(persona);
		System.out.println( tarjeta.procesarFichada(fichadaCarga).toString());
		
		LineaColectivo l165 = new LineaColectivo ("165");
		LectoraColectivo lectoraColectivoA = new LectoraColectivo ();
		
		InternoColectivo internoA = new InternoColectivo (l165, "internoA");
		internoA.setLectora(lectoraColectivoA);
		internoA.getLectora().setInterno(internoA);
		TramoColectivo tramoColectivoA = new TramoColectivo("Tramo A",new BigDecimal (9), l165);
		
		FichadaColectivo fichadaAColectivo = new FichadaColectivo (new GregorianCalendar(2018, 3, 6, 13,10,00),tramoColectivoA, lectoraColectivoA);
		System.out.println("Saldo tarjeta antes de viaje" + tarjeta.getSaldo().toString());
		System.out.println(tarjeta.procesarFichada(fichadaAColectivo).toString());
		System.out.println("Saldo tarjeta despues viaje" + tarjeta.getSaldo().toString());

	}

}
