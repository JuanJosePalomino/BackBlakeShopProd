package com.blakeshop.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "categoria")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_categoria")
	private int id_categoria;

	@NotNull
	@Column(name = "nombre")
	private String nombre;
	

	@Column(name = "descripcion")
	private String descripcion;
	
	@NotNull
	@Column(name = "seccion")
	private String seccion;
	
	@JsonIgnore
	@OneToMany(mappedBy = "categoria")
	private List<Producto> productos;
	
	
	public Categoria() {
		
	}
	
	
	


	public Categoria(int id_categoria, @NotNull String nombre, String descripcion, @NotNull String seccion) {
		super();
		this.id_categoria = id_categoria;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.seccion = seccion;
	}





	public int getId_categoria() {
		return id_categoria;
	}

	public void setId_categoria(int id_categoria) {
		this.id_categoria = id_categoria;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
	public String getSeccion() {
		return seccion;
	}


	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}


	public List<Producto> getProductos() {
		return productos;
	}


	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}


	public void setProducto(Producto producto) {
		this.productos.add(producto);
	}
	
}


