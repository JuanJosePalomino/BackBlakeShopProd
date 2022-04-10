package com.blakeshop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.blakeshop.entity.Categoria;
import com.blakeshop.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	CategoriaRepository categoriaRepository;
	
	public Page<Categoria> list(Pageable page){
		
		return categoriaRepository.findAll(page);
	}
	
	public Optional<Categoria> getOne(int id){
		
		return categoriaRepository.findById(id);
	}
	
	public Optional<Categoria> getOneByNombre(String nombre){
		
		return categoriaRepository.findByNombre(nombre);
	}
	
	public boolean exists(int id) {
		
		return categoriaRepository.existsById(id);
	}
	
	public boolean existsByNombre(String nombre) {
		return categoriaRepository.existsByNombre(nombre);
	}
	
	public boolean existsBySeccion(String seccion) {
		return categoriaRepository.existsBySeccion(seccion);
	}
	
	public boolean existsByDescripcion(String descripcion) {
		return categoriaRepository.existsByDescripcion(descripcion);
	}
	
	public void saveCategoria(Categoria categoria) {
		this.categoriaRepository.save(categoria);
	}
	
	public void delete(int id) {
		
		categoriaRepository.deleteById(id);
	}
	
}
