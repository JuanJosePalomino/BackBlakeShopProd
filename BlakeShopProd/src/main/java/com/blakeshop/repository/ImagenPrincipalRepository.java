package com.blakeshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blakeshop.entity.ImagenPrincipal;

@Repository
public interface ImagenPrincipalRepository extends JpaRepository<ImagenPrincipal, Integer> {
	

}
