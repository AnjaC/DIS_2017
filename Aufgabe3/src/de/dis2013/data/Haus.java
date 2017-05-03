package de.dis2013.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import de.dis2013.util.Helper;

/**
 * Haus-Bean
 */
@Entity
@Table(name="haeuser")
public class Haus extends Immobilie {
	private int stockwerke;
	private int kaufpreis;
	private boolean garten;

	public Haus() {
		super();
	}
	@Column(name="Stockwerke", nullable=false)
	public int getStockwerke() {
		return stockwerke;
	}
		
	public void setStockwerke(int stockwerke) {
		this.stockwerke = stockwerke;
	}
	@Column(name="Kaufpreis", nullable=false)	
	public int getKaufpreis() {
		return kaufpreis;
	}

	public void setKaufpreis(int kaufpreis) {
		this.kaufpreis = kaufpreis;
	}
	@Column(name="Garten", nullable=false)	
	public boolean isGarten() {
		return garten;
	}

	public void setGarten(boolean garten) {
		this.garten = garten;
	}
	 @Override
	  public String toString()
	  {
		
		String haus="Stockwerke:"+this.stockwerke+" "+"Kaufpreis:"+this.kaufpreis+" "+"Garten:"+this.garten+
				"Ort:"+this.getOrt()+" "+"PLZ:"+this.getPlz()+"Strasse:"+this.getStrasse()+""+"Hausnummer"+this.getHausnummer()+""+"Verwalter:"+this.getVerwalter()+" "+"Flaeche:"+this.getFlaeche()+" "+"ID:"+this.getId();
	    return haus;}
	
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		
		result = prime * result + getStockwerke();
		result = prime * result + getKaufpreis();
		result = prime * result + ((isGarten()) ? 1 : 0);
		
		return result;
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null || !(obj instanceof Haus))
			return false;
	
		Haus other = (Haus)obj;
	
		if(other.getId() != getId() ||
				other.getPlz() != getPlz() ||
				other.getFlaeche() != getFlaeche() ||
				!Helper.compareObjects(this.getOrt(), other.getOrt()) ||
				!Helper.compareObjects(this.getStrasse(), other.getStrasse()) ||
				!Helper.compareObjects(this.getHausnummer(), other.getHausnummer()) ||
				getStockwerke() != other.getStockwerke() ||
				getKaufpreis() != other.getKaufpreis() ||
				isGarten() != other.isGarten())
		{
			return false;
		}
		
		return true;
	}
}
