package com.blakeshop.dto;

import java.sql.Date;
import java.util.List;

import com.blakeshop.entity.Categoria;
import com.blakeshop.entity.DetallePedido;
import com.blakeshop.entity.Imagen;
import com.blakeshop.entity.ImagenPrincipal;
import com.blakeshop.entity.Marca;
import com.blakeshop.entity.TallaProducto;

public class ProductoDto {
	
	private int id_producto;
	
	private String nombre;
	
	private String descripcion;
	
	private double precioUnitario;
	
	private int stock;
	
	private String seccion;
	
	private boolean estado;
	
	private Date fechaCreacion;
	
	private Categoria categoria;
	
	private Marca marca;
	
	private List<Imagen> imagenes;
	
	private ImagenPrincipal imagenPrincipal;
	
	private List<TallaProducto> tallasProductos;
	
	private List<DetallePedido> detalles;
	
	public ProductoDto() {
		
	}

	public ProductoDto(int id_producto, String nombre, String descripcion, double precioUnitario, int stock, String seccion,
			boolean estado, List<TallaProducto> tallasProductos, List<DetallePedido> detalles) {
		super();
		this.id_producto = id_producto;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precioUnitario = precioUnitario;
		this.stock = stock;
		this.seccion = seccion;
		this.estado = estado;
		this.tallasProductos = tallasProductos;
		this.detalles = detalles;
	}

	public int getId_producto() {
		return id_producto;
	}

	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
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

	public double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getSeccion() {
		return seccion;
	}

	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public List<Imagen> getImagenes() {
		return imagenes;
	}

	public void setImagenes(List<Imagen> imagenes) {
		this.imagenes = imagenes;
	}

	public ImagenPrincipal getImagenPrincipal() {
		return imagenPrincipal;
	}

	public void setImagenPrincipal(ImagenPrincipal imagenPrincipal) {
		this.imagenPrincipal = imagenPrincipal;
	}

	public List<TallaProducto> getTallasProductos() {
		return tallasProductos;
	}

	public void setTallasProductos(List<TallaProducto> tallasProductos) {
		this.tallasProductos = tallasProductos;
	}

	public List<DetallePedido> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<DetallePedido> detalles) {
		this.detalles = detalles;
	}

	
	
	
	
	
	
}
