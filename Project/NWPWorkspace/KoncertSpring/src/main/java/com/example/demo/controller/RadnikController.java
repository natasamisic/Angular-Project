package com.example.demo.controller;

import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
import model.Omiljenikoncerti;
import model.Slika;
import model.Zanr;

@CrossOrigin
@Controller
@RequestMapping(value="/radnik/")
public class RadnikController {
	
	@Autowired
	KoncertRepository koncertRepo;
	@Autowired
	ZanrRepository zanrRepo;
	@Autowired
	KorisnikRepository korisnikRepo;
	@Autowired
	UlogaRepository ulogaRepo;
	@Autowired
	KartaRepository kartaRepo;
	@Autowired
	KomentarRepository komentarRepo;
	@Autowired
	OmiljeniKoncertRepository omiljeniKoncertRepo;
	@Autowired
	SlikaRepository slikaRepo;
	
	
	@RequestMapping(value="dodajKoncert", method=RequestMethod.POST)
	public ResponseEntity<Boolean> dodajKoncert(@RequestParam("nazivZanra") String nazivZanra, @RequestParam("izvodjac") String izvodjac, @RequestParam("grad") String grad, @RequestParam("adresa") String adresa, @RequestParam("opis") String opis, @RequestParam("trajanje") int trajanje, @RequestParam("cena") double cena, @RequestParam("datumIzvodjenja") String datumIzvodjenja) {
		Date datum = null;
		try {
			datum = new SimpleDateFormat("yyyy-MM-dd").parse(datumIzvodjenja);
		}catch(Exception e ) {
			e.printStackTrace();
		}
		Koncert koncert = new Koncert();
		koncert.setAdresa(adresa);
		koncert.setCena(cena);
		koncert.setDatumIzvodjenja(datum);
		koncert.setGrad(grad);
		koncert.setIzvodjac(izvodjac);
		koncert.setMaxBrojKarata(100);
		koncert.setOpis(opis);
		koncert.setTrajanje(trajanje);
		Zanr zanr = zanrRepo.findByNaziv(nazivZanra);
		koncert.setZanr(zanr);
		koncert = koncertRepo.save(koncert);
		if(koncert != null) {
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}else {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="sacuvajSliku/{idKoncerta}", method=RequestMethod.POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<Boolean> sacuvajSliku(@PathVariable int idKoncerta, @RequestParam("slika") MultipartFile file) {
		Koncert k = koncertRepo.findById(idKoncerta).get();
		if(file != null) {
			String fileName = file.getOriginalFilename();
			try {
				String filePath = System.getProperty("user.dir");
				File imageFile = new File(filePath+"/src/main/webapp/images", fileName);	
				file.transferTo(imageFile);
				Slika slika = new Slika();
				slika.setKoncert(k);
				slika.setSlika(Files.readAllBytes(imageFile.toPath()));
				Slika s = slikaRepo.save(slika);
				if(s != null) {
					return new ResponseEntity<Boolean>(true, HttpStatus.OK);
				}else {
					return new ResponseEntity<Boolean>(false, HttpStatus.OK);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new ResponseEntity<Boolean>(false, HttpStatus.OK);
	}

	
	@RequestMapping(value="azurirajKoncert", method=RequestMethod.POST)
	public ResponseEntity<Boolean> azurirajKoncert(@RequestParam("idKoncerta") int idKoncerta, @RequestParam("adresa") String adresa, @RequestParam("trajanje") int trajanje, @RequestParam("cena") double cena, @RequestParam("datumIzvodjenja") String datumIzvodjenja) {
		Koncert koncert = koncertRepo.findById(idKoncerta).get();
		Date datum = null;
		try {
			datum = new SimpleDateFormat("yyyy-MM-dd").parse(datumIzvodjenja);
		}catch(Exception e ) {
			e.printStackTrace();
		}
		koncert.setZanr(koncert.getZanr());
		koncert.setIzvodjac(koncert.getIzvodjac());
		koncert.setDatumIzvodjenja(datum);
		koncert.setTrajanje(trajanje);
		koncert.setGrad(koncert.getGrad());
		koncert.setAdresa(adresa);
		koncert.setOpis(koncert.getOpis());
		koncert.setCena(cena);
		Koncert azuriran = koncertRepo.save(koncert);
		if(azuriran != null) {
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(false, HttpStatus.OK);
	}
	
	@RequestMapping(value="obrisiKoncert/{idKoncerta}", method=RequestMethod.POST)
	public ResponseEntity<Boolean> obrisiKoncert(@PathVariable int idKoncerta) {
		Koncert koncert = koncertRepo.findById(idKoncerta).get();
		List<Komentar> komentari = komentarRepo.findByKoncert(koncert);
		for(Komentar k:komentari) {
			komentarRepo.delete(k);
		}
		List<Omiljenikoncerti> omiljeni = omiljeniKoncertRepo.findByKoncert(koncert);
		for(Omiljenikoncerti o:omiljeni) {
			omiljeniKoncertRepo.delete(o);
		}
		List<Karta> karte = kartaRepo.findByKoncert(koncert);
		for(Karta k:karte) {
			kartaRepo.delete(k);
		}
		List<Slika> slike = slikaRepo.findByKoncert(koncert);
		for(Slika s:slike) {
			slikaRepo.delete(s);
		}
		koncertRepo.delete(koncert);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	@RequestMapping(value="getBrojKarataZaKoncert/{idKoncerta}", method=RequestMethod.GET)
	public ResponseEntity<Integer> getBrojKarataZaKoncert(@PathVariable int idKoncerta) {
		Koncert koncert = koncertRepo.findById(idKoncerta).get();
		return new ResponseEntity<Integer>(koncert.getKartas().size(), HttpStatus.OK);
	}
	
	@RequestMapping(value="getKarteKoncerta/{idKoncerta}", method=RequestMethod.GET)
	public ResponseEntity<List<Karta>> getKarteKoncerta(@PathVariable Integer idKoncerta) {
		Koncert koncert = koncertRepo.findById(idKoncerta).get();		
		return new ResponseEntity<List<Karta>>(koncert.getKartas(), HttpStatus.OK);
	}
	
}
