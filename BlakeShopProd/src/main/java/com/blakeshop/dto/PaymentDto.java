package com.blakeshop.dto;

import com.mercadopago.resources.Payment.Status;
import com.mercadopago.resources.datastructures.payment.Order;

public class PaymentDto {

	private Status status;
	
	private Order order;
	
	public PaymentDto() {
		
	}
	
	

	public PaymentDto(Status status, Order order) {
		super();
		this.status = status;
		this.order = order;
	}



	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	
}


