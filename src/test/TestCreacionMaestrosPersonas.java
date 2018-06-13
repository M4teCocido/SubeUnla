package test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import dao.DocumentoDao;
import dao.PermisoDao;
import dao.PersonaDao;
import dao.TarjetaSubeDao;
import dao.UsuarioDao;
import dao.descuentos.DescuentoBoletoEstudiantilDao;
import dao.descuentos.DescuentoTarifaSocialDao;
import dao.fichadas.subte.EstacionSubteDao;
import dao.lectoras.LectoraExternaDao;
import dao.lectoras.LectoraSubteDao;
import modelo.Documento;
import modelo.Permiso;
import modelo.eTipoDocumento;
import modelo.Descuentos.DescuentoBoletoEstudiantil;
import modelo.Descuentos.DescuentoTarifaSocial;
import modelo.Descuentos.eTipoBoletoEstudiantil;
import modelo.fichadas.FichadaRecarga;
import modelo.fichadas.subte.EstacionSubte;
import modelo.fichadas.subte.FichadaSubte;
import modelo.lectoras.LectoraExterna;
import modelo.lectoras.LectoraSubte;
import util.RNG;
import modelo.Persona;
import modelo.TarjetaSube;
import modelo.Usuario;
import modelo.eGenero;

public class TestCreacionMaestrosPersonas {

	private static List<Persona> personas = new ArrayList<Persona>();
	private static int contadorTarjeta = 0;
	
	public static void main(String[] args) {
		GregorianCalendar fecha = new GregorianCalendar (1993, 11, 16);
		
		UsuarioDao daoUsuarios = new UsuarioDao();
		PersonaDao daoPersona = new PersonaDao();
		DocumentoDao daoDocumento = new DocumentoDao();
		PermisoDao daoPermisos = new PermisoDao();
		DescuentoBoletoEstudiantilDao daoBoletoEstudiantil = new DescuentoBoletoEstudiantilDao();
		DescuentoTarifaSocialDao daoTarifaSocial = new DescuentoTarifaSocialDao();
		TarjetaSubeDao daoTarjeta = new TarjetaSubeDao();
		EstacionSubteDao daoEstacionSubte = new EstacionSubteDao();
		LectoraSubteDao daoLectoraSubte = new LectoraSubteDao();
		LectoraExternaDao daoLectoraExterna = new LectoraExternaDao (); 
		List<Permiso> permisos = new ArrayList<Permiso>();
		List<LectoraExterna> lectorasExternas = new ArrayList<LectoraExterna>();
		Persona persona;
		Documento doc;
		DescuentoTarifaSocial descuentoTarifaSocial;
		DescuentoBoletoEstudiantil dbe;
		TarjetaSube tarjeta1;
		TarjetaSube tarjeta2;
		EstacionSubte estacionSubte;
		LectoraSubte lectora;
		
		try {
			//Creamos los objetos
			
			/*
			persona = new Persona("Gonzalo", "Montaña", eGenero.M, fecha, "gonzamcomps@gmail.com", "1558912066", "42991823");
			doc = new Documento("37612478", eTipoDocumento.DNI, persona);
			descuentoTarifaSocial = new DescuentoTarifaSocial(persona);
			dbe = new DescuentoBoletoEstudiantil(eTipoBoletoEstudiantil.ESCOLAR, persona);
			tarjeta1 = new TarjetaSube("TARJETA1", 100);
			tarjeta2 = new TarjetaSube("TARJETA2", 200);
			persona.asociarTarjeta(tarjeta1);
			persona.asociarTarjeta(tarjeta2);*/
			
			agregarPersona("Gonzalo", "Montaña", eGenero.M, fecha, "gonzamcomps@gmail.com", "1558912066", "42991823", "39383938");
			agregarPersona("Martin", "Rios", eGenero.M, fecha, "riosmartin93@gmail.com", "1558912066", "42991823", "29383938");
			agregarPersona("Rodrigo", "Hernandez", eGenero.M, fecha, "asdasd@gmail.com", "1558912066", "42991823", "19383938");
			agregarPersona("Ignacio", "Oliveto", eGenero.M, fecha, "dsadsa@gmail.com", "1558912066", "42991823", "49383938");
			agregarPersona("Cristian", "Juarez", eGenero.M, fecha, "haha@gmail.com", "1558912066", "42991823", "59383938");
			
			lectorasExternas.add(new LectoraExterna(4000, "Kiosko 9 De Julio"));
			lectorasExternas.add(new LectoraExterna(4001, "Terminal Palomar"));
			lectorasExternas.add(new LectoraExterna(4002, "Kiosko UNLa"));
			lectorasExternas.add(new LectoraExterna(4003, "Multiservicio Ricardo"));
			
			//tarjeta1.setPropietario(persona);
			//tarjeta2.setPropietario(persona);
			/*
			estacionSubte = daoEstacionSubte.traerEstacion(1);
			lectora = daoLectoraSubte.traerLectora(1);
			tarjeta2.procesarFichada(new FichadaSubte(new GregorianCalendar(), lectora, estacionSubte));
			tarjeta2.procesarFichada(new FichadaSubte(new GregorianCalendar(), lectora, estacionSubte));
			tarjeta2.procesarFichada(new FichadaSubte(new GregorianCalendar(), lectora, estacionSubte));
			//tarjeta2.procesarFichada(new FichadaSubte(new GregorianCalendar(), null, estacionSubte));
			persona.setDocumento(doc);*/
			//Persistimos
			
			permisos.add(new Permiso("SUBEABM", "SUBEABM", "SUBEABM"));
			permisos.add(new Permiso("REPORTES", "REPORTES", "REPORTES"));
			permisos.add(new Permiso("ESTADISTICAS", "ESTADISTICAS", "ESTADISTICAS"));
			permisos.add(new Permiso("CONSULTARTARJETA", "CONSULTARTARJETA", "CONSULTARTARJETA"));
			
			List<Usuario> admins = new ArrayList<Usuario>();
			
			for (Permiso p : permisos) {
				daoPermisos.agregarPermiso(p);
			}
			
			admins.add(new Usuario("ROY", "matecocido", null));
			admins.add(new Usuario("NACHO", "oliverto", null));
			admins.add(new Usuario("GONZA", "hstark", null));
			admins.add(new Usuario("POTA", "potamia", null));
			admins.add(new Usuario("CRIS", "cmjh", null));
			
			for (Usuario admin : admins) {
				admin.agregarPermiso(permisos.get(0));
				admin.agregarPermiso(permisos.get(1));
				admin.agregarPermiso(permisos.get(2));
				daoUsuarios.agregarUsuario(admin);
			}

			for (Persona p : personas) {
				daoPersona.agregarPersona(p);
				for (TarjetaSube t : p.getTarjetasAsociadas()) {
					daoTarjeta.agregarTarjetaSube(t);
				}
				daoUsuarios.agregarUsuario(new Usuario(p.getDocumento().getNumero().toString(), p.getDocumento().getNumero().toString(), p));
			}

			for (LectoraExterna l : lectorasExternas) {
				daoLectoraExterna.agregarLectora(l);
			}
			
			for (Persona p : daoPersona.traerPersonas()) {
				System.out.println("");
				System.out.println(p);
			}
			
			//int idPersona = daoPersona.agregarPersona(persona);
			//int idDoc = daoDocumento.agregarDocumento(doc);
			//int idTarifaSocial = daoTarifaSocial.agregarDescuento(descuentoTarifaSocial);
			//int idBoletoEstudiantil = daoBoletoEstudiantil.agregarDescuento(dbe);
			//int idTarjeta1 = daoTarjeta.agregarTarjetaSube(tarjeta1);
			//int idTarjeta2 = daoTarjeta.agregarTarjetaSube(tarjeta2);
			//Traemos la persona y chequeamos si trae todo.
			//persona = daoPersona.traerPersona(idPersona);
			
			//tarjeta1 = daoTarjeta.traerTarjeta(idTarjeta1);
			//tarjeta2 = daoTarjeta.traerTarjeta(idTarjeta2);

			
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private static void agregarPersona(String nombre, String apellido, eGenero genero, GregorianCalendar fechaNac, String mail, String celular, String telefono, String nroDoc) {
		try {
			Persona persona = new Persona(nombre, apellido, genero, fechaNac, mail, celular, telefono);
			persona.setDocumento(new Documento(nroDoc, eTipoDocumento.DNI, persona));
			persona.asociarTarjeta(new TarjetaSube("606126728585992" + (contadorTarjeta++), 25));
			//Randomizamos agregados de Descuentos.
			if (RNG.rollBoolean()) {
				persona.asignarDescuentoBoletoEstudiantil(new DescuentoBoletoEstudiantil(eTipoBoletoEstudiantil.ESCOLAR, persona));
			}
			if (RNG.rollBoolean()) {
				persona.asignarDescuentoTarifaSocial(new DescuentoTarifaSocial(persona));
			}			
			personas.add(persona);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}