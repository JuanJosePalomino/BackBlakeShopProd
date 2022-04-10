package com.blakeshop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;



@Entity
@Table(name = "imagen_principal")
public class ImagenPrincipal {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="id_imagen_principal")
	private int id_imagen_principal;
	
	@NotNull
	@Column(name = "nombre")
	private String nombre;
	
	@NotNull
	@Column(name ="imagen_principal_url")
	private String imagen_principal_url;
	
	@NotNull
	@Column(name = "imagen_principal_id")
	private String imagen_principal_id;
	
	public ImagenPrincipal() {
		
	}

	
	
	public ImagenPrincipal(String nombre, String imagen_principal_url, String imagen_principal_id) {
		super();
		this.nombre = nombre;
		this.imagen_principal_url = imagen_principal_url;
		this.imagen_principal_id = imagen_principal_id;
	}



	public int getId_imagen_principal() {
		return id_imagen_principal;
	}

	public void setId_imagen_principal(int id_imagen_principal) {
		this.id_imagen_principal = id_imagen_principal;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getImagen_principal_url() {
		return imagen_principal_url;
	}

	public void setImagen_principal_url(String imagen_principal_url) {
		this.imagen_principal_url = imagen_principal_url;
	}

	public String getImagen_principal_id() {
		return imagen_principal_id;
	}

	public void setImagen_principal_id(String imagen_principal_id) {
		this.imagen_principal_id = imagen_principal_id;
	}
	
	
}
