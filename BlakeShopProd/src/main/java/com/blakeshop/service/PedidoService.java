package com.blakeshop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.blakeshop.entity.Pedido;
import com.blakeshop.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository pedidoRepository;
	
	public Page<Pedido> list(Pageable page){
		
		return pedidoRepository.findAll(page);
	}
	
	public Optional<Pedido> getOne(int id){
		
		return pedidoRepository.findById(id);
	}
	
	public Optional<Pedido> getOneByOrder_id(String order_id){
		return pedidoRepository.findByOrderId(order_id);
	}
	
	public boolean exists(int id) {
		
		return pedidoRepository.existsById(id);
	}
	
	
	public void savePedido(Pedido pedido) {
		this.pedidoRepository.save(pedido);
	}
	public void delete(int id) {
		
		pedidoRepository.deleteById(id);
	}
		
}

