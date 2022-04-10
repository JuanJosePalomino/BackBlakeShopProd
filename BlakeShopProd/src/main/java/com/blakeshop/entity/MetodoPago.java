package com.blakeshop.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "metodo")
public class MetodoPago {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_metodo;
	
	@NotNull
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "descripcion")
	private String descripcion;

	@OneToMany
	@JoinColumn(name = "id_metodo")
	private List<Pedido> pedidos;
	
	public MetodoPago() {
		
	}
	
	
	public int getId_metodo() {
		return id_metodo;
	}

	public void setId_metodo(int id_metodo) {
		this.id_metodo = id_metodo;
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


	public List<Pedido> getPedidos() {
		return pedidos;
	}


	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
	
	
}
