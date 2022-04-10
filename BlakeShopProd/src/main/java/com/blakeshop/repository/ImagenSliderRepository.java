package com.blakeshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blakeshop.entity.ImagenSlider;

@Repository
public interface ImagenSliderRepository extends JpaRepository<ImagenSlider, Integer> {

}
