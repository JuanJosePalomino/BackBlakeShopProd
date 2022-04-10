package com.blakeshop.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blakeshop.entity.Marca;
import com.blakeshop.entity.Talla;
import com.blakeshop.service.TallaService;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/blakeshop")
public class TallaController {
	
	@Autowired
	TallaService tallaService;

	@GetMapping("/tallas")
	public ResponseEntity<Page<Talla>> tallas(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "2000") int size,
			@RequestParam(defaultValue = "nombre") String order,
			@RequestParam(defaultValue = "true") boolean asc){
		
		Page<Talla> tallas = this.tallaService.list(PageRequest.of(page, size, Sort.by(order)));
		
		if(!asc) {
			tallas = this.tallaService.list(PageRequest.of(page, size, Sort.by(order).descending()));
		}
		
		return ResponseEntity.ok(tallas);
	}
	
	@GetMapping("/talla")
	public ResponseEntity<Talla> talla(@RequestParam("idTallaBuscar") int id){
		
		  Optional<Talla> talla = this.tallaService.getOne(id);
		  
		  if(talla.isPresent()) {
			  return ResponseEntity.ok(talla.get());
		  }else {
			  return ResponseEntity.notFound().build();
		  }
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/insertarTalla")
	public ResponseEntity<Talla> insertarTalla(@Valid @RequestBody Talla talla, BindingResult bindingResult){
		
		if(bindingResult.hasErrors())
			return new ResponseEntity("Error en los campos", HttpStatus.BAD_REQUEST);
		if(tallaService.existsByTalla(talla.getTalla()))
			return new ResponseEntity("Esta talla ya existe", HttpStatus.BAD_REQUEST);
		
		this.tallaService.saveTalla(talla);
		
		return ResponseEntity.ok(talla);
		
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/actualizarTalla")
	public ResponseEntity<Talla> actualizarTalla(@Valid @RequestBody Talla talla, BindingResult bindingResult){
	
		if(bindingResult.hasErrors())
			return new ResponseEntity("Error en los campos", HttpStatus.BAD_REQUEST);
		if(tallaService.existsByTalla(talla.getTalla()))
			return new ResponseEntity("Esta talla ya existe", HttpStatus.BAD_REQUEST);
		
		this.tallaService.saveTalla(talla);
		
		return ResponseEntity.ok(talla);
	
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("eliminarTalla")
	public void eliminarTalla(@RequestParam("idTallaEliminar") int id){
		
		this.tallaService.delete(id);
		
	}
	
}
