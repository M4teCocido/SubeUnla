package modelo.lectoras;

import java.util.HashSet;
import java.util.Set;

import modelo.fichadas.Fichada;

public abstract class Lectora {
	
	private int idLectora;
	private int nroSerie;
	private Set<Fichada> fichadas;

	public Lectora() {}

	public Lectora(int nroSerie) {
		super();
		this.setNroSerie(nroSerie);
		this.setFichadas(new HashSet<Fichada>());
	}

	public int getIdLectora() {
		return idLectora;
	}
	
	protected void setIdLectora(int idLectora) {
		this.idLectora = idLectora;
	}

	public int getNroSerie() {
		return nroSerie;
	}

	public void setNroSerie(int nroSerie) {
		this.nroSerie = nroSerie;
	}

	public Set<Fichada> getFichadas() {
		return fichadas;
	}

	public void setFichadas(Set<Fichada> fichadas) {
		this.fichadas = fichadas;
	}

}
