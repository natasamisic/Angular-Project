package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Korisnik;
import model.Uloga;

public interface KorisnikRepository extends JpaRepository<Korisnik, Integer>{
	public List<Korisnik> findByUloga(Uloga u);
	public Korisnik findByUsername(String username);
}
