package test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.hibernate.HibernateException;

import dao.fichadas.tren.EstacionTrenDao;
import dao.fichadas.tren.LineaTrenDao;
import dao.fichadas.tren.SeccionTrenDao;
import dao.fichadas.tren.ViajeTrenDao;
import dao.lectoras.LectoraTrenDao;
import modelo.fichadas.tren.EstacionTren;
import modelo.fichadas.tren.LineaTren;
import modelo.fichadas.tren.SeccionTren;
import modelo.fichadas.tren.ViajeTren;
import modelo.lectoras.LectoraTren;


public class TestCreacionMaestrosTrenes {

	private static int numeroSerieLectora = 3000;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Generamos Maestros de Subte
		
		LineaTrenDao daoLinea = new LineaTrenDao();
		EstacionTrenDao daoEstacion = new EstacionTrenDao();
		SeccionTrenDao daoSeccion = new SeccionTrenDao();
		ViajeTrenDao daoViaje = new ViajeTrenDao();
		LectoraTrenDao daoLectora = new LectoraTrenDao();
		
		LineaTren lineaRoca = new LineaTren("74");
		List<EstacionTren> estacionesRoca = new ArrayList<EstacionTren>();
		List<SeccionTren> seccionesRoca = new ArrayList<SeccionTren>();
		List<LectoraTren> lectorasRoca = new ArrayList<LectoraTren>();
		List<ViajeTren> viajesRoca = new ArrayList<ViajeTren>();
		
		seccionesRoca.add(new SeccionTren("5 km", new BigDecimal(6), lineaRoca));
		seccionesRoca.add(new SeccionTren("10 km", new BigDecimal(12), lineaRoca));
		seccionesRoca.add(new SeccionTren("15 km", new BigDecimal(18), lineaRoca));
		
		agregarEstacionTren("Alejandro Korn", lineaRoca, estacionesRoca, lectorasRoca);
		agregarEstacionTren("Guernica", lineaRoca, estacionesRoca, lectorasRoca);
		agregarEstacionTren("Glew", lineaRoca, estacionesRoca, lectorasRoca);
		agregarEstacionTren("Longchamps", lineaRoca, estacionesRoca, lectorasRoca);
		agregarEstacionTren("Adrogue", lineaRoca, estacionesRoca, lectorasRoca);
		agregarEstacionTren("Temperley", lineaRoca, estacionesRoca, lectorasRoca);
		agregarEstacionTren("Lomas de Zamora", lineaRoca, estacionesRoca, lectorasRoca);
		agregarEstacionTren("Banfield", lineaRoca, estacionesRoca, lectorasRoca);
		agregarEstacionTren("Remedios de Escalada", lineaRoca, estacionesRoca, lectorasRoca);
		agregarEstacionTren("Lanus", lineaRoca, estacionesRoca, lectorasRoca);
		agregarEstacionTren("Gerli", lineaRoca, estacionesRoca, lectorasRoca);
		agregarEstacionTren("Avellaneda", lineaRoca, estacionesRoca, lectorasRoca);
		agregarEstacionTren("Yrigoyen", lineaRoca, estacionesRoca, lectorasRoca);
		agregarEstacionTren("Plaza Constitucion", lineaRoca, estacionesRoca, lectorasRoca);

		viajesRoca = generarViajesPrueba(estacionesRoca, seccionesRoca, lineaRoca);
		
		//Persistimos.
		try {
			daoLinea.agregarLinea(lineaRoca);
			
			for (EstacionTren e : estacionesRoca) {
				daoEstacion.agregarEstacion(e);
			}
			
			for (LectoraTren l : lectorasRoca) {
				daoLectora.agregarLectora(l);
			}
			
			for (SeccionTren t : seccionesRoca) {
				daoSeccion.agregarSeccion(t);
			}
			
			for (ViajeTren v : viajesRoca) {
				daoViaje.agregarViaje(v);
			}
			
			System.out.println("Datos de Maestros de trenes persistidos!");
			
		} catch (HibernateException he) {
			System.out.println(he.getMessage() + ", " + he.toString());
			he.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage() + ", " + e.toString());
			e.printStackTrace();
		}
		
	}

	private static List<ViajeTren> generarViajesPrueba(List<EstacionTren> estaciones, List<SeccionTren> secciones, LineaTren linea) {
		List<ViajeTren> viajes = new ArrayList<ViajeTren>();
		Random rand = new Random();
		for (EstacionTren origen : estaciones) {
			for (EstacionTren destino : estaciones) {
				viajes.add(new ViajeTren(origen, destino, secciones.get(rand.nextInt(secciones.size())), linea));
			}
		}
		return viajes;
	}
	
	private static void agregarEstacionTren(String nombre, LineaTren linea, List<EstacionTren> estaciones, List<LectoraTren> lectoras) {
		EstacionTren estacion = new EstacionTren(nombre, linea);
		estaciones.add(estacion);
		lectoras.add(new LectoraTren(numeroSerieLectora++, estacion, true));
		lectoras.add(new LectoraTren(numeroSerieLectora++, estacion, false));
	}

}
