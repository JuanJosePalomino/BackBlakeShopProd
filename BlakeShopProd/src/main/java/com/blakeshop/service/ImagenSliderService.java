package com.blakeshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blakeshop.entity.ImagenSlider;
import com.blakeshop.repository.ImagenSliderRepository;

@Service
public class ImagenSliderService {
	
	@Autowired
	ImagenSliderRepository imagenSliderRepository;
	
	public List<ImagenSlider> list(){
		
		return imagenSliderRepository.findAll();
	}
	
	public Optional<ImagenSlider> getOne(int id) {
		
		return imagenSliderRepository.findById(id);
	}
	
	public void saveImagenSlider(ImagenSlider imagenSlider) {
		imagenSliderRepository.save(imagenSlider);
	}
	
	public boolean exists(int id) {
		
		return imagenSliderRepository.existsById(id);
	}
	
	public void delete(int id) {
		
		imagenSliderRepository.deleteById(id);
	}
}
