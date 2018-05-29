package test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import dao.DocumentoDao;
import dao.PersonaDao;
import dao.TarjetaSubeDao;
import dao.descuentos.DescuentoBoletoEstudiantilDao;
import dao.descuentos.DescuentoTarifaSocialDao;
import dao.fichadas.subte.EstacionSubteDao;
import dao.lectoras.LectoraSubteDao;
import modelo.Documento;
import modelo.eTipoDocumento;
import modelo.Descuentos.DescuentoBoletoEstudiantil;
import modelo.Descuentos.DescuentoTarifaSocial;
import modelo.Descuentos.eTipoBoletoEstudiantil;
import modelo.fichadas.FichadaRecarga;
import modelo.fichadas.subte.EstacionSubte;
import modelo.fichadas.subte.FichadaSubte;
import modelo.lectoras.LectoraSubte;
import util.RNG;
import modelo.Persona;
import modelo.TarjetaSube;
import modelo.eGenero;

public class TestCreacionMaestrosPersonas {

	private static List<Persona> personas = new ArrayList<Persona>();
	private static int contadorTarjeta = 0;
	
	public static void main(String[] args) {
		GregorianCalendar fecha = new GregorianCalendar (1993, 11, 16);
		
			
		PersonaDao daoPersona = new PersonaDao();
		DocumentoDao daoDocumento = new DocumentoDao();
		DescuentoBoletoEstudiantilDao daoBoletoEstudiantil = new DescuentoBoletoEstudiantilDao();
		DescuentoTarifaSocialDao daoTarifaSocial = new DescuentoTarifaSocialDao();
		TarjetaSubeDao daoTarjeta = new TarjetaSubeDao();
		EstacionSubteDao daoEstacionSubte = new EstacionSubteDao();
		LectoraSubteDao daoLectoraSubte = new LectoraSubteDao();
		
		
		
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
			for (Persona p : personas) {
				daoPersona.agregarPersona(p);
				for (TarjetaSube t : p.getTarjetasAsociadas()) {
					daoTarjeta.agregarTarjetaSube(t);
				}
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
			persona.asociarTarjeta(new TarjetaSube("TARJETA" + (contadorTarjeta++), 25));
			persona.asociarTarjeta(new TarjetaSube("TARJETA" + (contadorTarjeta++), 10).SetActivaC(false));
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