package com.blakeshop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "imagen_slider")
public class ImagenSlider {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_imagen_slider")
	private int id_imagen_slider;
	
	@NotNull
	@Column(name = "nombre")
	private String nombre;
	
	@NotNull
	@Column(name = "imagen_slider_url")
	private String imagen_slider_url;
	
	@NotNull
	@Column(name = "imagen_slider_id")
	private String imagen_slider_id;

	
	
	
	public ImagenSlider() {
		
	}
	
	public ImagenSlider(@NotNull String nombre, @NotNull String imagen__slider_url,
			@NotNull String imagen_slider_id) {
		super();
		this.nombre = nombre;
		this.imagen_slider_url = imagen__slider_url;
		this.imagen_slider_id = imagen_slider_id;
	}


	public int getId_imagen_slider() {
		return id_imagen_slider;
	}


	public void setId_imagen_slider(int id_imagen_slider) {
		this.id_imagen_slider = id_imagen_slider;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getImagen_slider_url() {
		return imagen_slider_url;
	}


	public void setImagen_slider_url(String imagen_slider_url) {
		this.imagen_slider_url = imagen_slider_url;
	}


	public String getImagen_slider_id() {
		return imagen_slider_id;
	}


	public void setImagen_slider_id(String imagen_slider_id) {
		this.imagen_slider_id = imagen_slider_id;
	}
	
	

}
