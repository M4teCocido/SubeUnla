package test;

import modelo.fichadas.tren.LineaTren;
import negocio.LineaTrenABM;

public class TestABM {

	public static void main(String[] args) {
		
		try { //test de las validaciones de todo lo de tren
			LineaTrenABM abm = new LineaTrenABM();
			
			int idLinea = 1;
			abm.traerLineaPorId(idLinea);
			abm.eliminarLineaPorId(idLinea);
			
			String nombre = "";
			abm.agregarLinea(nombre);
			
			LineaTren l = abm.traerLineaPorId(1);
			l.setNombre(nombre);
			abm.modificarLinea(l);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
