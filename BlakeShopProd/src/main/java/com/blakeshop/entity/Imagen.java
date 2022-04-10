package com.blakeshop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

@Entity
@Table(name = "imagen")
public class Imagen {

	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id_imagen")
	private int id_imagen;
	
	@NotNull
	@Column(name = "nombre")
	private String nombre;
	
	@NotNull
	@Column(name ="imagen_url")
	private String imagen_url;
	
	@NotNull
	@Column(name = "imagen_id")
	private String imagen_id;
	
	public Imagen() {
		
	}
	

	public Imagen(String nombre, String imagen_url, String imagen_id) {
		super();
		this.nombre = nombre;
		this.imagen_url = imagen_url;
		this.imagen_id = imagen_id;
	}



	public int getId_imagen() {
		return id_imagen;
	}

	public void setId_imagen(int id_imagen) {
		this.id_imagen = id_imagen;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getImagen_url() {
		return imagen_url;
	}

	public void setImagen_url(String imagen_url) {
		this.imagen_url = imagen_url;
	}

	public String getImagen_id() {
		return imagen_id;
	}

	public void setImagen_id(String imagen_id) {
		this.imagen_id = imagen_id;
	}
	
	
}
