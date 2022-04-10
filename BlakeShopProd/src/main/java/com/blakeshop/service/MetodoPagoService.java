package com.blakeshop.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.blakeshop.entity.MetodoPago;
import com.blakeshop.repository.MetodoPagoRepository;



@Service
public class MetodoPagoService {

	@Autowired
	MetodoPagoRepository metodoPagoRepository;
	
	public Page<MetodoPago> list(Pageable page){
		
		return metodoPagoRepository.findAll(page);
	}
	
	public Optional<MetodoPago> getOne(int id){
		
		return metodoPagoRepository.findById(id);
	}
	
	public boolean exists(int id) {
		
		return metodoPagoRepository.existsById(id);
	}
	
	public void saveMetodoPago(MetodoPago metodoPago) {
		this.metodoPagoRepository.save(metodoPago);
	}
	
	public void delete(int id) {
		
		metodoPagoRepository.deleteById(id);
	}
}
