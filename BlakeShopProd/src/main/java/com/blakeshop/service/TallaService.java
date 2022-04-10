package com.blakeshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.blakeshop.entity.Talla;
import com.blakeshop.repository.TallaRepository;



@Service
public class TallaService {

	@Autowired
	TallaRepository tallaRepository;
	
	public Page<Talla> list(Pageable page){
		
		return tallaRepository.findAll(page);
	}
	
	public Optional<Talla> getOne(int id){
		
		return tallaRepository.findById(id);
	}
	
	public boolean exists(int id) {
		
		return tallaRepository.existsById(id);
	}
	
	public boolean existsByTalla(String talla) {
		return tallaRepository.existsByTalla(talla);
	}
	
	public void saveTalla(Talla talla) {
		this.tallaRepository.save(talla);
	}
	
	public void delete(int id) {
		
		tallaRepository.deleteById(id);
	}
}
