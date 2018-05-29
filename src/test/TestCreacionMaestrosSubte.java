package test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;

import dao.fichadas.subte.EstacionSubteDao;
import dao.fichadas.subte.LineaSubteDao;
import dao.lectoras.LectoraSubteDao;
import modelo.fichadas.subte.EstacionSubte;
import modelo.fichadas.subte.LineaSubte;
import modelo.lectoras.LectoraSubte;

public class TestCreacionMaestrosSubte {

private static int numeroSerieLectora = 1000;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Generamos Maestros de Subte
		
		LineaSubteDao daoLineaSubte = new LineaSubteDao();
		EstacionSubteDao daoEstacionSubte = new EstacionSubteDao();
		LectoraSubteDao daoLectoraSubte = new LectoraSubteDao();
		
		LineaSubte lineaC = new LineaSubte("C", new BigDecimal (10));
		List<EstacionSubte> estacionesLineaC = new ArrayList<EstacionSubte>();
		List<LectoraSubte> lectorasLineaC = new ArrayList<LectoraSubte>();
		
		
		agregarEstacionSubte("Constitucion", lineaC, estacionesLineaC, lectorasLineaC);
		agregarEstacionSubte("San Juan", lineaC, estacionesLineaC, lectorasLineaC);
		agregarEstacionSubte("Independencia", lineaC, estacionesLineaC, lectorasLineaC);
		agregarEstacionSubte("Mariano Moreno", lineaC, estacionesLineaC, lectorasLineaC);
		agregarEstacionSubte("Avenida de Mayo", lineaC, estacionesLineaC, lectorasLineaC);
		agregarEstacionSubte("Diagonal Norte", lineaC, estacionesLineaC, lectorasLineaC);
		agregarEstacionSubte("Lavalle", lineaC, estacionesLineaC, lectorasLineaC);
		agregarEstacionSubte("General San Martin", lineaC, estacionesLineaC, lectorasLineaC);
		agregarEstacionSubte("Retiro", lineaC, estacionesLineaC, lectorasLineaC);

		//Persistimos.
		try {
			daoLineaSubte.agregarLinea(lineaC);
			
			for (EstacionSubte e : estacionesLineaC) {
				daoEstacionSubte.agregarEstacion(e);
			}
			
			for (LectoraSubte l : lectorasLineaC) {
				daoLectoraSubte.agregarLectora(l);
			}
		} catch (HibernateException he) {
			System.out.println(he.getMessage() + ", " + he.toString());
			he.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage() + ", " + e.toString());
			e.printStackTrace();
		}
		
	}

	private static void agregarEstacionSubte(String nombre, LineaSubte linea, List<EstacionSubte> estaciones, List<LectoraSubte> lectoras) {
		EstacionSubte estacion = new EstacionSubte(nombre, linea);
		estaciones.add(estacion);
		lectoras.add(new LectoraSubte(numeroSerieLectora++, estacion));
	}

}
