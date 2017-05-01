package de.dis2013.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import de.dis2013.util.Helper;

/**
 * Personen-Bean
 */
@Entity
@Table(name="personen")
public class Person {
	private int id;
	private String vorname;
	private String nachname;
	private String adresse;
	

	public Person() {
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
@Column(name="vorname", nullable=false)	
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	
@Column(name="nachname", nullable=false)	
	public String getNachname() {
		return nachname;
	}
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}
	 @Override
	  public String toString()
	  {
		String person="Nachname:"+this.nachname+" "+"Vorname:"+this.vorname+" "+"Adresse:"+this.adresse+" "+"ID:"+this.id;
	    return person;
	  }
@Column(name="adresse", nullable=false)	
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		
		result = prime * result + ((getVorname() == null) ? 0 : getVorname().hashCode());
		result = prime * result + ((getNachname() == null) ? 0 : getNachname().hashCode());
		result = prime * result + ((getAdresse() == null) ? 0 : getAdresse().hashCode());
		
		return result;
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null || !(obj instanceof Person))
			return false;
	
		Person other = (Person)obj;
	
		if(other.getId() != getId() ||
				!Helper.compareObjects(this.getVorname(), other.getVorname()) ||
				!Helper.compareObjects(this.getNachname(), other.getNachname()) ||
				!Helper.compareObjects(this.getAdresse(), other.getAdresse()))
		{
			return false;
		}
		
		return true;
	}
}