package com.blakeshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blakeshop.entity.Producto;
import com.blakeshop.entity.TallaProducto;

@Repository
public interface TallaProductoRepository extends JpaRepository<TallaProducto, Integer>{
	
	void deleteAllByProducto(Producto producto);
	
}
