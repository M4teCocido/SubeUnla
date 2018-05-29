package test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;

import dao.fichadas.colectivo.InternoColectivoDao;
import dao.fichadas.colectivo.LineaColectivoDao;
import dao.fichadas.colectivo.TramoColectivoDao;
import dao.lectoras.LectoraColectivoDao;
import modelo.fichadas.colectivo.InternoColectivo;
import modelo.fichadas.colectivo.LineaColectivo;
import modelo.fichadas.colectivo.TramoColectivo;
import modelo.lectoras.LectoraColectivo;



public class TestCreacionMaestrosColectivo {

	private static int numeroSerieLectora = 2000;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Generamos Maestros de Subte
		
		LineaColectivoDao daoLinea = new LineaColectivoDao();
		InternoColectivoDao daoInterno = new InternoColectivoDao();
		TramoColectivoDao daoTramo = new TramoColectivoDao();
		LectoraColectivoDao daoLectora = new LectoraColectivoDao();
		
		LineaColectivo linea74 = new LineaColectivo("74");
		List<InternoColectivo> internosLinea74 = new ArrayList<InternoColectivo>();
		List<LectoraColectivo> lectorasLinea74 = new ArrayList<LectoraColectivo>();
		List<TramoColectivo> tramosLinea74 = new ArrayList<TramoColectivo>();
		
		tramosLinea74.add(new TramoColectivo("2 km", new BigDecimal(9), linea74));
		tramosLinea74.add(new TramoColectivo("4 km", new BigDecimal(12), linea74));
		tramosLinea74.add(new TramoColectivo("6 km", new BigDecimal(15), linea74));
		
		agregarInternoColectivo("Constitucion", linea74, internosLinea74, lectorasLinea74);
		agregarInternoColectivo("San Juan", linea74, internosLinea74, lectorasLinea74);
		agregarInternoColectivo("Independencia", linea74, internosLinea74, lectorasLinea74);
		agregarInternoColectivo("Mariano Moreno", linea74, internosLinea74, lectorasLinea74);
		agregarInternoColectivo("Avenida de Mayo", linea74, internosLinea74, lectorasLinea74);
		agregarInternoColectivo("Diagonal Norte", linea74, internosLinea74, lectorasLinea74);
		agregarInternoColectivo("Lavalle", linea74, internosLinea74, lectorasLinea74);
		agregarInternoColectivo("General San Martin", linea74, internosLinea74, lectorasLinea74);
		agregarInternoColectivo("Retiro", linea74, internosLinea74, lectorasLinea74);

		//Persistimos.
		try {
			daoLinea.agregarLinea(linea74);
			
			for (InternoColectivo i : internosLinea74) {
				daoInterno.agregarInterno(i);
			}
			
			for (LectoraColectivo l : lectorasLinea74) {
				daoLectora.agregarLectora(l);
			}
			
			for (TramoColectivo t : tramosLinea74) {
				daoTramo.agregarTramo(t);
			}
			
			InternoColectivo c = daoInterno.traerInterno(1);
			System.out.println(c.toString());
			
		} catch (HibernateException he) {
			System.out.println(he.getMessage() + ", " + he.toString());
			he.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage() + ", " + e.toString());
			e.printStackTrace();
		}	
		
	}

	private static void agregarInternoColectivo(String nombre, LineaColectivo linea, List<InternoColectivo> internos, List<LectoraColectivo> lectoras) {
		InternoColectivo interno = new InternoColectivo(linea, nombre);
		internos.add(interno);
		lectoras.add(new LectoraColectivo(numeroSerieLectora++, interno));
	}
	
}
