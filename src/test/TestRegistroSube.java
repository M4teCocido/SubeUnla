package test;

import java.math.BigDecimal;

import modelo.TarjetaSube;
import modelo.Usuario;
import negocio.PersonaABM;
import negocio.TarjetaSubeABM;
import negocio.UsuarioABM;

public class TestRegistroSube {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BigDecimal monto = new BigDecimal(10);
		TarjetaSubeABM abm = new TarjetaSubeABM();
		PersonaABM abm2 = new PersonaABM();
		UsuarioABM abm3 = new UsuarioABM();
		try {
			//abm.agregar("TARJETA13", monto);
			/*TarjetaSube tarjeta = abm.traerTarjetaPorId(12);
			tarjeta.setPropietario(abm2.traerPersona(6));
			abm.modificar(tarjeta);*/
			Usuario usuario = abm3.traerUsuarioPorId(2);
			usuario.setPersona(abm2.traerPersona(8));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
