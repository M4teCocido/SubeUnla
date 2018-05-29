package modelo;
import modelo.eTipoDocumento;

public class Documento {
	
	private int idDocumento;
	private String numero;
	private eTipoDocumento tipoDocumento;
	private Persona persona;
	
	public Documento() {}
	
	public Documento(String numero, eTipoDocumento tipoDocumento, Persona persona) throws Exception {
		this.setNumero(numero);
		this.setTipoDocumento(tipoDocumento);
		this.persona = persona;
	}
	
	public String getNumero() {
		return numero;
	}
	
	public void setNumero(String numero) throws Exception{
		if (validar(numero) == false) throw new Exception("El documento esta mal ingresado.");
		else this.numero = numero;
	}

	public eTipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(eTipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public int getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(int idDocumento) {
		this.idDocumento = idDocumento;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	@Override
	public String toString() {
		return "Documento [idDocumento=" + idDocumento + ", numero=" + numero + ", tipoDocumento=" + tipoDocumento + ", Persona=" + this.persona.getNombre() 
				+ "]";
	}

	public boolean validar(String numero) {
		boolean valido = false;
		if (numero.matches("^[0-9]*$") && (numero.length() > 6 && numero.length() < 9)) valido = true;
		return valido;
	}
}
