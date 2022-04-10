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

import com.blakeshop.entity.MetodoPago;
import com.blakeshop.service.MetodoPagoService;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/blakeshop")
public class MetodoPagoController {
	
	@Autowired
	MetodoPagoService metodoPagoService;
	
	@GetMapping("/metodos")
	public ResponseEntity<Page<MetodoPago>> metodos(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "2000") int size,
			@RequestParam(defaultValue = "fecha") String order,
			@RequestParam(defaultValue = "true") boolean asc){
		
		Page<MetodoPago> metodos = this.metodoPagoService.list(PageRequest.of(page, size,Sort.by(order)));
		
		if(!asc) {
			metodos = this.metodoPagoService.list(PageRequest.of(page, size,Sort.by(order).descending()));
		}
		
		return ResponseEntity.ok(metodos);
	}
	
	@GetMapping("/metodo")
	public ResponseEntity<MetodoPago> metodo(@RequestParam("idMetodoBuscar") int id){
		
		Optional<MetodoPago> metodo = this.metodoPagoService.getOne(id);
		
		if(metodo.isPresent()) {
			
			return ResponseEntity.ok(metodo.get());
			
		}else {
			
			return ResponseEntity.notFound().build();
			
		}
		
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/insertarMetodo")
	public ResponseEntity<?> insertarMetodo(@Valid @RequestBody MetodoPago metodo, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors())
			
			return new ResponseEntity("Error en los campos", HttpStatus.BAD_REQUEST);
		
		this.metodoPagoService.saveMetodoPago(metodo);
		
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/actualizarMetodo")
	public ResponseEntity<MetodoPago> actualizarMetodo(@Valid @RequestBody MetodoPago metodo, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors())
			return new ResponseEntity("Error en los campos", HttpStatus.BAD_REQUEST);
		
		this.metodoPagoService.saveMetodoPago(metodo);
		return ResponseEntity.ok(metodo);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/eliminarMetodo")
	public void eliminarMetodo(@RequestParam("idMetodoEliminar") int id) {
		this.metodoPagoService.delete(id);
	}
	

}
