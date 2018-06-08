package modelo.lectoras;

import modelo.fichadas.subte.EstacionSubte;

public class LectoraSubte extends Lectora{
	
	private EstacionSubte estacion;
	
	public LectoraSubte () {}
	
	public LectoraSubte(int nroSerie, EstacionSubte estacion) {
		super(nroSerie);
		this.estacion = estacion;
	}
	
	public EstacionSubte getEstacion() {
		return estacion;
	}
	
	public void setEstacion(EstacionSubte estacion) {
		this.estacion = estacion;
	}

	@Override
	public String toString() {
		return "LectoraSubte [estacion=" + estacion + "]";
	}
	
	
}
