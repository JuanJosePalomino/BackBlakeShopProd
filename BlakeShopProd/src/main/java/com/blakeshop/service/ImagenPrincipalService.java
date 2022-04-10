package com.blakeshop.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.blakeshop.entity.ImagenPrincipal;
import com.blakeshop.repository.ImagenPrincipalRepository;

@Service
@Transactional
public class ImagenPrincipalService {

	@Autowired
	ImagenPrincipalRepository imagenPrincipalRepository;
	
	public List<ImagenPrincipal> list(){
		
		return imagenPrincipalRepository.findAll();
	}
	
	public Optional<ImagenPrincipal> getOne(int id) {
		
		return imagenPrincipalRepository.findById(id);
	}
	
	public boolean exists(int id) {
		
		return imagenPrincipalRepository.existsById(id);
	}
	
	public void delete(int id) {
		
		imagenPrincipalRepository.deleteById(id);
	}
}

