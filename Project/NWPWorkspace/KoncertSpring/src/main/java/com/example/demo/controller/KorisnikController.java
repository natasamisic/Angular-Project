package com.example.demo.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.repository.KartaRepository;
import com.example.demo.repository.KomentarRepository;
import com.example.demo.repository.KoncertRepository;
import com.example.demo.repository.KorisnikRepository;
import com.example.demo.repository.OmiljeniKoncertRepository;
import com.example.demo.repository.SlikaRepository;
import com.example.demo.repository.UlogaRepository;
import com.example.demo.repository.ZanrRepository;

import model.Karta;
import model.Komentar;
import model.Koncert;
import model.Korisnik;
import model.Omiljenikoncerti;

@CrossOrigin
@Controller
@RequestMapping(value="/korisnik/")
public class KorisnikController {
	@Autowired
	KorisnikRepository korisnikRepo;
	@Autowired
	KoncertRepository koncertRepo;
	@Autowired
	KartaRepository kartaRepo;
	@Autowired
	UlogaRepository ulogaRepo;
	@Autowired
	ZanrRepository zanrRepo;
	@Autowired
	OmiljeniKoncertRepository omiljeniKoncertRepo;
	@Autowired
	KomentarRepository komentarRepo;
	@Autowired
	SlikaRepository slikaRepo;
	
	@RequestMapping(value="rezervisiKartu", method=RequestMethod.POST)
	public ResponseEntity<Boolean> rezervisiKartu(@RequestParam("idKoncerta") int idKoncerta, @RequestParam("idKorisnika") int idKorisnika) {
		Korisnik korisnik = korisnikRepo.findById(idKorisnika).get();
		Koncert koncert = koncertRepo.findById(idKoncerta).get();
		Date date = new Date();
		Timestamp datumRezervacije = new Timestamp(date.getTime());
		Karta k = new Karta();
		k.setKoncert(koncert);
		k.setKorisnik(korisnik);
		k.setDatumRezervacije(datumRezervacije);
		Karta karta = kartaRepo.save(k);
		if(karta != null) {
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(false, HttpStatus.OK);
	}
	
	@RequestMapping(value="getRezervisaneKarte/{idKorisnika}", method=RequestMethod.GET)
	public ResponseEntity<List<Karta>> getRezervisaneKarte(@PathVariable int idKorisnika) {
		Korisnik korisnik = korisnikRepo.findById(idKorisnika).get();
		return new ResponseEntity<List<Karta>>(korisnik.getKartas(), HttpStatus.OK);
	}
	
	@RequestMapping(value="dodajKomentar", method=RequestMethod.POST)
	public ResponseEntity<Boolean> dodajKomentar(@RequestParam("tekst") String tekst, @RequestParam("idKoncerta") int idKoncerta, @RequestParam("idKorisnika") int idKorisnika) {
		Korisnik korisnik = korisnikRepo.findById(idKorisnika).get();
		Koncert koncert = koncertRepo.findById(idKoncerta).get();
		Komentar komentar = new Komentar();
		komentar.setTekst(tekst);
		komentar.setKoncert(koncert);
		komentar.setKorisnik(korisnik);
		Komentar kom = komentarRepo.save(komentar);
		if(kom != null) {
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(false, HttpStatus.OK);
	}
	
	@RequestMapping(value="otkaziRezervaciju/{idKarte}", method=RequestMethod.POST)
	public ResponseEntity<Boolean> otkaziRezervaciju(@PathVariable int idKarte) {
		kartaRepo.deleteById(idKarte);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	@RequestMapping(value="dodajUListuOmiljenih", method=RequestMethod.POST)
	public ResponseEntity<Boolean> dodajUListuOmiljenih(@RequestParam("idKoncerta") int idKoncerta, @RequestParam("idKorisnika") int idKorisnika) {
		Korisnik korisnik = korisnikRepo.findById(idKorisnika).get();
		Koncert koncert = koncertRepo.findById(idKoncerta).get();
		Omiljenikoncerti omiljeniKoncert = new Omiljenikoncerti();
		omiljeniKoncert.setKoncert(koncert);
		omiljeniKoncert.setKorisnik(korisnik);
		Omiljenikoncerti omiljeni = omiljeniKoncertRepo.save(omiljeniKoncert);
		if(omiljeni != null) {
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(false, HttpStatus.OK);
	}
	
	@RequestMapping(value="getListuOmiljenihKoncerata/{idKorisnika}", method=RequestMethod.GET)
	public ResponseEntity<List<Omiljenikoncerti>> getListuOmiljenihKoncerata(@PathVariable int idKorisnika) {
		Korisnik korisnik = korisnikRepo.findById(idKorisnika).get();
		return new ResponseEntity<List<Omiljenikoncerti>>(korisnik.getOmiljenikoncertis(), HttpStatus.OK);
	}
	
	@RequestMapping(value="proveraListeKorisnikaZaKoncert", method=RequestMethod.POST)
	public ResponseEntity<Boolean> proveraListeKorisnikaZaKoncert(@RequestParam("idKoncerta") int idKoncerta, @RequestParam("idKorisnika") int idKorisnika) {
		Koncert koncert = koncertRepo.findById(idKoncerta).get();
		Korisnik korisnik = korisnikRepo.findById(idKorisnika).get();
		Omiljenikoncerti koncertNaListi = omiljeniKoncertRepo.findByKoncertAndKorisnik(koncert, korisnik);
		if(koncertNaListi != null) {
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(false, HttpStatus.OK);
	}
	
	@RequestMapping(value="izbaciIzListeOmiljenih/{idKoncerta}", method=RequestMethod.POST)
	public ResponseEntity<Boolean> izbaciIzListeOmiljenih(@PathVariable int idKoncerta) {
		omiljeniKoncertRepo.deleteById(idKoncerta);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

	@RequestMapping(value="getProfil/{idKorisnika}", method=RequestMethod.GET)
	public ResponseEntity<Korisnik> getProfil(@PathVariable int idKorisnika) {
		return new ResponseEntity<Korisnik>(korisnikRepo.findById(idKorisnika).get(), HttpStatus.OK);
	}
	
	@RequestMapping(value="obrisiKomentar/{idKomentara}", method=RequestMethod.POST)
	public ResponseEntity<Boolean> obrisiKomentar(@PathVariable int idKomentara) {
		komentarRepo.deleteById(idKomentara);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

}	
	
