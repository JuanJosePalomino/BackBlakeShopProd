package com.blakeshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blakeshop.entity.ImagenProductoEspecial;
import com.blakeshop.entity.ImagenSlider;
import com.blakeshop.repository.ImagenProductoEspecialRepository;
import com.blakeshop.repository.ImagenSliderRepository;

@Service
public class ImagenProductoEspecialService {
	
	@Autowired
	ImagenProductoEspecialRepository imagenProductoEspecialRepository;
	
	public List<ImagenProductoEspecial> list(){
		
		return imagenProductoEspecialRepository.findAll();
	}
	
	public Optional<ImagenProductoEspecial> getOne(int id) {
		
		return imagenProductoEspecialRepository.findById(id);
	}
	
	public void saveImagenProductoEspecial(ImagenProductoEspecial imagenProductoEspecial) {
		imagenProductoEspecialRepository.save(imagenProductoEspecial);
	}
	
	public boolean exists(int id) {
		
		return imagenProductoEspecialRepository.existsById(id);
	}
	
	public void delete(int id) {
		
		imagenProductoEspecialRepository.deleteById(id);
	}
	
	public void deleteAll() {
		imagenProductoEspecialRepository.deleteAllInBatch();
	}

}
