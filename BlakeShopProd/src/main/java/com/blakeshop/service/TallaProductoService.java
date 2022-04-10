package com.blakeshop.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blakeshop.entity.Producto;
import com.blakeshop.entity.TallaProducto;
import com.blakeshop.repository.TallaProductoRepository;

@Service
@Transactional
public class TallaProductoService {

	@Autowired
	TallaProductoRepository tallaProductoRepository;
	
	public List<TallaProducto> list(){
		
		return tallaProductoRepository.findAll();
	}
	
	public Optional<TallaProducto> getOne(int id) {
		
		return tallaProductoRepository.findById(id);
	}
	
	public boolean exists(int id) {
		
		return tallaProductoRepository.existsById(id);
	}
	
	public void saveTallaProducto(TallaProducto tallaProducto) {
		this.tallaProductoRepository.save(tallaProducto);
	}
	
	public void delete(int id) {
		
		tallaProductoRepository.deleteById(id);
	}
	
	public void deleteByProducto(Producto producto) {
		tallaProductoRepository.deleteAllByProducto(producto);
	}
	
}
