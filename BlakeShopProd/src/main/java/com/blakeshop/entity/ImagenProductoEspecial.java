package com.blakeshop.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "imagen_producto_especial")
public class ImagenProductoEspecial {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_imagen_producto_especial;
	
	@NotNull
	@Column()
	private String nombre;
	
	@NotNull
	@Column()
	private String imagen_producto_especial_url;
	
	@NotNull
	@Column()
	private String imagen_producto_especial_id;
	
	@OneToOne()
	@JoinColumn(name = "id_producto", unique = true)
	private Producto producto;
	
	public ImagenProductoEspecial() {
		
	}

	

	public ImagenProductoEspecial(@NotNull String nombre,
			@NotNull String imagen_producto_especial_url, @NotNull String imagen_producto_especial_id) {
		super();
		this.id_imagen_producto_especial = id_imagen_producto_especial;
		this.nombre = nombre;
		this.imagen_producto_especial_url = imagen_producto_especial_url;
		this.imagen_producto_especial_id = imagen_producto_especial_id;
	}



	public int getId_imagen_producto_especial() {
		return id_imagen_producto_especial;
	}

	public void setId_imagen_producto_especial(int id_imagen_producto_especial) {
		this.id_imagen_producto_especial = id_imagen_producto_especial;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getImagen_producto_especial_url() {
		return imagen_producto_especial_url;
	}

	public void setImagen_producto_especial_url(String imagen_producto_especial_url) {
		this.imagen_producto_especial_url = imagen_producto_especial_url;
	}

	public String getImagen_producto_especial_id() {
		return imagen_producto_especial_id;
	}

	public void setImagen_producto_especial_id(String imagen_producto_especial_id) {
		this.imagen_producto_especial_id = imagen_producto_especial_id;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	
	
	
	
}


	
