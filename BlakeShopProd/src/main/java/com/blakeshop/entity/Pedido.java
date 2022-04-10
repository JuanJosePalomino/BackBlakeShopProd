package com.blakeshop.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.blakeshop.security.entity.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "pedido")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pedido")
	private int id_pedido;
	
	@NotNull
	@Column(name = "order_id", unique = true)
	private String orderId;
	
	@NotNull
	@Column(name = "fecha")
	private Date fecha;
	
	@NotNull
	@Column(name = "estado_pago")
	private String estadoPago;
	
	@NotNull
	@Column(name = "estado_envio")
	private String estadoEnvio;
	
	/*
	@ManyToMany
	@JoinTable(name = "detalle_pedido", joinColumns = @JoinColumn(name = "id_pedido",nullable = false),
			   inverseJoinColumns = @JoinColumn(name = "id_producto",nullable = false))
	private List<Producto> productos;
	*/
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_informacion_envio", unique = true)
	private InformacionEnvio informacionEnvio;
	
	
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	private List<DetallePedido> detalles;
	
	@JsonIgnore
	@ManyToOne()
	@JoinColumn(name = "id")
	private Usuario usuario;
	
	public Pedido() {
		
	}


	public Pedido(@NotNull String orderId, @NotNull Date fecha, InformacionEnvio informacionEnvio) {
		super();
		this.orderId = orderId;
		this.fecha = fecha;
		this.informacionEnvio = informacionEnvio;
	}



	public int getId_pedido() {
		return id_pedido;
	}

	public void setId_pedido(int id_pedido) {
		this.id_pedido = id_pedido;
	}

	public String getOrderId() {
		return orderId;
	}


	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
	

	public String getEstadoPago() {
		return estadoPago;
	}


	public void setEstadoPago(String estadoPago) {
		this.estadoPago = estadoPago;
	}


	public String getEstadoEnvio() {
		return estadoEnvio;
	}


	public void setEstadoEnvio(String estadoEnvio) {
		this.estadoEnvio = estadoEnvio;
	}


	public InformacionEnvio getInformacionEnvio() {
		return informacionEnvio;
	}

	public void setInformacionEnvio(InformacionEnvio informacionEnvio) {
		this.informacionEnvio = informacionEnvio;
	}

	public List<DetallePedido> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<DetallePedido> detalles) {
		this.detalles = detalles;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
	
	
	
	
	
	
	
}

