package modelo.Descuentos;

import java.math.BigDecimal;

import modelo.fichadas.Fichada;

//SuperClase de descuentos
public abstract class DescuentoSube {
	
	private int idDescuento;
	private String nombre;
	
	public DescuentoSube() {}
	
	public DescuentoSube(String nombre) {
		super();
		this.nombre = nombre;
	}

	protected void setIdDescuento(int idDescuento) {
		this.idDescuento = idDescuento;
	}
	
	public int getIdDescuento() {
		return this.idDescuento;
	}
	
	protected String getNombre() {
		return nombre;
	}

	protected void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public abstract BigDecimal aplicarDescuento (BigDecimal importe, Fichada fichada);

}
