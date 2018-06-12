package test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import modelo.TarjetaSube;
import modelo.TarjetaSube.Resultado;
import modelo.fichadas.FichadaRecarga;
import modelo.fichadas.TransaccionSUBE;
import modelo.fichadas.tren.EstacionTren;
import modelo.fichadas.tren.FichadaTren;
import modelo.fichadas.tren.LineaTren;
import modelo.fichadas.tren.SeccionTren;
import modelo.fichadas.tren.eTipoFichadaTren;
import modelo.lectoras.LectoraExterna;
import modelo.lectoras.LectoraTren;

public class TestTarjetaSubeTren {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Se crea una tarjeta nueva con saldo 0
		BigDecimal saldo = new BigDecimal(0);
		TarjetaSube tarjeta = null;
		try {
			tarjeta = new TarjetaSube("123456", saldo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Se le realiza una recarga y chequeamos que se haya cargado correctamente
		GregorianCalendar fechaRecarga = new GregorianCalendar(2018, 5, 28, 10, 0, 0);
		BigDecimal montoCarga = new BigDecimal(100);
		LectoraExterna lectoraRecarga = new LectoraExterna(1, "Kiosco");
		FichadaRecarga cargaSaldo = new FichadaRecarga(fechaRecarga, montoCarga, lectoraRecarga);
		
		System.out.println(tarjeta.procesarFichada(cargaSaldo));
		
		System.out.println("Su saldo es: " + tarjeta.getSaldo());
		
		//Cargamos la linea de tren, la lista de estaciones de tren y chequeamos que te guarden bien
		List<EstacionTren> listaEstacionesTren = new ArrayList<EstacionTren>();
		
		LineaTren lineaTren = new LineaTren("Roca");
		
		listaEstacionesTren.add(new EstacionTren("Alejandro Korn", lineaTren)); //0
		listaEstacionesTren.add(new EstacionTren("Guernica", lineaTren)); //1
		listaEstacionesTren.add(new EstacionTren("Glew", lineaTren)); //2
		listaEstacionesTren.add(new EstacionTren("Longchamps", lineaTren)); //3
		listaEstacionesTren.add(new EstacionTren("Burzaco", lineaTren)); //4
		listaEstacionesTren.add(new EstacionTren("Adrogue", lineaTren)); //5
		listaEstacionesTren.add(new EstacionTren("Temperley", lineaTren)); //6
		listaEstacionesTren.add(new EstacionTren("Lomas de Zamora", lineaTren)); //7
		listaEstacionesTren.add(new EstacionTren("Banfield", lineaTren)); //8
		listaEstacionesTren.add(new EstacionTren("Remedios de Escalada", lineaTren)); //9
		listaEstacionesTren.add(new EstacionTren("Lanus", lineaTren)); //10
		listaEstacionesTren.add(new EstacionTren("Gerli", lineaTren)); //11
		listaEstacionesTren.add(new EstacionTren("Avellaneda", lineaTren)); //12
		listaEstacionesTren.add(new EstacionTren("Yrigoyen", lineaTren)); //13
		listaEstacionesTren.add(new EstacionTren("Plaza Constitucion", lineaTren)); //14
		
		//System.out.println(listaEstacionesTren);
		
		//Cargamos la lista de secciones de tren y chequeamos que se hayan guardado bien
		List<SeccionTren> listaSeccionesTren = new ArrayList<SeccionTren>();
		/*
		listaSeccionesTren.add(new SeccionTren("5 km", new BigDecimal (10), lineaTren));
		listaSeccionesTren.add(new SeccionTren("10 km", new BigDecimal (20), lineaTren));
		listaSeccionesTren.add(new SeccionTren("15 km", new BigDecimal (30), lineaTren));
		*/
		
		lineaTren.agregarSeccion(new SeccionTren("5 km", new BigDecimal (10), lineaTren));
		lineaTren.agregarSeccion(new SeccionTren("10 km", new BigDecimal (20), lineaTren));
		lineaTren.agregarSeccion(new SeccionTren("15 km", new BigDecimal (30), lineaTren));
		
		//System.out.println(listaSeccionesTren);

		//Simulamos viajes de tren y chequeamos el saldo al final
		GregorianCalendar fechaFichadaTrenEntrada = new GregorianCalendar(2018, 5, 28, 10, 20, 0);
		LectoraTren lectoraTrenEntrada = new LectoraTren(4, listaEstacionesTren.get(4), true);
		FichadaTren fichadaTrenEntrada = new FichadaTren(fechaFichadaTrenEntrada, lectoraTrenEntrada.getEstacion(), eTipoFichadaTren.ENTRADA, lectoraTrenEntrada);
		
		GregorianCalendar fechaFichadaTrenSalida = new GregorianCalendar(2018, 5, 28, 11, 20, 0);
		LectoraTren lectoraTrenSalida = new LectoraTren(5, listaEstacionesTren.get(5), false);
		FichadaTren fichadaTrenSalida = new FichadaTren(fechaFichadaTrenSalida, lectoraTrenSalida.getEstacion(), eTipoFichadaTren.SALIDA, lectoraTrenSalida);
				
		Resultado result1 = tarjeta.procesarFichada(fichadaTrenEntrada);
		Resultado result2 = tarjeta.procesarFichada(fichadaTrenSalida);
		
		for (TransaccionSUBE t : tarjeta.getTransacciones()) {
			System.out.println(t);
		}
		
		System.out.println(result1);
		System.out.println(result2);
		
		System.out.println("Su saldo es: " + tarjeta.getSaldo());
		
		/*System.out.println("Su saldo luego del viaje es: " + tarjeta.getSaldo());
		
		System.out.println(tarjeta.getTransacciones());*/
	}
}
