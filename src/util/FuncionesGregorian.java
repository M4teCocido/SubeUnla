package util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class FuncionesGregorian {

	private static int [] diasDelMes = {31,28,31,30,31,30,31,31,30,31,30,31};
	
	private static String [] daysOfWeek = 
		{"Domingo","Lunes", "Martes","Miercoles","Jueves","Viernes","Sabado"};
	
	private static String [] months = 
		{"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
	
	public static int traerAnio ( GregorianCalendar f ){
		return f.get (Calendar.YEAR); 
	}
	
	public static int traerMes(GregorianCalendar f){
		return f.get(Calendar.MONTH);
	}

	public static int traerDia(GregorianCalendar f){
		return f.get(Calendar.DAY_OF_MONTH);
	}
	
	public static boolean esFechaValida(int anio, int mes, int dia){
		
		if (mes < 1 || mes > 12)
			return false;
		
		int diasMes;
		
		diasMes = traerCantDiasDeUnMes(anio,mes);
		
		if (dia < 1 || dia > diasMes)
			return false;
		
		if (anio < 1)
			return false;
		
		return true;
	}
	
	public static GregorianCalendar traerFecha(String fecha) throws RuntimeException{ 
		//Formato dd/mm/aaaa
		String [] datosFecha = fecha.split("/");
		if (datosFecha.length != 3){
			throw new RuntimeException("El formato de la fecha es invalido!");
		}
		int [] valoresFecha = new int[3];
		for (int i = 0; i < 3; i++){
			try{
				valoresFecha[i] = Integer.parseInt(datosFecha[i]);
			} catch (NumberFormatException e){
				throw new RuntimeException("Los datos ingresados no son Numericos");
			}
		}

		int dia = valoresFecha[0];
		int mes = valoresFecha[1]; //Ver si hay que restarle 1.
		int anio = valoresFecha[2];
		
		if (!esFechaValida(anio, mes, dia)){
			throw new RuntimeException("Los valores ingresados no son validos!");
		}
		
		GregorianCalendar c = new GregorianCalendar(anio, mes, dia);
		return c;
		
	}
	
	public static GregorianCalendar traerFecha(int anio,int mes,int dia){
		
		GregorianCalendar c = new GregorianCalendar(anio,mes,dia);
		return c;
		
		
	}
	
	public static boolean esBisiesto(int anio){
		return ((anio % 4 == 0) && ((anio % 100 != 0) || (anio % 400 == 0)));
	}
	
	
	public static String traerFechaCorta ( GregorianCalendar f ){
		//Asumo que la fecha es valida, dado que esta contenida
		//en un GregorianCalendar.
		return new SimpleDateFormat("dd/MM/yyyy").format(f.getTime());
		
		//return fechaCorta;
	}
	
	public static String traerFechaCortaHora(GregorianCalendar f){
		return new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(f.getTime());
	}
	
	public static GregorianCalendar traerFechaProximo(GregorianCalendar f, int dias){
		GregorianCalendar c = (GregorianCalendar) f.clone();
		c.add(Calendar.DAY_OF_MONTH, dias);
		return c;
	}
	
	public static boolean esDiaHabil(GregorianCalendar fecha){
		int dayOfWeek = fecha.get(Calendar.DAY_OF_WEEK);
		return (dayOfWeek > 1 && dayOfWeek < 7);
	}
	
	public static String traerDiaDeLaSemana(GregorianCalendar fecha){
		return daysOfWeek[fecha.get(Calendar.DAY_OF_WEEK) - 1];
	}
	
	public static String traerMesEnLetras(GregorianCalendar f){
		return months[f.get(Calendar.MONTH)];
	}
	
	public static String traerFechaLarga(GregorianCalendar f){
		return (traerDiaDeLaSemana(f) + " " + traerDia(f) + " de " + traerMesEnLetras(f) + " del " + traerAnio(f));
	}

	public static boolean sonFechasIguales(GregorianCalendar fecha, GregorianCalendar fecha1){
		return (traerAnio(fecha) == traerAnio(fecha1) && traerMes(fecha) == traerMes(fecha) && traerDia(fecha) == traerDia(fecha1));
	}
	
	public static boolean sonFechasHorasIguales(GregorianCalendar fecha, GregorianCalendar fecha1){
		return (fecha.equals(fecha1));
	}
	
	public static int traerCantDiasDeUnMes(int anio, int mes){
		
		int diasMes;
		if (mes == 2 && esBisiesto(anio)) 
			diasMes = 29;
		else 
			diasMes = diasDelMes[mes - 1];
		
		return diasMes; 
	}
	
	public static double aproximar2Decimal (double valor){
		return round (valor,2);
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	
	public static boolean esNumero(char c){
		return Character.isDigit(c);
	}	
	
	public static boolean esLetra(char c){
		return Character.isLetter(c);
	}
	
	public static boolean esCadenaNros(String cadena){
		for (int i = 0; i < cadena.length(); i++){
		    char c = cadena.charAt(i);        
		    if (!esNumero(c))
		    	return false;
		}
		return true;
	}
	
	public static boolean esCadenaLetras(String cadena){
		
		for (int i = 0; i < cadena.length(); i++){
		    char c = cadena.charAt(i);        
		    if (!esLetra(c))
		    	return false;
		}
		return true;
	}
	
}
