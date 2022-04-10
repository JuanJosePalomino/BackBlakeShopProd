package com.blakeshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blakeshop.entity.ImagenProductoEspecial;

@Repository
public interface ImagenProductoEspecialRepository extends JpaRepository<ImagenProductoEspecial, Integer> {

}
