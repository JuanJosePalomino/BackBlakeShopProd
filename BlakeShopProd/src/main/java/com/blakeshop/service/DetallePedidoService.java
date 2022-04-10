package com.blakeshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blakeshop.entity.DetallePedido;
import com.blakeshop.repository.DetallePedidoRepository;


@Service
public class DetallePedidoService {
	@Autowired
	DetallePedidoRepository detallePedidoRepository;
	
	public List<DetallePedido> list(){
		
		return detallePedidoRepository.findAll();
	}
	
	public Optional<DetallePedido> getOne(int id) {
		
		return detallePedidoRepository.findById(id);
	}
	
	public boolean exists(int id) {
		
		return detallePedidoRepository.existsById(id);
	}
	
	public void saveDetalle(DetallePedido detalle) {
		this.detallePedidoRepository.save(detalle);
	}
	
	public void delete(int id) {
		
		detallePedidoRepository.deleteById(id);
	}
}
