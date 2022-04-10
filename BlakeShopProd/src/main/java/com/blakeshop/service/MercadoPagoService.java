package com.blakeshop.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.internal.util.MathHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blakeshop.dto.DetallePedidoDto;
import com.blakeshop.dto.DetallesDto;
import com.blakeshop.entity.DetallePedido;
import com.blakeshop.entity.InformacionEnvio;
import com.blakeshop.entity.Pedido;
import com.blakeshop.entity.Producto;
import com.blakeshop.entity.TallaProducto;
import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.Preference.AutoReturn;
import com.mercadopago.resources.datastructures.advancedpayment.ReceiverAddress;
import com.mercadopago.resources.datastructures.preference.AddressReceiver;
import com.mercadopago.resources.datastructures.preference.BackUrls;
import com.mercadopago.resources.datastructures.preference.Identification;
import com.mercadopago.resources.datastructures.preference.Item;
import com.mercadopago.resources.datastructures.preference.Payer;
import com.mercadopago.resources.datastructures.preference.Phone;
import com.mercadopago.resources.datastructures.preference.Shipments;
import com.mercadopago.resources.datastructures.preference.Shipments.ShipmentMode;

@Service
public class MercadoPagoService {
	
	@Autowired
	TallaProductoService tallaProductoService;

	public Preference crearPreferencia(DetallesDto detalles) throws MPException {
		
		
		
		Preference preference = new Preference();
		
		
		preference.setAdditionalInfo("BLAKESHOP");
		
		preference.setBinaryMode(true);
		
		preference.setAutoReturn(AutoReturn.all);
		
		BackUrls backUrls = new BackUrls("http://localhost:4200/checkout/pago", "http://localhost:4200/pendingPayment", "http://localhost:4200/checkout/pago");
		
		preference.setBackUrls(backUrls);
		
		Shipments shipment = new Shipments();
		
		float costoEnvio = 7000;
		
		shipment.setCost(costoEnvio);
		
		shipment.setMode(ShipmentMode.not_specified);
		
		InformacionEnvio informacionEnvio = new InformacionEnvio();
		
		AddressReceiver receiverAddress = new AddressReceiver();
		
		receiverAddress.setStreetName(informacionEnvio.getDireccion());
		
		receiverAddress.setZipCode(informacionEnvio.getCodigo_postal());
		
		receiverAddress.setApartment(informacionEnvio.getApartamento());
		
		shipment.setReceiverAddress(receiverAddress);
		
		preference.setShipments(shipment);
		
		Payer payer = new Payer();
		
		payer.setName(informacionEnvio.getNombre() + " " + informacionEnvio.getApellidos());
		
		payer.setPhone(new Phone().setNumber(informacionEnvio.getTelefono()));
		
		payer.setIdentification(new Identification().setNumber(informacionEnvio.getIdentificacion()));
		
		payer.setEmail(informacionEnvio.getEmail());
		
		preference.setPayer(payer);
		
		
		for(DetallePedidoDto tempDetalle: detalles.getDetalles()) {
			
			Optional<TallaProducto> tempTallaProducto = this.tallaProductoService.getOne(tempDetalle.getTalla().getIdTallaProducto());
			
			if(tempTallaProducto.isPresent()) {
				
				if(tempTallaProducto.get().getStockTallaProducto() < tempDetalle.getCantidad()) {
					return null;
				}
				
			}
			
			Item item = new Item();
			
			Producto producto = tempDetalle.getProducto();
			
			item.setId(Integer.toString(producto.getId_producto()));
			
			item.setTitle(producto.getNombre());
			
			item.setPictureUrl(producto.getImagenPrincipal().getImagen_principal_url());
			
			item.setUnitPrice((float)Math.round(producto.getPrecioUnitario() - (producto.getOferta() * producto.getPrecioUnitario())/100));
			
			item.setQuantity(tempDetalle.getCantidad());
			
			preference.appendItem(item);
			
		}
		
		Preference result = preference.save();
		
		return result;
	}
	
}
