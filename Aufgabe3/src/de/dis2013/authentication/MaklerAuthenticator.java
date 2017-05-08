package de.dis2013.authentication;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import de.dis2013.core.ImmoService;
import de.dis2013.data.Makler;
import de.dis2013.data.Person;
import de.dis2013.util.FormUtil;

/**
 * Authentifiziert einen Makler
 */
public class MaklerAuthenticator implements Authenticator {
	private static final SessionFactory sessionFactory;
	static{
		sessionFactory=new Configuration().configure().buildSessionFactory();
	}
	private ImmoService service;
	private Makler lastAuthenticatedMakler;
	
	/**
	 * Konstruktor
	 * @param service Immobilien-Service zum Auffinden des entsprechenden Maklers
	 */
//	public MaklerAuthenticator(ImmoService service) {
//		this.service = service;
//	}
	
	/**
	 * Gibt das Makler-Objekt zum letzten erfolgreich authentisierten Makler zurück
	 */
	public Makler getLastAuthenticatedMakler() {
		return this.lastAuthenticatedMakler;
	}
	
	/**
	 * Fragt nach Makler-Login und -Passwort und überprüft die Eingabe
	 */	  
	public boolean authenticate() {
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		boolean ret=true;
		
		String login = FormUtil.readString("Makler-Login");
		String password = FormUtil.readPassword("Passwort");
		
		Makler makler=(Makler) session.createQuery("from Makler where login LIKE :login").setParameter("login", "%"+login+"%").uniqueResult();
//	Query query=session.createSQLQuery("select passwort from Makler where login LIKE :login");
//	query.setParameter("login", "%"+login+"%");
//	query.getQueryString();
	
System.out.println(makler.getPasswort());
		
//		Makler m = service.getMaklerByLogin(login);
//		Makler m= new Makler();

		session.getTransaction().commit();
		
		if(makler == null)
			ret = false;
		else
			ret = password.equals(makler.getPasswort());
		
		lastAuthenticatedMakler = makler;
		
		if(!ret)
			FormUtil.showMessage("Benutzername oder Passwort falsch!");
		
		return ret;
	}
	
}
