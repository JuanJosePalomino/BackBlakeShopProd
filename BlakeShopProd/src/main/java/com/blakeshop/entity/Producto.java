package com.blakeshop.entity;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="producto")
public class Producto{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_producto")
	private int id_producto;

	@NotNull
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@NotNull
	@Column(name = "precio_unitario")
	private double precioUnitario;
	
	@NotNull
	@Column(name = "stock")
	private int stock;
	
	@Column(name = "seccion")
	private String seccion;
	
	@Column(name = "oferta",columnDefinition = "integer default 0")
	private int oferta;
	
	@Column(name = "fecha_creacion")
	private Date fechaCreacion;
	
	@ManyToOne
	@JoinColumn(name = "id_categoria")
	private Categoria categoria;
	

	@ManyToOne
	@JoinColumn(name = "id_marca")
	private Marca marca;
	

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_producto")
	private List<Imagen> imagenes;
	

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_imagen_principal", unique = true)
	private ImagenPrincipal imagenPrincipal;
	
	
	
	@OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
	private List<TallaProducto> tallasProductos;
	
	@JsonIgnore
	@OneToMany(mappedBy = "producto")
	private List<DetallePedido> detalles;
	
	public Producto() {
		
	}



	public Producto(@NotNull String nombre, String descripcion, @NotNull double precioUnitario,
			@NotNull int stock, String seccion, int oferta, List<TallaProducto> tallasProductos) {
		
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precioUnitario = precioUnitario;
		this.stock = stock;
		this.seccion = seccion;
		this.oferta = oferta;
		this.tallasProductos = tallasProductos;
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

	

	public int getOferta() {
		return oferta;
	}



	public void setOferta(int oferta) {
		this.oferta = oferta;
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
