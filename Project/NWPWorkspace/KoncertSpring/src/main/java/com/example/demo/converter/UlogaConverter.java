package com.example.demo.converter;

import org.springframework.core.convert.converter.Converter;

import com.example.demo.repository.UlogaRepository;

import model.Uloga;

public class UlogaConverter implements Converter<String, Uloga>{

	UlogaRepository ur;
	
	public UlogaConverter(UlogaRepository ur) {
		this.ur = ur;
	}
	
	@Override
	public Uloga convert(String source) {
		int id = -1;
		try {
			id = Integer.parseInt(source);
		} catch (NumberFormatException nfe) {
			return null;
		}
		Uloga uloga = ur.findById(id).get();
		return uloga;
	}

}
