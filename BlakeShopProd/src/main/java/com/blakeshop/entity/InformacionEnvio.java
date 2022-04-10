package com.blakeshop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "envio")
public class InformacionEnvio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_informacion_envio;
	
	@NotNull
	@Column
	private String email;
	
	@NotNull
	@Column
	private String nombre;
	
	@NotNull
	@Column
	private String apellidos;
	
	@NotNull
	@Column
	private String identificacion;
	
	@NotNull
	@Column
	private String telefono;
	
	@NotNull
	@Column
	private String direccion;
	
	@Column
	private String apartamento;
	
	@NotNull
	@Column
	private String ciudad;

	@Column
	private String codigo_postal;
	
	@NotNull
	@Column
	private String pais;
	
	@NotNull
	@Column
	private String provincia;
	
	
	public InformacionEnvio() {
		
	}


	public InformacionEnvio(@NotNull String email, @NotNull String nombre, @NotNull String apellidos,
			@NotNull String identificacion, @NotNull String telefono, @NotNull String direccion, String apartamento,
			@NotNull String ciudad, String codigo_postal, @NotNull String pais, @NotNull String provincia) {
		super();
		this.email = email;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.identificacion = identificacion;
		this.telefono = telefono;
		this.direccion = direccion;
		this.apartamento = apartamento;
		this.ciudad = ciudad;
		this.codigo_postal = codigo_postal;
		this.pais = pais;
		this.provincia = provincia;
	}


	public int getId_informacion_envio() {
		return id_informacion_envio;
	}


	public void setId_informacion_envio(int id_informacion_envio) {
		this.id_informacion_envio = id_informacion_envio;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellidos() {
		return apellidos;
	}


	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}


	public String getIdentificacion() {
		return identificacion;
	}


	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public String getApartamento() {
		return apartamento;
	}


	public void setApartamento(String apartamento) {
		this.apartamento = apartamento;
	}


	public String getCiudad() {
		return ciudad;
	}


	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}


	public String getCodigo_postal() {
		return codigo_postal;
	}


	public void setCodigo_postal(String codigo_postal) {
		this.codigo_postal = codigo_postal;
	}


	public String getPais() {
		return pais;
	}


	public void setPais(String pais) {
		this.pais = pais;
	}


	public String getProvincia() {
		return provincia;
	}


	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	
	
	

}
