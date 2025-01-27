package com.example.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.repository.KoncertRepository;
import com.example.demo.repository.ZanrRepository;

import model.Komentar;
import model.Koncert;
import model.Zanr;

@CrossOrigin
@Controller
@RequestMapping(value="/koncert/")
public class KoncertController {
	
	@Autowired
	KoncertRepository koncertRepo;

	@Autowired
	ZanrRepository zanrRepo;
	
	
	@RequestMapping(value="getSviKoncerti", method=RequestMethod.GET)
	public ResponseEntity<List<Koncert>> getSviKoncerti() {
		List<Koncert> koncerti = koncertRepo.findAll();
//		System.out.println("Koncerti: "+ koncerti.size());
		return new ResponseEntity<List<Koncert>>(koncerti, HttpStatus.OK);
	}
	
	@RequestMapping(value="prikaziKoncert/{idKoncerta}", method=RequestMethod.GET)
	public ResponseEntity<Koncert> prikaziKoncert(@PathVariable Integer idKoncerta) {
		Koncert koncert = koncertRepo.findById(idKoncerta).get();		
		return new ResponseEntity<Koncert>(koncert, HttpStatus.OK);
	}
	
	@RequestMapping(value="nadjiKomentareKoncerta/{idKoncerta}", method=RequestMethod.GET)
	public ResponseEntity<List<Komentar>> nadjiKomentareKoncerta(@PathVariable Integer idKoncerta) {
		Koncert koncert = koncertRepo.findById(idKoncerta).get();		
		return new ResponseEntity<List<Komentar>>(koncert.getKomentars(), HttpStatus.OK);
	}
	
//	@RequestMapping(value="getSlikeKoncerta/{idKoncerta}", method=RequestMethod.GET)
//	public ResponseEntity<List<Slika>> getSlikeKoncerta(@PathVariable Integer idKoncerta) {
//		Koncert koncert = koncertRepo.findById(idKoncerta).get();		
//		return new ResponseEntity<List<Slika>>(koncert.getSlikas(), HttpStatus.OK);
//	}
	
	@RequestMapping(value="nadjiKoncertPoIzvodjacu", method=RequestMethod.GET)
	public ResponseEntity<Koncert> nadjiKoncertPoIzvodjacu(@RequestParam(name="izvodjac") String izvodjac) {
		Koncert koncert = koncertRepo.findByIzvodjac(izvodjac);
		return new ResponseEntity<Koncert>(koncert, HttpStatus.OK);
	}
	
	@RequestMapping(value="getZanroveZaPretragu", method=RequestMethod.GET)
	public ResponseEntity<List<Zanr>> getZanroveZaPretragu() {
		return new ResponseEntity<List<Zanr>>(zanrRepo.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value="getKoncertePoZanru/{idZanra}", method=RequestMethod.GET)
	public ResponseEntity<List<Koncert>> getKoncertePoZanru(@PathVariable Integer idZanra) {
		Zanr z = zanrRepo.findById(idZanra).get();
		List<Koncert> koncerti = koncertRepo.findByZanr(z);
		return new ResponseEntity<List<Koncert>>(koncerti, HttpStatus.OK);
	}
	
	@Autowired ServletContext context;
	
	@RequestMapping(value="getSlike", method=RequestMethod.GET)
	public ResponseEntity<List<String>> getSlike(){
		List<String> images = new ArrayList<String>();
		String filePath = context.getRealPath("/images");
		File fileFolder = new File(filePath);
		if(fileFolder != null) {
			for(final File file: fileFolder.listFiles()) {
				String encodeBase64 = null;
				try {
					String extension = FilenameUtils.getExtension(file.getName());
					FileInputStream fileInputStream = new FileInputStream(file);
					byte[] bytes = new byte[(int) file.length()];
					fileInputStream.read(bytes);
					encodeBase64 = Base64.getEncoder().encodeToString(bytes);
					images.add("data:image/"+extension+";base64,"+encodeBase64);
					fileInputStream.close();
				}catch(Exception e) {
					
				}
			}
		}
		return new ResponseEntity<List<String>>(images, HttpStatus.OK);
	}
	
}
