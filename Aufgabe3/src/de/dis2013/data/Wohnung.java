package de.dis2013.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import de.dis2013.util.Helper;


/**
 * Wohnungs-Bean
 */
@Entity
@Table(name="wohnungen")
public class Wohnung extends Immobilie {
	private int stockwerk;
	private int mietpreis;
	private int zimmer;
	private boolean balkon;
	private boolean ebk;

	public Wohnung() {
		super();
	}
@Column(name="stockwerk", nullable=false)	
	public int getStockwerk() {
		return stockwerk;
	}
	public void setStockwerk(int stockwerk) {
		this.stockwerk = stockwerk;
	}
	@Column(name="mietpreis", nullable=false)	
	public int getMietpreis() {
		return mietpreis;
	}
	public void setMietpreis(int mietpreis) {
		this.mietpreis = mietpreis;
	}
	@Column(name="zimmer", nullable=false)	
	public int getZimmer() {
		return zimmer;
	}
	public void setZimmer(int zimmer) {
		this.zimmer = zimmer;
	}
	@Column(name="balkon", nullable=false)	
	public boolean isBalkon() {
		return balkon;
	}
	public void setBalkon(boolean balkon) {
		this.balkon = balkon;
	}
	@Column(name="ebk", nullable=false)	
	public boolean isEbk() {
		return ebk;
	}
	public void setEbk(boolean ebk) {
		this.ebk = ebk;
	}
	 @Override
	  public String toString()
	  {
		
		String wohnung="Stockwerke:"+this.stockwerk+" "+"Mietpreis:"+this.mietpreis+" "+"Zimmer:"+this.zimmer+"Balkon"+this.balkon+"EbK:"+this.ebk+
				"Ort:"+this.getOrt()+" "+"PLZ:"+this.getPlz()+"Strasse:"+this.getStrasse()+""+"Hausnummer"+this.getHausnummer()+""+"Verwalter:"+this.getVerwalter()+" "+"Flaeche:"+this.getFlaeche()+" "+"ID:"+this.getId();
	    return wohnung;}
	
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		
		result = prime * result + getStockwerk();
		result = prime * result + getMietpreis();
		result = prime * result + getZimmer();
		result = prime * result + ((isBalkon()) ? 1 : 0);
		result = prime * result + ((isEbk()) ? 1 : 0);
		
		return result;
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null || !(obj instanceof Wohnung))
			return false;
	
		Wohnung other = (Wohnung)obj;
	
		if(other.getId() != getId() ||
				other.getPlz() != getPlz() ||
				other.getFlaeche() != getFlaeche() ||
				!Helper.compareObjects(this.getOrt(), other.getOrt()) ||
				!Helper.compareObjects(this.getStrasse(), other.getStrasse()) ||
				!Helper.compareObjects(this.getHausnummer(), other.getHausnummer()) ||
				getStockwerk() != other.getStockwerk() ||
				getMietpreis() != other.getMietpreis() ||
				getZimmer() != other.getZimmer() ||
				isBalkon() != other.isBalkon() ||
				isEbk() != other.isEbk())
		{
			return false;
		}
		
		return true;
	}
}
