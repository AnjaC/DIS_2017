package de.dis2013.data;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import de.dis2013.util.Helper;

/**
 * Makler-Bean
 */
@Entity
@Table(name="makler",uniqueConstraints=
	@UniqueConstraint(columnNames={"login"}))
public class Makler {
	private int id;
	private String name;
	private String adresse;
	private String login;
	private String passwort;
	static int currentId = 0;
	private Set<Immobilie> immobilien;
	
	public Makler() {
	}
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column(name="id", nullable=false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
@Column(name="name", nullable=false)	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
@Column(name="addresse", nullable=false)
	public String getAdresse() {
		return adresse;
	}
	
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
@Column(name="login",unique=true)

	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
@Column(name="passwort", nullable=false)	
	public String getPasswort() {
		return passwort;
	}
	
	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	public Set<Immobilie> getImmobilien() {
		return immobilien;
	}
	@Column(name="Immobilie", nullable=false)	
	public void setImmobilien(Set<Immobilie> immobilien) {
		this.immobilien = immobilien;
	}
	 @Override
	  public String toString()
	  {
		String makler="Name:"+this.name+""+"ID:"+this.id;
	    return makler;
	  }
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		
		result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
		result = prime * result + ((getAdresse() == null) ? 0 : getAdresse().hashCode());
		result = prime * result + ((getLogin() == null) ? 0 : getLogin().hashCode());
		result = prime * result + ((getPasswort() == null) ? 0 : getPasswort().hashCode());
		
		return result;
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		if (obj == null || !(obj instanceof Makler))
			return false;
	
		Makler other = (Makler)obj;
	
		if(other.getId() != getId() ||
				!Helper.compareObjects(getName(), other.getName()) ||
				!Helper.compareObjects(getAdresse(), other.getAdresse()) ||
				!Helper.compareObjects(getLogin(), other.getLogin()) ||
				!Helper.compareObjects(getPasswort(), other.getPasswort()))
		{
			return false;
		}
		
		return true;
	}
}
