package com.blakeshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blakeshop.entity.Imagen;

@Repository
public interface ImagenRepository extends JpaRepository<Imagen, Integer>{

}
