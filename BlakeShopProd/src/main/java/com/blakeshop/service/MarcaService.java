package com.blakeshop.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.blakeshop.entity.Marca;
import com.blakeshop.repository.MarcaRepository;

@Service
public class MarcaService {

	@Autowired
	MarcaRepository marcaRepository;
	
	public Page<Marca> list(Pageable page){
		return marcaRepository.findAll(page);
	}
	
	public Optional<Marca> getOne(int id){
		return marcaRepository.findById(id);
	}
	
	public Optional<Marca> getOneByNombre(String nombre){
		
		return marcaRepository.findByNombre(nombre);
		
	}
	
	public boolean exists(int id) {
		return marcaRepository.existsById(id);
	}
	
	public boolean existsByNombre(String nombre) {
		return marcaRepository.existsByNombre(nombre);
	}
	
	public void saveMarca(Marca marca) {
		this.marcaRepository.save(marca);
	}
	
	public void delete(int id) {
		marcaRepository.deleteById(id);
	}
	
}
