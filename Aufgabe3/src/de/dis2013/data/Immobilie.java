package de.dis2013.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import de.dis2013.util.Helper;

/**
 * Immobilien-Bean
 */

public abstract class Immobilie {
	private int id = -1;
	private String ort;
	private int plz;
	private String strasse;
	private String hausnummer;
	private int flaeche;
	private Makler verwalter;
	static int currentId = 0;
	
	public Immobilie() {
		this.id = currentId++;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="Ort", nullable=false)	
	public String getOrt() {
		return ort;
	}
	public void setOrt(String ort) {
		this.ort = ort;
	}
	@Column(name="PLZ", nullable=false)	
	public int getPlz() {
		return plz;
	}
	public void setPlz(int plz) {
		this.plz = plz;
	}
	@Column(name="Strasse", nullable=false)	
	public String getStrasse() {
		return strasse;
	}
	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}
	@Column(name="Hausnummer", nullable=false)	
	public String getHausnummer() {
		return hausnummer;
	}
	public void setHausnummer(String hausnummer) {
		this.hausnummer = hausnummer;
	}
	@Column(name="Flaeche", nullable=false)	
	public int getFlaeche() {
		return flaeche;
	}
	public void setFlaeche(int flaeche) {
		this.flaeche = flaeche;
	}
	
	public void setVerwalter(Makler verwalter) {
		this.verwalter = verwalter;
	}
	@Column(name="Verwalter", nullable=false)	
	public Makler getVerwalter() {
		return verwalter;
	}


	public int hashCode() {
		final int prime = 31;
		int result = 1;
		
		result = prime * result + ((getOrt() == null) ? 0 : getOrt().hashCode());
		result = prime * result + ((getStrasse() == null) ? 0 : getStrasse().hashCode());
		result = prime * result + ((getHausnummer() == null) ? 0 : getHausnummer().hashCode());
		
		return result;
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null || !(obj instanceof Immobilie))
			return false;
	
		Immobilie other = (Immobilie)obj;
	
		if(other.getId() != getId() ||
				other.getPlz() != getPlz() ||
				other.getFlaeche() != getFlaeche() ||
				!Helper.compareObjects(this.getOrt(), other.getOrt()) ||
				!Helper.compareObjects(this.getStrasse(), other.getStrasse()) ||
				!Helper.compareObjects(this.getHausnummer(), other.getHausnummer()))
		{
			return false;
		}
		
		return true;
	}
}
