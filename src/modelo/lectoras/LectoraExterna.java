package modelo.lectoras;

public class LectoraExterna extends Lectora {

	private String ubicacion;
	
	public LectoraExterna() {

	}

	public LectoraExterna(int nroSerie, String ubicacion) {
		super(nroSerie);
		this.ubicacion = ubicacion;
		// TODO Auto-generated constructor stub
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	@Override
	public String toString() {
		return "LectoraExterna [ubicacion=" + ubicacion + "]";
	} 
	
	
}
