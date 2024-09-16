package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Koncert;
import model.Slika;

public interface SlikaRepository extends JpaRepository<Slika, Integer>{
	List<Slika> findByKoncert(Koncert k);
}
