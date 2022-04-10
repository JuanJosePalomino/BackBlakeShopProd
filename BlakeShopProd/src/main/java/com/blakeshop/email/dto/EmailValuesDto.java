package com.blakeshop.email.dto;

public class EmailValuesDto {
	
	
	private String emailFrom;
	
	private String emailTo;
	
	private String subject;
	
	private String nombreUsuario;
	
	private String token;

	public EmailValuesDto() {
		
	}

	public EmailValuesDto(String emailFrom, String emailTo, String subject, String nombreUsuario, String token) {
		super();
		this.emailFrom = emailFrom;
		this.emailTo = emailTo;
		this.subject = subject;
		this.nombreUsuario = nombreUsuario;
		this.token = token;
	}

	public String getEmailFrom() {
		return emailFrom;
	}

	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}

	public String getEmailTo() {
		return emailTo;
	}

	public void setEmailTo(String emailTo) {
		this.emailTo = emailTo;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
	
	
	
}
