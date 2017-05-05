package de.dis2013.editor;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import de.dis2013.core.ImmoService;
import de.dis2013.data.Haus;
import de.dis2013.data.Makler;
import de.dis2013.data.Person;
import de.dis2013.data.Wohnung;
import de.dis2013.menu.AppartmentSelectionMenu;
import de.dis2013.menu.HouseSelectionMenu;
import de.dis2013.menu.Menu;
import de.dis2013.util.FormUtil;

/**
 * Klasse für die Menüs zur Verwaltung von Immobilien
 */
public class ImmobilienEditor {
	private static final SessionFactory sessionFactory;
	static{
		sessionFactory=new Configuration().configure().buildSessionFactory();
	}
	///Immobilienservice, der genutzt werden soll
	private ImmoService service;
	
	///Wird als Verwalter für die Immobilien eingetragen
	private Makler verwalter;
	
	public ImmobilienEditor(ImmoService service, Makler verwalter) {
		this.service = service;
		this.verwalter = verwalter;
	}
	
	/**
	 * Zeigt das Immobilien-Hauptmenü
	 */
	public void showImmoMenu() {
		//Menüoptionen
		final int NEW_HOUSE = 0;
		final int EDIT_HOUSE = 1;
		final int DELETE_HOUSE = 2;
		final int NEW_APPARTMENT = 3;
		final int EDIT_APPARTMENT = 4;
		final int DELETE_APPARTMENT = 5;
		final int BACK = 6;
		
		//Immobilienverwaltungsmenü
		Menu maklerMenu = new Menu("Immobilien-Verwaltung");
		maklerMenu.addEntry("Neues Haus anlegen", NEW_HOUSE);
		maklerMenu.addEntry("Haus bearbeiten", EDIT_HOUSE);
		maklerMenu.addEntry("Haus löschen", DELETE_HOUSE);
		
		maklerMenu.addEntry("Neue Wohnung anlegen", NEW_APPARTMENT);
		maklerMenu.addEntry("Wohnung bearbeiten", EDIT_APPARTMENT);
		maklerMenu.addEntry("Wohnung löschen", DELETE_APPARTMENT);
		
		maklerMenu.addEntry("Zurück zum Hauptmenü", BACK);
		
		//Verarbeite Eingabe
		while(true) {
			int response = maklerMenu.show();
			
			switch(response) {
				case NEW_HOUSE:
					newHouse();
					break;
				case EDIT_HOUSE:
					editHouse();
					break;
				case DELETE_HOUSE:
					deleteHouse();
					break;
				case NEW_APPARTMENT:
					newAppartment();
					break;
				case EDIT_APPARTMENT:
					editAppartment();
					break;
				case DELETE_APPARTMENT:
					deleteAppartment();
					break;
				case BACK:
					return;
			}
		}
	}
	
	/**
	 * Abfrage der Daten für ein neues Haus
	 */
	public void newHouse() {
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		Haus h = new Haus();
		
		h.setOrt(FormUtil.readString("Ort"));
		h.setPlz(FormUtil.readInt("PLZ"));
		h.setStrasse(FormUtil.readString("Straße"));
		h.setHausnummer(FormUtil.readString("Hausnummer"));
		h.setFlaeche(FormUtil.readInt("Fläche"));
		h.setStockwerke(FormUtil.readInt("Stockwerke"));
		h.setKaufpreis(FormUtil.readInt("Kaufpreis"));
		h.setGarten(FormUtil.readBoolean("Garten"));
		
		List<Makler> listMakler = session.getNamedQuery("alle_Makler").list();

		for (Makler makler : listMakler)
		{

			System.out.println(makler);
		}
		
		System.out.println("Geben den ID des Maklers ein:");
		Scanner scan= new Scanner(System.in);
		int id=scan.nextInt();
		Makler makler=(Makler)session.get(Makler.class, id);
		h.setVerwalter(makler);
		
		session.save(h);
	session.getTransaction().commit();}
	
	
	/**
	 * Lässt den Benutzer ein Haus zum bearbeiten auswählen
	 * und fragt anschließend die neuen Daten ab.
	 */
	public void editHouse() {
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<Haus> listHaus = session.getNamedQuery("alle_haeuser").list();

		for (Haus h : listHaus)
		{

			System.out.println(h);
		}
		System.out.println("Geben den ID des Hauses ein:");
		Scanner scan= new Scanner(System.in);
		int id=scan.nextInt();
		Haus h=(Haus)session.get(Haus.class, id);
		//Alle Häuser suchen, die vom Makler verwaltet werden
//		Set<Haus> haeuser = service.getAllHaeuserForMakler(verwalter);
//		
//		//Auswahlmenü für das zu bearbeitende Haus
//		HouseSelectionMenu hsm = new HouseSelectionMenu("Liste der verwalteten Häuser", haeuser);
//		int id = hsm.show();
//		
//		//Falls nicht der Eintrag "zurück" gewählt wurde, Haus bearbeiten
//		if(id != HouseSelectionMenu.BACK) {
//			//Gewähltes Haus laden
//			Haus h = service.getHausById(id);
//			
//			System.out.println("Haus "+h.getStrasse()+" "+h.getHausnummer()+", "+h.getPlz()+" "+h.getOrt()+" wird bearbeitet. Leere Felder bzw. Eingabe von 0 lässt Feld unverändert.");
//			
			//Neue Daten abfragen
			String newOrt = FormUtil.readString("Ort ("+h.getOrt()+")");
			int newPlz = FormUtil.readInt("PLZ ("+h.getPlz()+")");
			String newStrasse = FormUtil.readString("Straße ("+h.getStrasse()+")");
			String newHausNummer = FormUtil.readString("Hausnummer ("+h.getHausnummer()+")");
			int newFlaeche = FormUtil.readInt("Fläche ("+h.getFlaeche()+")");
			int newStockwerke = FormUtil.readInt("Stockwerke ("+h.getStockwerke()+")");
			int newKaufpreis = FormUtil.readInt("Kaufpreis ("+h.getKaufpreis()+")");
			boolean newGarten = FormUtil.readBoolean("Garten ("+(h.isGarten() ? "j" : "n")+")");
			
			//Neue Daten setzen
			if(!newOrt.equals(""))
				h.setOrt(newOrt);
			
			if(!newStrasse.equals(""))
				h.setStrasse(newStrasse);
			
			if(!newHausNummer.equals(""))
				h.setHausnummer(newHausNummer);
			
			if(newPlz != 0)
				h.setPlz(newPlz);
			
			if(newFlaeche != 0)
				h.setFlaeche(newFlaeche);
			
			if(newStockwerke != 0)
				h.setStockwerke(newStockwerke);
			
			if(newKaufpreis != 0)
				h.setKaufpreis(newKaufpreis);
			
			h.setGarten(newGarten);
			session.update(h);
			session.getTransaction().commit();
		}
	
	
	/**
	 * Zeigt die Liste von verwalteten Häusern und löscht das
	 * entsprechende Haus nach Auswahl
	 */
	public void deleteHouse() {
//		//Alle Häuser suchen, die vom Makler verwaltet werden
//		Set<Haus> haeuser = service.getAllHaeuserForMakler(verwalter);
//		
//		//Auswahlmenü für das zu bearbeitende Haus
//		HouseSelectionMenu hsm = new HouseSelectionMenu("Liste der verwalteten Häuser", haeuser);
//		int id = hsm.show();
//		
//		//Falls nicht der Eintrag "zurück" gewählt wurde, Haus löschen
//		if(id != HouseSelectionMenu.BACK) {
//			Haus h = service.getHausById(id);
//			service.deleteHouse(h);
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<Haus> listHaus = session.getNamedQuery("alle_haeuser").list();

		for (Haus h : listHaus)
		{

			System.out.println(h);
		}
		System.out.println("Geben den ID des Hauses ein:");
		Scanner scan= new Scanner(System.in);
		int id=scan.nextInt();
		Haus h=(Haus)session.get(Haus.class, id);
		h.setId(id);
		session.delete(h);
		session.getTransaction().commit();

	}
		
	
	
	/**
	 * Abfrage der Daten für eine neue Wohnung
	 */
	public void newAppartment() {
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		

		Wohnung w = new Wohnung();
		
		w.setOrt(FormUtil.readString("Ort"));
		w.setPlz(FormUtil.readInt("PLZ"));
		w.setStrasse(FormUtil.readString("Straße"));
		w.setHausnummer(FormUtil.readString("Hausnummer"));
		w.setFlaeche(FormUtil.readInt("Fläche"));
		w.setStockwerk(FormUtil.readInt("Stockwerk"));
		w.setMietpreis(FormUtil.readInt("Mietpreis"));
		w.setEbk(FormUtil.readBoolean("EBK"));
		w.setBalkon(FormUtil.readBoolean("Balkon"));
		w.setVerwalter(this.verwalter);
		
		List<Makler> listMakler = session.getNamedQuery("alle_Makler").list();

		for (Makler makler : listMakler)
		{

			System.out.println(makler);
		}
		
		System.out.println("Geben den ID des Maklers ein:");
		Scanner scan= new Scanner(System.in);
		int id=scan.nextInt();
		Makler makler=(Makler)session.get(Makler.class, id);
		w.setVerwalter(makler);
		
		session.save(w);
	session.getTransaction().commit();
	}
		
//		service.addWohnung(w);
	
	
	/**
	 * Lässt den Benutzer eine Wohnung zum bearbeiten auswählen
	 * und fragt anschließend die neuen Daten ab.
	 */
	public void editAppartment() {
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<Wohnung> listWohnung = session.getNamedQuery("alle_wohnungen").list();

		for (Wohnung w : listWohnung)
		{

			System.out.println(w);
		}
		System.out.println("Geben den ID der Wohnung ein:");
		Scanner scan= new Scanner(System.in);
		int id=scan.nextInt();
		Wohnung w=(Wohnung)session.get(Wohnung.class, id);
//		//Alle Wohnungen suchen, die vom Makler verwaltet werden
//		Set<Wohnung> wohnungen = service.getAllWohnungenForMakler(verwalter);
//		
//		//Auswahlmenü für die zu bearbeitende Wohnung
//		AppartmentSelectionMenu asm = new AppartmentSelectionMenu("Liste der verwalteten Wohnungen", wohnungen);
//		int id = asm.show();
//		
//		//Falls nicht der Eintrag "zurück" gewählt wurde, Wohnung bearbeiten
//		if(id != AppartmentSelectionMenu.BACK) {
//			//Wohnung laden
//			Wohnung w = service.getWohnungById(id);
//			
//			System.out.println("Haus "+w.getStrasse()+" "+w.getHausnummer()+", "+w.getPlz()+" "+w.getOrt()+" wird bearbeitet. Leere Felder bzw. Eingabe von 0 lässt Feld unverändert.");
//			
			//Neue Daten abfragen
			String newOrt = FormUtil.readString("Ort ("+w.getOrt()+")");
			int newPlz = FormUtil.readInt("PLZ ("+w.getPlz()+")");
			String newStrasse = FormUtil.readString("Straße ("+w.getStrasse()+")");
			String newHausNummer = FormUtil.readString("Hausnummer ("+w.getHausnummer()+")");
			int newFlaeche = FormUtil.readInt("Fläche ("+w.getFlaeche()+")");
			int newStockwerk = FormUtil.readInt("Stockwerk ("+w.getStockwerk()+")");
			int newMietpreis = FormUtil.readInt("Mietpreis ("+w.getMietpreis()+")");
			boolean newEbk = FormUtil.readBoolean("EBK ("+(w.isEbk() ? "j" : "n")+")");
			boolean newBalkon = FormUtil.readBoolean("Balkon ("+(w.isBalkon() ? "j" : "n")+")");
			
			//Neue Daten setzen
			if(!newOrt.equals(""))
				w.setOrt(newOrt);
			
			if(!newStrasse.equals(""))
				w.setStrasse(newStrasse);
			
			if(!newHausNummer.equals(""))
				w.setHausnummer(newHausNummer);
			
			if(newPlz != 0)
				w.setPlz(newPlz);
			
			if(newFlaeche != 0)
				w.setFlaeche(newFlaeche);
			
			if(newStockwerk != 0)
				w.setStockwerk(newStockwerk);
			
			if(newMietpreis != 0)
				w.setMietpreis(newMietpreis);
			
			w.setEbk(newEbk);
			w.setBalkon(newBalkon);
			session.update(w);
			session.getTransaction().commit();
		}
	
	
	/**
	 * Zeigt die Liste von verwalteten Wohnungen und löscht die
	 * entsprechende Wohnung nach Auswahl
	 */
	public void deleteAppartment() {
//		//Alle Wohnungen suchen, die vom Makler verwaltet werden
//		Set<Wohnung> wohnungen = service.getAllWohnungenForMakler(verwalter);
//		
//		//Auswahlmenü für die zu bearbeitende Wohnung
//		AppartmentSelectionMenu asm = new AppartmentSelectionMenu("Liste der verwalteten Wohnungen", wohnungen);
//		int id = asm.show();
//		
//		//Falls nicht der Eintrag "zurück" gewählt wurde, Wohnung löschen
//		if(id != HouseSelectionMenu.BACK) {
//			Wohnung w = service.getWohnungById(id);
//			service.deleteWohnung(w);
//		}
//	}
//}
		Session session=sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<Wohnung> listWohnung = session.getNamedQuery("alle_wohnungen").list();

		for (Wohnung w : listWohnung)
		{

			System.out.println(w);
		}
		System.out.println("Geben den ID der Wohnung ein:");
		Scanner scan= new Scanner(System.in);
		int id=scan.nextInt();
		Wohnung w=(Wohnung)session.get(Wohnung.class, id);
		w.setId(id);
		session.delete(w);
		session.getTransaction().commit();

	}}
