package com.blakeshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPConfException;
import com.mercadopago.exceptions.MPException;

@SpringBootApplication
public class BlakeShopApplication {
	

	public static void main(String[] args){
		SpringApplication.run(BlakeShopApplication.class, args);
	
	}

}
