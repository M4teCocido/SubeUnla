package modelo;

import java.util.HashSet;
import java.util.Set;

import modelo.Usuario;

public class Permiso {
	
	private int idPermiso;
	private String nombre;
	private String descripcion;
	private String codigo;
	private Set<Usuario> usuarios;
	
	public Permiso() {}
	
	public Permiso(String nombre, String descripcion, String codigo) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.codigo = codigo;
		this.usuarios = new HashSet<Usuario>();
	}
	
	public int getIdPermiso() {
		return idPermiso;
	}

	protected void setIdPermiso(int idPermiso) {
		this.idPermiso = idPermiso;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public boolean equals(Permiso p) {
		boolean equivalente = false;
		if ((p.getCodigo().compareTo(codigo) == 0)) {
			equivalente = true;	
		}
		return equivalente;
	}
	
	@Override
	public String toString() {
		return "Permiso [idPermiso=" + idPermiso + ", nombre=" + nombre + ", descripcion=" + descripcion + ", codigo="
				+ codigo + ", usuarios=" + usuarios + "]";
	}

	public Set<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
}
