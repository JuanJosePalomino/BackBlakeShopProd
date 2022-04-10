package com.blakeshop.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
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

import com.blakeshop.entity.ImagenProductoEspecial;
import com.blakeshop.entity.Producto;
import com.blakeshop.service.CloudinaryService;
import com.blakeshop.service.ImagenProductoEspecialService;
import com.blakeshop.service.ProductoService;


@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/blakeshop")
public class ImagenProducoEspecialController {
	
	@Autowired
	ImagenProductoEspecialService imagenProductoEspecialService;
	
	@Autowired
	CloudinaryService cloudinaryService;
	
	@Autowired
	ProductoService productoService;
	
	@GetMapping("/imagenesProductoEspecial")
	public ResponseEntity<ImagenProductoEspecial> imagenesProductoEspecial(){
		
		return new ResponseEntity(imagenProductoEspecialService.list(), HttpStatus.OK);
	
	}	
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/subirImagenProductoEspecial")
	public ResponseEntity<?> uploadImagenSlider(@RequestParam MultipartFile multipartfile, @RequestParam("idProducto") int id ) throws IOException{
		
		BufferedImage bi = ImageIO.read(multipartfile.getInputStream());
		
		if(bi == null) {
			return new ResponseEntity("Imagen inválida", HttpStatus.BAD_REQUEST);
		}
		
		Producto producto = this.productoService.getOne(id).get();
		
		this.imagenProductoEspecialService.deleteAll();
		
		Map result = cloudinaryService.upload(multipartfile);
		
		ImagenProductoEspecial imagen = new ImagenProductoEspecial((String)result.get("original_filename"),(String)result.get("url"),(String)result.get("public_id"));
	
		imagen.setProducto(producto);
		
		imagenProductoEspecialService.saveImagenProductoEspecial(imagen);
		
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/eliminarImagenProductoEspecial")
	public ResponseEntity<?> eliminarImagenSlider(@RequestParam("idImagenProductoEspecialEliminar") int id) throws IOException{
		
		if(!imagenProductoEspecialService.exists(id)) {
			return new ResponseEntity("Esta imagen no existe en la base de datos", HttpStatus.BAD_REQUEST);
		}
		
		Optional<ImagenProductoEspecial> imagenProductoEspecial = imagenProductoEspecialService.getOne(id);
		
		Map result = cloudinaryService.delete(imagenProductoEspecial.get().getImagen_producto_especial_id());
		
		imagenProductoEspecialService.delete(id);
		
		return new ResponseEntity(HttpStatus.OK);
	}
}
