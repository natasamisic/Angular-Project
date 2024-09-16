package com.example.demo.converter;

import org.springframework.core.convert.converter.Converter;

import com.example.demo.repository.ZanrRepository;

import model.Zanr;

public class ZanrConverter implements Converter<String, Zanr>{

	ZanrRepository zr;
	
	public ZanrConverter(ZanrRepository zr) {
		this.zr = zr;
	}
	
	@Override
	public Zanr convert(String source) {
		int id = -1;
		try {
			id = Integer.parseInt(source);
		}catch(NumberFormatException e) {
			return null;
		}
		Zanr z = zr.findById(id).get();
		return z;
	}

}
