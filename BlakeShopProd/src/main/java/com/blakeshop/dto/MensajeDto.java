package com.blakeshop.dto;

public class MensajeDto {

	private String sandboxInitPoint;

	public MensajeDto() {
		
	}
	
	public MensajeDto(String sandboxInitPoint) {
		super();
		this.sandboxInitPoint = sandboxInitPoint;
	}

	public String getSandboxInitPoint() {
		return sandboxInitPoint;
	}

	public void setSandboxInitPoint(String sandboxInitPoint) {
		this.sandboxInitPoint = sandboxInitPoint;
	}
	
	
	
}	
