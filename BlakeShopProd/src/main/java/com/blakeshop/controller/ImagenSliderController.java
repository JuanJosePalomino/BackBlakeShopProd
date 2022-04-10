package com.blakeshop.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.blakeshop.entity.ImagenSlider;
import com.blakeshop.service.CloudinaryService;
import com.blakeshop.service.ImagenSliderService;


@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/blakeshop")
public class ImagenSliderController {

	
	@Autowired
	ImagenSliderService imagenSliderService;
	
	@Autowired
	CloudinaryService cloudinaryService;
	
	@GetMapping("/imagenesSlider")
	public ResponseEntity<ImagenSlider> imagenesSlider(){
		
		return new ResponseEntity(imagenSliderService.list(), HttpStatus.OK);
	
	}	
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/subirImagenSlider")
	public ResponseEntity<?> uploadImagenSlider(@RequestParam MultipartFile multipartfile) throws IOException{
		
		BufferedImage bi = ImageIO.read(multipartfile.getInputStream());
		
		if(bi == null) {
			return new ResponseEntity("Imagen inválida", HttpStatus.BAD_REQUEST);
		}
		
		Map result = cloudinaryService.upload(multipartfile);
		
		ImagenSlider imagen = new ImagenSlider((String)result.get("original_filename"),(String)result.get("url"),(String)result.get("public_id"));
	
		imagenSliderService.saveImagenSlider(imagen);
		
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/eliminarImagenSlider")
	public ResponseEntity<?> eliminarImagenSlider(@RequestParam("idImagenSliderEliminar") int id ) throws IOException{
		
		if(!imagenSliderService.exists(id)) {
			return new ResponseEntity("Esta imagen no existe en la base de datos", HttpStatus.BAD_REQUEST);
		}
		
		Optional<ImagenSlider> imagenSlider = imagenSliderService.getOne(id);
		
		Map result = cloudinaryService.delete(imagenSlider.get().getImagen_slider_id());
		
		imagenSliderService.delete(id);
		
		return new ResponseEntity(HttpStatus.OK);
	}
	
}
