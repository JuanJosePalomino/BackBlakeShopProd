package com.blakeshop.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blakeshop.entity.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{

	boolean existsByNombre(String nombre);

	boolean existsBySeccion(String seccion);
	
	boolean existsByDescripcion(String descripcion);
	
	Optional<Categoria> findByNombre(String nombre);
	

}
