package com.blakeshop.controller;

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
import com.blakeshop.service.MarcaService;

@RestController
@CrossOrigin
@RequestMapping("/blakeshop")
public class MarcaController {
	
	@Autowired
	MarcaService marcaService;
	
	
	@GetMapping("marcas")
	public ResponseEntity<Page<Marca>> marcas(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "2000") int size,
			@RequestParam(defaultValue = "nombre") String order,
			@RequestParam(defaultValue = "true") boolean asc){
		
		Page<Marca> marcas = this.marcaService.list(PageRequest.of(page, size, Sort.by(order)));
		
		if(!asc) {
			marcas = this.marcaService.list(PageRequest.of(page, size, Sort.by(order).descending()));
		}
		
		return ResponseEntity.ok(marcas);
		
	}
	
	
	@GetMapping("/marca")
	public ResponseEntity<Marca> marca(@RequestParam("idMarcaBuscar") int id){
		Optional<Marca> marca = this.marcaService.getOne(id);
		
		if(marca.isPresent()) {
			return ResponseEntity.ok(marca.get());
		}else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/insertarMarca")
	public ResponseEntity<Marca> insertarMarca(@Valid @RequestBody Marca marca, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) 
			return new ResponseEntity("Error en los campos", HttpStatus.BAD_REQUEST);
		if(marcaService.existsByNombre(marca.getNombre()))
			return new ResponseEntity("Esta categoria ya existe", HttpStatus.BAD_REQUEST);
		
		this.marcaService.saveMarca(marca);
		
		return new ResponseEntity(marca,HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/actualizarMarca")
	public ResponseEntity<Marca> actualizarMarca(@Valid @RequestBody Marca marca, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors())
			
			return new ResponseEntity("Error en los campos", HttpStatus.BAD_REQUEST);
		
		if(marcaService.existsByNombre(marca.getNombre()))
			if(!(marcaService.getOneByNombre(marca.getNombre()).get().getId_marca() == marca.getId_marca())) {
				return new ResponseEntity("Esta categoria ya existe", HttpStatus.BAD_REQUEST);
			}
			
		this.marcaService.saveMarca(marca);
		
		return new ResponseEntity(marca,HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("eliminarMarca")
	public void eliminarMarca(@RequestParam("idMarcaEliminar")int id) {
		this.marcaService.delete(id);
	}
	

}
