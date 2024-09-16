package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Koncert;
import model.Korisnik;
import model.Omiljenikoncerti;

public interface OmiljeniKoncertRepository extends JpaRepository<Omiljenikoncerti, Integer>{
	public Omiljenikoncerti findByKoncertAndKorisnik(Koncert k, Korisnik kor);
	public List<Omiljenikoncerti> findByKoncert(Koncert k);
	public List<Omiljenikoncerti> findByKorisnik(Korisnik k);
	public boolean deleteByKoncert(Koncert k);
}
