package com.blakeshop.dto;


import com.blakeshop.entity.Pedido;
import com.blakeshop.entity.Producto;
import com.blakeshop.entity.TallaProducto;

public class DetallePedidoDto {
	
	
	private int id_detalle;
	
	private Pedido pedido;
	
	private Producto producto;
	
	private TallaProducto talla;
	
	private int cantidad;
	
	private int oferta;
	
	private double precio;

	public DetallePedidoDto() {
		
	}

	
	
	public DetallePedidoDto(int id_detalle, Pedido pedido, Producto producto, TallaProducto talla, int cantidad, int oferta,
			double precio) {
		super();
		this.id_detalle = id_detalle;
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

	public TallaProducto getTalla() {
		return talla;
	}

	public void setTalla(TallaProducto talla) {
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
