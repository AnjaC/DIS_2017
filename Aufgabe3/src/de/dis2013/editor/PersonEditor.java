package de.dis2013.editor;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Query;
import org.hibernate.Session;


import de.dis2013.core.ImmoService;
import de.dis2013.data.Person;
import de.dis2013.menu.Menu;
import de.dis2013.menu.PersonSelectionMenu;
import de.dis2013.util.FormUtil;

/**
 * Klasse für die Menüs zur Verwaltung von Personen
 */
public class PersonEditor {
	private static final SessionFactory sessionFactory;
	static{
		sessionFactory=new Configuration().configure().buildSessionFactory();
	}
	///Immobilienservice, der genutzt werden soll
//	private ImmoService service;
//	
//	public PersonEditor(ImmoService service) {
//		this.service = service;
//	}
	
	/**
	 * Zeigt die Personenverwaltung
	 */
	public void showPersonMenu() {
		//Menüoptionen
		final int NEW_PERSON = 0;
		final int EDIT_PERSON = 1;
		final int DELETE_PERSON = 2;
		final int BACK = 3;
		
		//Personenverwaltungsmenü
		Menu maklerMenu = new Menu("Personen-Verwaltung");
		maklerMenu.addEntry("Neue Person", NEW_PERSON);
		maklerMenu.addEntry("Person bearbeiten", EDIT_PERSON);
		maklerMenu.addEntry("Person löschen", DELETE_PERSON);
		maklerMenu.addEntry("Zurück zum Hauptmenü", BACK);
		
		//Verarbeite Eingabe
		while(true) {
			int response = maklerMenu.show();
			
			switch(response) {
				case NEW_PERSON:
					newPerson();
					break;
				case EDIT_PERSON:
					editPerson();
					break;
				case DELETE_PERSON:
					deletePerson();
					break;
				case BACK:
					return;
			}
		}
	}
	
	/**
	 * Legt eine neue Person an, nachdem der Benutzer
	 * die entprechenden Daten eingegeben hat.
	 */
	public void newPerson() {
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		Person p = new Person();
		
		p.setVorname(FormUtil.readString("Vorname"));
		p.setNachname(FormUtil.readString("Nachname"));
		p.setAdresse(FormUtil.readString("Adresse"));
		//session.persist(p);
		session.save(p);
		//service.addPerson(p);
		//session.getTransaction().commit();
		
		System.out.println("Person mit der ID "+p.getId()+" wurde erzeugt.");
		
		//session.getTransaction().begin();
		@SuppressWarnings("unchecked")
		List<Person> listPersonen = session.getNamedQuery("alle_Personen").list();

		for (Person person : listPersonen)
		{

			System.out.println(person);
		}
		session.getTransaction().commit();
	}
	
	/**
	 * Editiert eine Person, nachdem der Benutzer sie ausgewählt hat
	 */
	
	public void editPerson() {
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<Person> listPersonen = session.getNamedQuery("alle_Personen").list();

		for (Person person : listPersonen)
		{

			System.out.println(person);
		}
		System.out.println("Geben den ID der Person ein:");
		Scanner scan= new Scanner(System.in);
		int id=scan.nextInt();
		Person person=(Person)session.get(Person.class, id);
		
		String newVorname = FormUtil.readString("Vorname ("+person.getVorname()+")");
		String newNachname = FormUtil.readString("Nachname ("+person.getNachname()+")");
		String newAddress = FormUtil.readString("Adresse ("+person.getAdresse()+")");
		
		//Neue Daten setzen
		if(!newVorname.equals(""))
			person.setVorname(newVorname);
		if(!newNachname.equals(""))
			person.setNachname(newNachname);
		if(!newAddress.equals(""))
			person.setAdresse(newAddress);
	
		session.update(person);
		session.getTransaction().commit();
//		//Personenauswahlmenü
//		Menu personSelectionMenu = new PersonSelectionMenu("Person bearbeiten", service.getAllPersons());
//		int id = personSelectionMenu.show();
//		
//		//Person barbeiten?
//		if(id != PersonSelectionMenu.BACK) {
//			//Person laden
//			Person p = service.getPersonById(id);
//			System.out.println("Person "+p.getVorname()+" "+p.getNachname()+" wird bearbeitet. Leere Felder bleiben unverändert.");
//			
//			//Neue Daten einlesen
//			String newVorname = FormUtil.readString("Vorname ("+p.getVorname()+")");
//			String newNachname = FormUtil.readString("Nachname ("+p.getNachname()+")");
//			String newAddress = FormUtil.readString("Adresse ("+p.getAdresse()+")");
//			
//			//Neue Daten setzen
//			if(!newVorname.equals(""))
//				p.setVorname(newVorname);
//			if(!newNachname.equals(""))
//				p.setNachname(newNachname);
//			if(!newAddress.equals(""))
//				p.setAdresse(newAddress);
		}
//	}
	
	/**
	 * Löscht eine Person, nachdem der Benutzer
	 * die entprechende ID eingegeben hat.
	 */
	public void deletePerson() {
//		//Auswahl der Person
//		Menu personSelectionMenu = new PersonSelectionMenu("Person bearbeiten", service.getAllPersons());
//		int id = personSelectionMenu.show();
//		
//		//Löschen, falls nicht "zurück" gewählt wurde
//		if(id != PersonSelectionMenu.BACK) {
//			Person p = service.getPersonById(id);
//			service.deletePerson(p);
//		}
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<Person> listPersonen = session.getNamedQuery("alle_Personen").list();

		for (Person person : listPersonen)
		{

			System.out.println(person);
		}
		System.out.println("Geben den ID der Person ein:");
		Scanner scan= new Scanner(System.in);
		int id=scan.nextInt();
		Person p=(Person)session.get(Person.class, id);
		p.setId(id);
		session.delete(p);
		session.getTransaction().commit();

	}
		
}
