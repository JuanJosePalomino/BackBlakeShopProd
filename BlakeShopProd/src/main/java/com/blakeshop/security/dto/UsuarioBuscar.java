package com.blakeshop.security.dto;

import javax.validation.constraints.NotBlank;

public class UsuarioBuscar {

	@NotBlank
	private String nombreUsuario;

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	
	
}
