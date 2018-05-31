package test;

import java.math.BigDecimal;
import java.util.GregorianCalendar;

import dao.DocumentoDao;
import dao.PersonaDao;
import dao.UsuarioDao;
import modelo.Documento;
import modelo.eTipoDocumento;
import modelo.Descuentos.DescuentoBoletoEstudiantil;
import dao.PermisoDao;
import dao.PersonaDao;
import dao.TarjetaSubeDao;
import dao.UsuarioDao;
import dao.fichadas.FichadaDao;
import dao.fichadas.TransaccionSUBEDao;
import dao.fichadas.subte.EstacionSubteDao;
import dao.fichadas.subte.FichadaSubteDao;
import dao.fichadas.subte.LineaSubteDao;
import dao.lectoras.LectoraDao;
import dao.lectoras.LectoraSubteDao;
import modelo.Documento;
import modelo.eTipoDocumento;
import modelo.fichadas.Fichada;
import modelo.fichadas.TransaccionSUBE;
import modelo.fichadas.subte.EstacionSubte;
import modelo.fichadas.subte.FichadaSubte;
import modelo.fichadas.subte.LineaSubte;
import modelo.lectoras.Lectora;
import modelo.lectoras.LectoraSubte;
//import modelo.Persona.eGenero;
import modelo.eGenero;
import modelo.Permiso;
import modelo.Persona;
import modelo.TarjetaSube;
import modelo.Usuario;


public class TestDaos {

	public static void main(String[] args) {

		//Test inicial de persistencia : Movido a los distintos tests de creacion de maestros.
		try {
			//----------------------------TEST PERMISO----------------------------
			/*PermisoDao dao = new PermisoDao();
			Permiso permiso = new Permiso("Permiso 1", "Test del permiso", "1111");
			dao.agregarPermiso(permiso);*/
			//----------------------------TEST PERSONA----------------------------
			/*UsuarioDao daoUsuario = new UsuarioDao();
			PersonaDao daoPersona = new PersonaDao();
			DocumentoDao daoDocumento = new DocumentoDao();
			GregorianCalendar cal = new GregorianCalendar(1993, 11, 16);
			//Creamos y Guardamos
			Persona persona = new Persona("Gonzalo", "Montaña", eGenero.M, cal, "gonzamcomps@gmail.com", "1558912066", "42991823");
			int idPersona = daoPersona.agregarPersona(persona);
			Documento doc = new Documento("37612478", eTipoDocumento.DNI, persona);
			int idDoc = daoDocumento.agregarDocumento(doc);
			DescuentoBoletoEstudiantil dbe = new DescuentoBoletoEstudiantil(DescuentoBoletoEstudiantil.eTipoBoletoEstudiantil.ESCOLAR);
			int idDescuentoEstudiantil;
			//Ahora levantamos y revisamos que funque.
			doc = daoDocumento.traerDocumento(idDoc);
			persona = daoPersona.traerPersona(idPersona);
			System.out.println(persona);
			//----------------------------TEST USUARIO----------------------------
			UsuarioDao daousuario = new UsuarioDao();
			Usuario usuario = new Usuario("Gonzalocapo", "capomal", persona);
			int idUsuario = daoUsuario.agregarUsuario(usuario);
			usuario = daoUsuario.traerUsuarioPorId(idUsuario);*/
			//----------------------------TEST TARJETA SUBE----------------------------
			/*TarjetaSubeDao daoTarjeta = new TarjetaSubeDao();
			TarjetaSube tarjeta = new TarjetaSube("11111", new BigDecimal (100));
			daoTarjeta.agregarTarjetaSube(tarjeta);
			int idTarjeta = daoTarjeta.agregarTarjetaSube(tarjeta);
			tarjeta = daoTarjeta.traerTarjeta(idTarjeta);
			System.out.println(tarjeta);*/
			//----------------------------TEST TRANSACCION----------------------------
			
			//Creo la persona y dao
			GregorianCalendar fechaNac = new GregorianCalendar(1993, 11, 16);
			Persona persona2 = new Persona("Gonzalo", "Montaña", eGenero.M, fechaNac, "gonzamcomps@gmail.com", "1558912066", "42991823");
			PersonaDao daoPersona2 = new PersonaDao();
			
			//Creo la tarjeta y le asigno propietario y dao
			TarjetaSube tarjeta2 = new TarjetaSube("9999", new BigDecimal (100));
			tarjeta2.setPropietario(persona2);
			TarjetaSubeDao daoTarjeta2 = new TarjetaSubeDao();
			
			//Creo la linea de subte, estacion y daos
			LineaSubte lineaSubte = new LineaSubte("C", new BigDecimal (10));
			LineaSubteDao daoLineaSubte = new LineaSubteDao();
			EstacionSubte estacionSubte = new EstacionSubte("Constitucion", lineaSubte);
			EstacionSubteDao daoEstacionSubte = new EstacionSubteDao();
			
			//Creo lectora y dao
			LectoraSubte lectoraSubte = new LectoraSubte(12323, estacionSubte);
			LectoraSubteDao daoLectoraSubte = new LectoraSubteDao();
			
			//Creo la fichada y dao
			GregorianCalendar fechaHoraFichada = new GregorianCalendar(2018, 3, 15, 15, 10, 25);
			//Fichada fichada = new Fichada(fechaHoraFichada, lectoraSubte);
			
			//Creo la fichadaSubte y dao
			//FichadaSubte fichadaSubte = new FichadaSubte(fichada.getFechaHora(), lectoraSubte, daoEstacionSubte.traerEstacion(1));
			FichadaSubte fichadaSubte = new FichadaSubte(fechaHoraFichada, lectoraSubte, estacionSubte);
			FichadaSubteDao daoFichadaSubte = new FichadaSubteDao();
			
			//Creo la transaccion y dao
			TransaccionSUBE transaccion = new TransaccionSUBE(lineaSubte.getPrecioViaje(), tarjeta2, fichadaSubte);
			TransaccionSUBEDao daoTransaccion = new TransaccionSUBEDao();
			
			//Persisto
			daoPersona2.agregarPersona(persona2);
			System.out.println("\nGuardamos persona\n");
			daoTarjeta2.agregarTarjetaSube(tarjeta2);
			System.out.println("\nGuardamos tarjeta\n");
			daoLineaSubte.agregarLinea(lineaSubte);
			System.out.println("\nGuardamos linea subte\n");
			daoEstacionSubte.agregarEstacion(estacionSubte);
			System.out.println("\nGuardamos estacion subte\n");
			daoLectoraSubte.agregarLectora(lectoraSubte);
			System.out.println("\nGuardamos lectoraSubte\n");
			daoFichadaSubte.agregarFichada(fichadaSubte);
			System.out.println("\nGuardamos fichadaSubte\n");
			daoTransaccion.agregarTransaccion(transaccion);
			System.out.println("\nGuardamos todo sin drama\n");
			
		} catch (Exception e) {
			System.out.println(e.getMessage() + ", " + e.getCause());
			e.printStackTrace();
		}
	}
}
