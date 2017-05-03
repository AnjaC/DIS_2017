package de.dis2013.data;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import de.dis2013.util.Helper;

/**
 * Vertrags-Bean
 */
public abstract class Vertrag {
	private int vertragsnummer = -1;
	private Date datum;
	private String ort;
	static int currentId = 0;
	int id;
	Person vertragspartner;
	
	public Vertrag() {
		this.id = currentId++;
	}
	@Column(name="Vertragsnummer", nullable=false)	
	public int getVertragsnummer() {
		return vertragsnummer;
	}
	public void setVertragsnummer(int vertragsnummer) {
		this.vertragsnummer = vertragsnummer;
	}
	@Column(name="Datum", nullable=false)	
	public Date getDatum() {
		return datum;
	}
	public void setDatum(Date datum) {
		this.datum = datum;
	}
	@Column(name="Ort", nullable=false)	
	public String getOrt() {
		return ort;
	}
	public void setOrt(String ort) {
		this.ort = ort;
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
	@Column(name="Vertragspartner", nullable=false)	
	public Person getVertragspartner() {
		return vertragspartner;
	}

	public void setVertragspartner(Person vertragspartner) {
		this.vertragspartner = vertragspartner;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		
		result = prime * result + ((getDatum() == null) ? 0 : getDatum().hashCode());
		result = prime * result + ((getOrt() == null) ? 0 : getOrt().hashCode());
		
		return result;
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null || !(obj instanceof Vertrag))
			return false;
	
		Vertrag other = (Vertrag)obj;
	
		if(other.getVertragsnummer() != getVertragsnummer() ||
				!Helper.compareObjects(this.getDatum(), other.getDatum()) ||
				!Helper.compareObjects(this.getOrt(), other.getOrt()))
		{
			return false;
		}
		
		return true;
	}
}
