package managers;

import javax.persistence.EntityManager;

import model.Koncert;
import model.Korisnik;
import model.Uloga;
import model.Zanr;

public class Manager {
	
	public Korisnik unosKorisnika(String ime, String prezime, String email, String username, String password, Integer uloga) {
		try {
			EntityManager em = JPAUtil.getEntityManager();
			
			Korisnik k = new Korisnik();
			k.setIme(ime);
			k.setPrezime(prezime);
			k.setEmail(email);
			k.setUsername(username);
			k.setPassword(password);
			Uloga u = em.find(Uloga.class, uloga);
			k.setUloga(u);
			
			em.getTransaction().begin();
			em.persist(k);
			em.getTransaction().commit();
			return k;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Koncert unosKoncerta(String izvodjac, int trajanje, String opis, int maxKarata, String grad, String adresa, int idZanr) {
		try {
			EntityManager em = JPAUtil.getEntityManager();
			
			Koncert k = new Koncert();
			k.setIzvodjac(izvodjac);
			k.setTrajanje(trajanje);
			k.setOpis(opis);
			k.setMaxBrojKarata(maxKarata);
			k.setGrad(grad);
			k.setAdresa(adresa);
			Zanr z = em.find(Zanr.class, idZanr);
			k.setZanr(z);
			
			em.getTransaction().begin();
			em.persist(k);
			em.getTransaction().commit();
			return k;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		Manager m = new Manager();
		Korisnik k = m.unosKorisnika("Marko", "Markovic", "mail@mail.com", "maki", "123", 1);
		if(k!=null) {
			System.out.println("Uspesno");
		}
//		Koncert k = m.unosKoncerta("Rihanna", 120, "Prvi put u Beogradu Rihanna! Ne propustite ovu specijalnu priliku!", 800, "Beograd", "Tolstojeva 15", 1);
//		if(k!=null) {
//			System.out.println("Uspesno");
//		}
	}

}
