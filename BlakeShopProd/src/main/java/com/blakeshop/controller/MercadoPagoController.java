package com.blakeshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.blakeshop.dto.DetallesDto;
import com.blakeshop.dto.MensajeDto;
import com.blakeshop.dto.PaymentDto;
import com.blakeshop.entity.DetallePedido;
import com.blakeshop.entity.Pedido;
import com.blakeshop.entity.Producto;
import com.blakeshop.service.MercadoPagoService;
import com.blakeshop.service.ProductoService;
import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Payment;
import com.mercadopago.resources.Payment.Status;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.payment.Order;
import com.mercadopago.resources.datastructures.preference.BackUrls;
import com.mercadopago.resources.datastructures.preference.Item;
import com.mercadopago.resources.datastructures.preference.Shipments;
import com.mercadopago.resources.datastructures.preference.Shipments.ShipmentMode;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/blakeshop")
public class MercadoPagoController {
	
	@Value("${mercadopago.ACCESS_TOKEN}")
	private String token;
	
	@Value("${mercadopago.CLIENT_ID}")
	private String clientId;
	
	@Value("${mercadopago.CLIENT_SECRET}")
	private String secret;
	
	@Autowired
	MercadoPagoService mercadoPagoService;
	
	@Autowired
	ProductoService productoService;
	
	@PostMapping("/crearPreferencia")
	public ResponseEntity<?> crearPreferencia(@RequestBody DetallesDto detalles ) throws MPException {
		
		MercadoPago.SDK.setAccessToken(token);
		MercadoPago.SDK.setClientId(clientId);
		MercadoPago.SDK.setClientSecret(secret);
		
		
		Preference preference =  mercadoPagoService.crearPreferencia(detalles);
		
		
		
		if(preference == null) {
			return new ResponseEntity("Uno o varios de tus productos sobrepasan el stock disponible, vuelve a la tienda y verifica la disponibilidad de los productos", HttpStatus.BAD_REQUEST);
		}
		
		
		
		String initPoint = preference.getInitPoint();
		
		return new ResponseEntity(new MensajeDto(initPoint),HttpStatus.OK);
	}
	
	@GetMapping("/processPayment")
	public ResponseEntity<?> processPayment(@RequestParam("collection_id") String collectionId) throws MPException{
		

		
		Payment payment = Payment.findById(collectionId);
		
		PaymentDto paymentDto = new PaymentDto(payment.getStatus(), payment.getOrder());
		
		
		return new ResponseEntity(paymentDto,HttpStatus.OK);
	
	}
	
	@GetMapping("/success")
	public ResponseEntity<?> failure(@RequestParam("collection_id") String collectionId,
			@RequestParam("collection_status") String collectionStatus,
			@RequestParam("external_reference") String externalReference,
			@RequestParam("payment_type") String paymentType,
			@RequestParam("merchant_order_id") String merchantOrderId,
			@RequestParam("preference_id") String preferenceId,
			@RequestParam("site_id") String siteId,
			@RequestParam("processing_mode") String processingMode,
			@RequestParam("merchant_account_id") String merchantAccountId){
		

		
		
		return null;
	
	}
	
}
