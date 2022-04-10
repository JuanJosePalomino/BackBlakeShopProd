package com.blakeshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blakeshop.entity.Marca;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Integer>{

	boolean existsByNombre(String nombre);
	
	Optional<Marca> findByNombre(String nombre);
}
