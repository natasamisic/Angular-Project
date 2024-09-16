package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Koncert;
import model.Zanr;

public interface KoncertRepository extends JpaRepository<Koncert, Integer>{
	public Koncert findByIzvodjac(String izvodjac);
	public List<Koncert> findByZanr(Zanr z);
	public List<Koncert> findAllByOrderByGrad();
	public List<Koncert> findAllByOrderByDatumIzvodjenja();
}
