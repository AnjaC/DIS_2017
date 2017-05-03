package de.dis2013.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import de.dis2013.util.Helper;

/**
 * Kaufvertrags-Bean
 */
@Entity
@Table(name="Kaufvertrag")
public class Kaufvertrag extends Vertrag {
	private int anzahlRaten;
	private int ratenzins;
	private Haus haus;
	
	public Kaufvertrag() {
		super();
	}
	@Column(name="anzahlRaten", nullable=false)		
	public int getAnzahlRaten() {
		return anzahlRaten;
	}
	public void setAnzahlRaten(int anzahlRaten) {
		this.anzahlRaten = anzahlRaten;
	}
	@Column(name="Ratenzins", nullable=false)	
	public int getRatenzins() {
		return ratenzins;
	}
	public void setRatenzins(int ratenzins) {
		this.ratenzins = ratenzins;
	}
	@Column(name="Haus", nullable=false)	
	public Haus getHaus() {
		return haus;
	}

	public void setHaus(Haus haus) {
		this.haus = haus;
	}
	@Override
	  public String toString()
	  {
		String kaufvertrag="Vertragsnummer:"+this.getVertragsnummer()+""+"Datum:"+this.getDatum()+"Ort:"+this.getOrt()+"ID:"+this.id+"anzahlraten"+this.anzahlRaten+"Ratenzins"+this.ratenzins+"Haus"+this.haus;
	    return kaufvertrag;
	  }

	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		
		result = prime * result + getAnzahlRaten();
		result = prime * result + getRatenzins();
		
		return result;
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null || !(obj instanceof Kaufvertrag))
			return false;
	
		Kaufvertrag other = (Kaufvertrag)obj;
	
		if(other.getVertragsnummer() != getVertragsnummer() ||
				!Helper.compareObjects(this.getDatum(), other.getDatum()) ||
				!Helper.compareObjects(this.getOrt(), other.getOrt()) ||
				other.getAnzahlRaten() != getAnzahlRaten() ||
				other.getRatenzins() != getRatenzins())
		{
			return false;
		}
		
		return true;
	}
}
