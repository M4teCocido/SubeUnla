package test;

import java.math.BigDecimal;
import java.util.GregorianCalendar;

import modelo.Persona;
import modelo.TarjetaSube;
import modelo.Descuentos.DescuentoTarifaSocial;
import modelo.fichadas.FichadaRecarga;
import modelo.fichadas.TransaccionSUBE;
import modelo.fichadas.tren.EstacionTren;
import modelo.fichadas.tren.FichadaTren;
import modelo.fichadas.tren.LineaTren;
import modelo.fichadas.tren.SeccionTren;
import modelo.fichadas.tren.ViajeTren;
import modelo.fichadas.tren.eTipoFichadaTren;
import modelo.lectoras.LectoraExterna;
import modelo.lectoras.LectoraTren;

public class TestSoloTren {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				Persona persona = new Persona();
				persona.asignarDescuentoTarifaSocial(new DescuentoTarifaSocial (persona));
				
				LectoraExterna lectoraExterna = new LectoraExterna (123123123, "Kiosco : El pelado Hernandez");
				
				TarjetaSube tarjeta = null;
				try {
					tarjeta = new TarjetaSube("9999",new BigDecimal (-10));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				FichadaRecarga fichadaCarga = new FichadaRecarga (new GregorianCalendar(2018, 3, 6, 13,10,00), new BigDecimal (100), lectoraExterna); 
				
				tarjeta.setPropietario(persona);
				System.out.println( tarjeta.procesarFichada(fichadaCarga).toString());
				
				
				
				//Instancia Objetos Fichada Tren======================================================================
				LineaTren roca = new LineaTren ("Roca");
				
				EstacionTren constitucionTren = new EstacionTren("Constitucion", roca); 
				EstacionTren lanusTren = new EstacionTren("Lanus", roca);
				EstacionTren aKornTren = new EstacionTren ("A. Korn", roca);
				
				LectoraTren lectoraTrenConstitucion = new LectoraTren (00001, constitucionTren,true );
				LectoraTren lectoraTrenLanus = new LectoraTren (00002, lanusTren,true );
				LectoraTren lectoraTrenAKorn = new LectoraTren (00003, aKornTren,true );
				
				
				
				
				SeccionTren primeraSeccion = new SeccionTren ("Primera Seccion", new BigDecimal(2),roca);
				roca.getSecciones().add(primeraSeccion);
				SeccionTren segundaSeccion = new SeccionTren ("Segunda Seccion", new BigDecimal(3),roca);
				roca.getSecciones().add(segundaSeccion);
				SeccionTren terceraSeccion = new SeccionTren ("Tercera Seccion", new BigDecimal(4),roca);
				roca.getSecciones().add(terceraSeccion);
				
				ViajeTren viajeA = new ViajeTren (constitucionTren,lanusTren, primeraSeccion,roca);
				roca.getViajes().add(viajeA);
				ViajeTren viajeB = new ViajeTren (constitucionTren, aKornTren, terceraSeccion,roca);
				roca.getViajes().add(viajeA);
				
				//====================================================================================================
				
				
				
				
				
				
				System.out.println("Caso de uso a");
				
				FichadaTren fichadaPruebaA = new FichadaTren (new GregorianCalendar(2018, 3, 6, 13,10,00),constitucionTren, eTipoFichadaTren.ENTRADA, lectoraTrenConstitucion);
				System.out.println(  tarjeta.procesarFichada(fichadaPruebaA).toString());
			
				
				FichadaTren fichadaPruebaB = new FichadaTren (new GregorianCalendar(2018, 3, 6, 14,10,00), lanusTren, eTipoFichadaTren.SALIDA, lectoraTrenLanus);
				System.out.println( tarjeta.procesarFichada(fichadaPruebaB).toString());
			  
			 //   FichadaTren fichadaPruebaC = new FichadaTren (new GregorianCalendar (2018, 3, 6, 18,10,00), aKornTren, eTipoFichadaTren.SALIDA, lectoraTrenAKorn);
			  //  System.out.println(  tarjeta.procesarFichada(fichadaPruebaC).toString());
			    
			 //   System.out.println("Caso de uso b)");
			    
			    
			//    FichadaTren fichadaPruebaD = new FichadaTren (new GregorianCalendar(2018, 3, 6, 19,10,00), lanusTren, eTipoFichadaTren.ENTRADA, lectoraTrenLanus);
			//    System.out.println(  tarjeta.procesarFichada(fichadaPruebaD));
			  
			//	  FichadaTren fichadaPruebaE = new FichadaTren (new GregorianCalendar(2018, 3, 6, 23,10,00), lanusTren, eTipoFichadaTren.SALIDA, lectoraTrenLanus);
			//	  System.out.println( tarjeta.procesarFichada(fichadaPruebaE));
				  
				  System.out.println("SAldo tarjeta"+tarjeta.getSaldo().toString());
	
	}

}
