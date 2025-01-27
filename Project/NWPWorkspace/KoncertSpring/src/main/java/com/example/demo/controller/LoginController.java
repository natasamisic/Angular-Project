package com.example.demo.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.repository.KoncertRepository;
import com.example.demo.repository.KorisnikRepository;
import com.example.demo.repository.UlogaRepository;

import classes.Response;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import model.Korisnik;
import model.Uloga;

@CrossOrigin
@Controller
@RequestMapping(value="/auth/")
public class LoginController {
	
	@Autowired
	KorisnikRepository korisnikRepo;
	@Autowired
	UlogaRepository ulogaRepo;
	@Autowired
	KoncertRepository koncertRepo;
	
	@RequestMapping(value="register", method=RequestMethod.POST)
	public ResponseEntity<Boolean> register(@RequestParam("ime") String ime, @RequestParam("prezime") String prezime, @RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("email") String email) {
		Korisnik vecIma = korisnikRepo.findByUsername(username);
		if(vecIma != null) {
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}
		Korisnik korisnik = new Korisnik();
		korisnik.setIme(ime);
		korisnik.setPrezime(prezime);
		korisnik.setUsername(username);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		korisnik.setPassword(passwordEncoder.encode(password));
		Uloga uloga = ulogaRepo.findById(2).get();
		korisnik.setEmail(email);
		korisnik.setUloga(uloga);
		Korisnik kor = korisnikRepo.save(korisnik);
		if(kor != null) {
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(false, HttpStatus.OK);
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ResponseEntity<Response> login(@RequestParam("username") String username, @RequestParam("password") String password, Principal principal) {
		Korisnik korisnik = korisnikRepo.findByUsername(username);
		Response resp = new Response();
		if ( korisnik == null) {
			return new ResponseEntity<Response>(resp, HttpStatus.OK);
		}
		BCryptPasswordEncoder passEncoder = new BCryptPasswordEncoder();
		if ( !passEncoder.matches(password, korisnik.getPassword()))
			return new ResponseEntity<Response>(resp, HttpStatus.OK);
		resp.setIdKorisnika(korisnik.getIdKorisnik());
		resp.setUloga(korisnik.getUloga().getNaziv());
		resp.setToken(getJWTToken(korisnik));
		return new ResponseEntity<Response>(resp, HttpStatus.OK);
	}
	
	private String getJWTToken(Korisnik korisnik) {
		String secretKey = "mySecretKey";
		
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(korisnik.getUloga().getNaziv());
		
		String token = Jwts.builder()
				.setId("softtekJWT")
				.setSubject(korisnik.getUsername())
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return "Bearer " + token;
	}

	
//	@RequestMapping(value = "/logout", method = RequestMethod.GET)
//	public void logout() {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();	
//		if (auth != null) {
//			SecurityContextHolder.getContext().setAuthentication(null);
//		}	
//	}

}
