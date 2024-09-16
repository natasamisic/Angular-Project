package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.Karta;
import model.Koncert;
import model.Korisnik;

public interface KartaRepository extends JpaRepository<Karta, Integer> {
	public List<Karta> findByKoncert(Koncert k);
	public List<Karta> findByKorisnik(Korisnik k);
	
	@Query("select k from Karta k where k.korisnik.idKorisnik=:id")
	public List<Karta> rezervisaneKarte(@Param("id")Integer id);
	
	@Query("delete from Karta k where k.koncert.idKoncert=:id")
	public void deleteByKoncert(@Param("id")Integer id);
}
