package com.blakeshop.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "detalle_pedido")
public class DetallePedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_detalle")
	private int id_detalle;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_pedido")
	private Pedido pedido;
	
	@ManyToOne
	@JoinColumn(name = "id_producto")
	private Producto producto;
	
	@Column(name = "talla")
	private String talla;
	
	@Column(name = "cantidad")
	private int cantidad;
	
	@Column(name = "oferta", columnDefinition = "integer default 0")
	private int oferta;
	
	@Column(name = "precio")
	private double precio;
	
	
	public DetallePedido() {
		
	}
	

	public DetallePedido(Pedido pedido, Producto producto, String talla, int cantidad, int oferta, double precio) {
		super();
		this.pedido = pedido;
		this.producto = producto;
		this.talla = talla;
		this.cantidad = cantidad;
		this.oferta = oferta;
		this.precio = precio;
	}




	public int getId_detalle() {
		return id_detalle;
	}

	public void setId_detalle(int id_detalle) {
		this.id_detalle = id_detalle;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public String getTalla() {
		return talla;
	}

	public void setTalla(String talla) {
		this.talla = talla;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	
	
	public int getOferta() {
		return oferta;
	}


	public void setOferta(int oferta) {
		this.oferta = oferta;
	}


	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	
}
