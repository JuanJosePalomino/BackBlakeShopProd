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

import com.blakeshop.entity.Categoria;
import com.blakeshop.service.CategoriaService;


@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/blakeshop")
public class CategoriaController {

	@Autowired
	CategoriaService categoriaService;
	
	@GetMapping("categorias")
	public ResponseEntity<Page<Categoria>> categorias(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "2000") int size,
			@RequestParam(defaultValue = "nombre") String order,
			@RequestParam(defaultValue = "true") boolean asc){
		
		Page<Categoria> categorias = this.categoriaService.list(PageRequest.of(page, size, Sort.by(order)));
		
		if(!asc) {
			categorias = this.categoriaService.list(PageRequest.of(page, size, Sort.by(order).descending()));
		}
		
		return ResponseEntity.ok(categorias);
		
	}
	
	@GetMapping("/categoria")
	public ResponseEntity<Categoria> categoria(@RequestParam("idCategoriaBuscar") int id){
		Optional<Categoria> categoria = this.categoriaService.getOne(id);
		
		if(categoria.isPresent()) {
			return ResponseEntity.ok(categoria.get());
		}else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/insertarCategoria")
	public ResponseEntity<?> insertarCategoria(@Valid @RequestBody Categoria categoria, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) 
			return new ResponseEntity("Error en los campos", HttpStatus.BAD_REQUEST);
		
		Optional<Categoria> categoriaValidation = categoriaService.getOneByNombre(categoria.getNombre());
		
		if(categoriaValidation.isPresent()) {
			
			if(categoriaValidation.get().getSeccion().equals(categoria.getSeccion())) {
				
				return new ResponseEntity("Esta categoria ya existe en esa sección", HttpStatus.BAD_REQUEST);
			}
		}
		
		this.categoriaService.saveCategoria(categoria);
		
		return new ResponseEntity(HttpStatus.OK);
		
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/actualizarCategoria")
	public ResponseEntity<Categoria> actualizarCategoria(@Valid @RequestBody Categoria categoria, BindingResult bindingResult){
		
		if(bindingResult.hasErrors())
			
			return new ResponseEntity("Error en los campos", HttpStatus.BAD_REQUEST);
		
		
		Optional<Categoria> categoriaValidation = categoriaService.getOne(categoria.getId_categoria());
		
		
		if(categoriaService.existsByNombre(categoria.getNombre()) && categoriaService.existsBySeccion(categoria.getSeccion()) && categoriaValidation.get().getDescripcion().equals(categoria.getDescripcion())) {
			
		
			return new ResponseEntity("Esta categoria ya existe", HttpStatus.BAD_REQUEST);
			
		}
		
			
		
		this.categoriaService.saveCategoria(categoria);
		
		return ResponseEntity.ok(categoria);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("eliminarCategoria")
	public void eliminarCategoria(@RequestParam("idCategoriaEliminar")int id) {
		this.categoriaService.delete(id);
	}
	
}
