package com.blakeshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blakeshop.entity.Imagen;
import com.blakeshop.repository.ImagenRepository;

@Service
public class ImagenService {
	
	@Autowired
	ImagenRepository imagenRepository;
	
	public List<Imagen> list(){
		
		return imagenRepository.findAll();
	}
	
	public Optional<Imagen> getOne(int id) {
		
		return imagenRepository.findById(id);
	}
	
	public boolean exists(int id) {
		
		return imagenRepository.existsById(id);
	}
	
	public void delete(int id) {
		
		imagenRepository.deleteById(id);
	}
}
