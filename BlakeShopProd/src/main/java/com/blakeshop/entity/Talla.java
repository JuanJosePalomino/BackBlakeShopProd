package com.blakeshop.entity;


import java.util.List;

import javax.persistence.CascadeType;
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
@Table(name = "talla")
public class Talla{


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_talla")
	private int id_talla;
	
	@NotNull
	@Column(name = "talla", unique=true)
	private String talla;
	
	@JsonIgnore
	@OneToMany(mappedBy = "talla")
	List<TallaProducto> tallasProductos;
	
	
	
	public Talla() {
		
	}
	
	
	
	public Talla(@NotNull String talla) {
		super();
		this.talla = talla;
	}



	public int getId_talla() {
		return id_talla;
	}

	public void setId_talla(int id_talla) {
		this.id_talla = id_talla;
	}

	public String getTalla() {
		return talla;
	}

	public void setTalla(String talla) {
		this.talla = talla;
	}



	public List<TallaProducto> getTallasProductos() {
		return tallasProductos;
	}



	public void setTallasProductos(List<TallaProducto> tallasProductos) {
		this.tallasProductos = tallasProductos;
	}
	
	
	
}
