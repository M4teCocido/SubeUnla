package modelo;
//import java.math.BigDecimal;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;
import modelo.eGenero;
import modelo.Documento;
import modelo.TarjetaSube;
import modelo.Descuentos.*;
import util.FuncionesGregorian;
import util.IndexableSet;

public class Persona {
		
	private int idPersona;
	private String nombre;
	private String apellido;
	private Documento documento;
	private eGenero genero;
	private GregorianCalendar fechaNacimiento;
	private String email;
	private String celular;
	private String telefono;
	private Set<TarjetaSube> tarjetasAsociadas;
	private DescuentoTarifaSocial descuentoTarifaSocial;
	private DescuentoBoletoEstudiantil descuentoBoletoEstudiantil;
	
	public Persona() {}
	
	public Persona(String nombre, String apellido, eGenero genero, GregorianCalendar fechaNacimiento, String email, String celular, String telefono) throws Exception {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		//this.documento = documento;
		this.genero = genero;
		this.fechaNacimiento = fechaNacimiento;
		this.setEmail(email);
		this.setCelular(celular);
		this.setTelefono(telefono);
		this.tarjetasAsociadas = new HashSet<TarjetaSube>();
	}

	public int getIdPersona() {
		return this.idPersona;
	}
	
	protected void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public Documento getDocumento() {
		return documento;
	}
	
	public void setDocumento(Documento documento) {
		this.documento = documento;
	}
	
	public eGenero getGenero() {
		return genero;
	}
	
	public void setGenero(eGenero genero) {
		this.genero = genero;
	}
	
	public GregorianCalendar getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	public void setFechaNacimiento(GregorianCalendar fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) throws Exception {
		if (validarEmail(email) == false) throw new Exception("El email contiene alguno de los siguientes caracteres no permitidos: ¡!?¿+^*¨Ç:;/");
		else {
			this.email = email;
		}
	}
	
	public String getCelular() {
		return celular;
	}
	
	public void setCelular(String celular) throws Exception {
		if (validarCelular(celular) == false) throw new Exception("El celular esta mal ingresado (no comienza con 11 o 15, contiene letras, mayor/menor a 10 digitos).");
		else {
			this.celular = celular;
		}
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	public void setTelefono(String telefono) throws Exception {
		if (validarTelefono(telefono) == false) throw new Exception("El telefono esta mal ingresado (mayor/menor a 8 digitos).");
		else {
			this.telefono = telefono;
		}
	}
	
	public Set<TarjetaSube> getTarjetasAsociadas() {
		return tarjetasAsociadas;
	}
	
	public void setTarjetasAsociadas(Set<TarjetaSube> tarjetasAsociadas) {
		this.tarjetasAsociadas = tarjetasAsociadas;
	}
	
	public DescuentoTarifaSocial getDescuentoTarifaSocial() {
		return descuentoTarifaSocial;
	}
	
	public void setDescuentoTarifaSocial(DescuentoTarifaSocial descuentoTarifaSocial) {
		this.descuentoTarifaSocial = descuentoTarifaSocial;
	}
	
	public DescuentoBoletoEstudiantil getDescuentoBoletoEstudiantil() {
		return descuentoBoletoEstudiantil;
	}

	public void setDescuentoBoletoEstudiantil(DescuentoBoletoEstudiantil descuentoBoletoEstudiantil) {
		this.descuentoBoletoEstudiantil = descuentoBoletoEstudiantil;
	}
	
	@Override
	public String toString() {
		
		String tarjetasString = "NINGUNA";
		String descuentoSocialString;
		String descuentoEstudiantilString;
		String documentoString;
		
		documentoString = (this.documento != null ? this.documento.toString() : "Ninguno");
		descuentoSocialString = (this.descuentoTarifaSocial != null ? this.descuentoTarifaSocial.toString() : "Ninguno"); 
		descuentoEstudiantilString =(this.descuentoBoletoEstudiantil != null ? this.descuentoBoletoEstudiantil.toString() : "Ninguno");
		if (this.tarjetasAsociadas.size() > 0) {
			tarjetasString = "";
			for (int i = 0; i < this.tarjetasAsociadas.size(); i++)	 {
				if (i == 0)
					tarjetasString += IndexableSet.get(this.tarjetasAsociadas, i).getCodigo();
				else
					tarjetasString += ", " + IndexableSet.get(this.tarjetasAsociadas, i).getCodigo();
			}
		}
		
		
		String resultado = "Nombre: " + getNombre() + "\nApellido: " + getApellido() + "\nDocumento: " + documentoString 
				+ "\nGenero: " + getGenero() + "\nFecha nacimiento: " + FuncionesGregorian.traerFechaCorta(getFechaNacimiento()) 
				+ "\nEmail: " + getEmail() + "\nCelular: " + getCelular() + "\nTelefono: " + getTelefono()
				+ "\nTarjetas Asociadas: " + tarjetasString + "\nDescuento tarifa social: " + descuentoSocialString
				+ "\nDescuento Boleto Estudiantil: " + descuentoEstudiantilString;
		
		return resultado;  
				 
	}
	
	public boolean asignarDescuentoBoletoEstudiantil(DescuentoBoletoEstudiantil descuento) {
		this.descuentoBoletoEstudiantil = descuento;
		descuento.setPersona(this);
		return true;
	}
	
	public boolean quitarDescuentoBoletoEstudiantil() {
		if (this.descuentoBoletoEstudiantil != null) {
			this.descuentoBoletoEstudiantil.setPersona(null);
			this.descuentoBoletoEstudiantil = null;
		}
		return true;
	}
	
	public boolean asignarDescuentoTarifaSocial(DescuentoTarifaSocial descuento) {
		this.descuentoTarifaSocial = descuento;
		descuento.setPersona(this);
		return true;
	}
	
	public boolean quitarDescuentoTarifaSocial() {
		if (this.descuentoTarifaSocial != null) {
			this.descuentoTarifaSocial.setPersona(null);
			this.descuentoTarifaSocial = null;
		}
		return true;
	}
	
	public boolean asociarTarjeta(TarjetaSube tarjeta) throws Exception {
		boolean asociado = false;
		if (!getTarjetasAsociadas().isEmpty()) {
			if (tarjetasAsociadas.contains(tarjeta)) 
				throw new Exception("La tarjeta ya esta asociada a esta persona.");
			else {
				tarjeta.setPropietario(this);
				tarjetasAsociadas.add(tarjeta);
				asociado = true;
			}
		} else {
			tarjeta.setPropietario(this);
			tarjetasAsociadas.add(tarjeta);
			asociado = true;
		}
		return asociado;
	}
	
	public boolean desasociarTarjeta(TarjetaSube tarjeta) throws Exception {
		boolean removed = false;
		if (tarjetasAsociadas.contains(tarjeta)) {
			tarjetasAsociadas.remove(tarjeta);
			tarjeta.setPropietario(null);
			removed = true;
		} else throw new Exception("La tarjeta no existe.");
		return removed;
	}
	
	public boolean validarTelefono(String telefono) {
		boolean valido = false;
		if (telefono.matches("^[0-9]*$") && (telefono.length() > 6 && telefono.length() < 9)) valido = true;
		return valido;
	}
	
	public boolean validarCelular(String celular) {
		boolean valido = false;
		if (celular.matches("^[0-9]*$") && (celular.length() == 10)) {
			if (celular.substring(0, 1).matches("^[1]*$")) {
				if (celular.substring(1, 2).matches("^[1]*$") || (celular.substring(1, 2).matches("^[5]*$"))) valido = true;
			}
		}
		return valido;
	}
	
	public boolean validarEmail(String email) {
		boolean valido = false;
		if (email.contains("@") && email.substring(0, email.indexOf("@")).matches("[a-zA-Z0-9]+") == true) valido = true;
		return valido;
	}
}

