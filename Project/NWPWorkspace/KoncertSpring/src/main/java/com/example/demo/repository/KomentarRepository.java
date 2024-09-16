package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Komentar;
import model.Koncert;
import model.Korisnik;

public interface KomentarRepository extends JpaRepository<Komentar, Integer>{
	public List<Komentar> findByKoncert(Koncert k);
	public void deleteByKoncert(Koncert k);
	public List<Komentar> findByKorisnik(Korisnik k);
}
