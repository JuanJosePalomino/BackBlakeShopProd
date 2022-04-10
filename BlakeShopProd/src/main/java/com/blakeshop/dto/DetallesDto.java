package com.blakeshop.dto;

import java.util.List;

import com.blakeshop.entity.DetallePedido;
import com.blakeshop.entity.InformacionEnvio;
import com.blakeshop.security.entity.Usuario;

public class DetallesDto {

	private String orderId;
	
	private InformacionEnvio informacionEnvio;
	
	private String nombreUsuario;
	
	private List<DetallePedidoDto> detalles;

	
	public DetallesDto() {
		
	}

	public DetallesDto(String orderId, InformacionEnvio informacionEnvio, String nombreUsuario,
			List<DetallePedidoDto> detalles) {
		super();
		this.orderId = orderId;
		this.informacionEnvio = informacionEnvio;
		this.nombreUsuario = nombreUsuario;
		this.detalles = detalles;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public InformacionEnvio getInformacionEnvio() {
		return informacionEnvio;
	}

	public void setInformacionEnvio(InformacionEnvio informacionEnvio) {
		this.informacionEnvio = informacionEnvio;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public List<DetallePedidoDto> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<DetallePedidoDto> detalles) {
		this.detalles = detalles;
	}

	
}
