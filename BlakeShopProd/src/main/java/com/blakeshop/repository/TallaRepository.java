package com.blakeshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blakeshop.entity.Talla;

@Repository
public interface TallaRepository extends JpaRepository<Talla, Integer>{

	boolean existsByTalla(String talla);
}
