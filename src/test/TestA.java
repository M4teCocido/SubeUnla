package test;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import java.util.Set;

import modelo.*;
import modelo.Descuentos.DescuentoTarifaSocial;
import modelo.fichadas.Fichada;
import modelo.fichadas.TransaccionSUBE;
import modelo.fichadas.colectivo.FichadaColectivo;
import modelo.fichadas.colectivo.InternoColectivo;
import modelo.fichadas.colectivo.LineaColectivo;
import modelo.fichadas.colectivo.TramoColectivo;
import modelo.fichadas.subte.EstacionSubte;
import modelo.fichadas.subte.FichadaSubte;
import modelo.fichadas.subte.LineaSubte;
import modelo.fichadas.tren.EstacionTren;
import modelo.fichadas.tren.FichadaTren;
import modelo.fichadas.tren.LineaTren;

import modelo.fichadas.tren.SeccionTren;
import modelo.fichadas.tren.ViajeTren;
import modelo.fichadas.tren.eTipoFichadaTren;
import util.FuncionesGregorian;

public class TestA {

	public static void main(String[] args) {
		Persona persona = new Persona();
		persona.asignarDescuentoTarifaSocial(new DescuentoTarifaSocial ());
		
		TarjetaSube tarjeta = new TarjetaSube("9999",new BigDecimal (200));
		//tarjeta.setSaldo(new BigDecimal (21));
		tarjeta.setPropietario(persona);
		

	
	
		
		
		
		
		
		
		//Instancio Fichada tren-----------------------------------------------------------------------------------------
		
	
		  
	    
	    for (TransaccionSUBE t : tarjeta.getTransacciones()) {
	    	System.out.println(t.toString());
	    }
	    
	    
	    
	
		
	}

	
	
}
