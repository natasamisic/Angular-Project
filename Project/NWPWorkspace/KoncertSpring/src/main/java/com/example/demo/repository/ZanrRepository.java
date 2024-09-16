package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Zanr;

public interface ZanrRepository extends JpaRepository<Zanr, Integer>{
	public Zanr findByNaziv(String zanr);
}
