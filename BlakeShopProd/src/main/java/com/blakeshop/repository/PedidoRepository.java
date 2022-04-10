package com.blakeshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blakeshop.entity.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer>{

	Optional<Pedido> findByOrderId(String order_id);
}
