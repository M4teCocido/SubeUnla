package modelo.lectoras;

import modelo.fichadas.colectivo.InternoColectivo;

public class LectoraColectivo extends Lectora {
	
	private InternoColectivo interno;
	
	public LectoraColectivo() {}
	
	public LectoraColectivo(int nroSerie, InternoColectivo interno) {
		super(nroSerie);
		this.interno = interno;
	}
	
	public InternoColectivo getInterno() {
		return interno;
	}
	
	public void setInterno(InternoColectivo interno) {
		this.interno = interno;
	}

	@Override
	public String toString() {
		return "LectoraColectivo [interno= " + interno.getIdInterno() + " nombre= "+ interno.getNombre() + "]";
	}
	
	
}
