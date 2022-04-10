package com.blakeshop.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "marca")
public class Marca {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_marca")
	private int id_marca;
	
	@NotNull
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "descripcion")
	private String descripcion;

	@JsonIgnore
	@OneToMany(mappedBy = "marca")
	private  List<Producto> productos;
	
	public Marca() {
		
	}


	public Marca(int id_marca, @NotNull String nombre, String descripcion) {
		super();
		this.id_marca = id_marca;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}





	public int getId_marca() {
		return id_marca;
	}

	public void setId_marca(int id_marca) {
		this.id_marca = id_marca;
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


	public List<Producto> getProductos() {
		return productos;
	}


	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	
}
