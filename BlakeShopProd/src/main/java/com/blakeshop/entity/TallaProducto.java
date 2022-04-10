package com.blakeshop.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "talla_producto")
public class TallaProducto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_talla_producto")
	private int idTallaProducto;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_producto")
	private Producto producto;
	
	@ManyToOne
	@JoinColumn(name = "id_talla")
	private Talla talla;
	
	
	@Column(name = "stock_talla_producto")
	private int stockTallaProducto;

	public TallaProducto() {
		
	}
	

	public TallaProducto(int idTallaProducto, Producto producto, Talla talla, int stockTallaProducto) {
		super();
		this.idTallaProducto = idTallaProducto;
		this.producto = producto;
		this.talla = talla;
		this.stockTallaProducto = stockTallaProducto;
	}


	public int getIdTallaProducto() {
		return idTallaProducto;
	}


	public void setIdTallaProducto(int idTallaProducto) {
		this.idTallaProducto = idTallaProducto;
	}


	public Producto getProducto() {
		return producto;
	}


	public void setProducto(Producto producto) {
		this.producto = producto;
	}


	public Talla getTalla() {
		return talla;
	}


	public void setTalla(Talla talla) {
		this.talla = talla;
	}


	public int getStockTallaProducto() {
		return stockTallaProducto;
	}


	public void setStockTallaProducto(int stockTallaProducto) {
		this.stockTallaProducto = stockTallaProducto;
	}
	
	
	
}
