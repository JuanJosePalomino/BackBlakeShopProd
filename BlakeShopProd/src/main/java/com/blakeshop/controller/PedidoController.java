package com.blakeshop.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.OrderBy;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blakeshop.dto.DetallePedidoDto;
import com.blakeshop.dto.DetallesDto;
import com.blakeshop.entity.DetallePedido;
import com.blakeshop.entity.Pedido;
import com.blakeshop.entity.Producto;
import com.blakeshop.entity.TallaProducto;
import com.blakeshop.security.entity.Usuario;
import com.blakeshop.security.service.UsuarioService;
import com.blakeshop.service.DetallePedidoService;
import com.blakeshop.service.PedidoService;
import com.blakeshop.service.ProductoService;
import com.blakeshop.service.TallaProductoService;
import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Payment;
import com.mercadopago.resources.Payment.Status;


@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/blakeshop")
public class PedidoController {

	@Autowired
	PedidoService pedidoService;
	
	@Autowired
	DetallePedidoService detallePedidoService;
	
	@Autowired
	UsuarioService usuarioService;

	@Autowired
	TallaProductoService tallaProductoService;
	
	@Autowired
	ProductoService productoService;
	
	@GetMapping("/pedidos")
	public ResponseEntity<Page<Pedido>> pedidos(@RequestParam(defaultValue = "0") int page,
												@RequestParam(defaultValue = "2000") int size,
												@RequestParam(defaultValue = "fecha") String order,
												@RequestParam(defaultValue = "true") boolean asc){
		
		Page<Pedido> pedidos = this.pedidoService.list(PageRequest.of(page, size,Sort.by(order)));
		
		if(!asc) {
			pedidos = this.pedidoService.list(PageRequest.of(page, size,Sort.by(order).descending()));
		}
		
		return ResponseEntity.ok(pedidos);
	}
	
	@GetMapping("/pedido")
	public ResponseEntity<Pedido> pedido(@RequestParam("idPedidoBuscar") int id){
		
		Optional<Pedido> pedido = this.pedidoService.getOne(id);
		
		if(pedido.isPresent()) {
			return ResponseEntity.ok(pedido.get());
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/insertarPedido")
	public ResponseEntity<?> insertarPedido(@RequestBody DetallesDto detallesPedidoDto) throws MPException{
		
		if(this.pedidoService.getOneByOrder_id(detallesPedidoDto.getOrderId()).isPresent()) {
			return new ResponseEntity("Ya se insertó este pedido en la base de datos", HttpStatus.BAD_REQUEST);
		}
		
		Date fechaPedido = new Date(new java.util.Date().getTime());
		
		Pedido pedido = new Pedido(detallesPedidoDto.getOrderId(),fechaPedido, detallesPedidoDto.getInformacionEnvio());
		
		List<DetallePedido> detalles = new ArrayList<DetallePedido>();
		
		for(DetallePedidoDto tempDetalle:detallesPedidoDto.getDetalles()) {
			
			/*Cogemos las respectivas tallas  que corresponden a los productos y los productos y les disminuimos el stock según la cantidad indicada en el pedido*/
			TallaProducto tempTallaProducto = this.tallaProductoService.getOne(tempDetalle.getTalla().getIdTallaProducto()).get();
			
			tempTallaProducto.setStockTallaProducto(tempTallaProducto.getStockTallaProducto() - tempDetalle.getCantidad());
			
			Producto tempProducto = this.productoService.getOne(tempTallaProducto.getProducto().getId_producto()).get();
			
			tempProducto.setStock(tempProducto.getStock() - tempDetalle.getCantidad());
			
			this.tallaProductoService.saveTallaProducto(tempTallaProducto);
			
			this.productoService.saveProducto(tempProducto);
			
			
			DetallePedido detallePedido = new DetallePedido(tempDetalle.getPedido(), tempDetalle.getProducto(), tempDetalle.getTalla().getTalla().getTalla(), tempDetalle.getCantidad(),tempDetalle.getOferta() ,tempDetalle.getPrecio());
			detalles.add(detallePedido);
		}
		
		Optional<Usuario> usuario = usuarioService.getByNombreUsuario(detallesPedidoDto.getNombreUsuario());
		
		if(usuario.isPresent()) {
			
			pedido.setUsuario(usuario.get());
		}
		
		pedido.setDetalles(detalles);
		
		Payment payment = Payment.findById(detallesPedidoDto.getOrderId());
		
		Status status = payment.getStatus();
		
		pedido.setEstadoPago(status.toString());
		
		pedido.setEstadoEnvio("Notificado");
		
		this.pedidoService.savePedido(pedido);
		
		for(DetallePedido tempDetalle:pedido.getDetalles()) {
			
			tempDetalle.setPedido(pedido);
			
			this.detallePedidoService.saveDetalle(tempDetalle);
		}
		
		
		
		
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@PutMapping("/actualizarPedido")
	public Pedido actualizarPedido(@RequestBody Pedido pedido) {
		
		Pedido pedidoActualizar = this.pedidoService.getOne(pedido.getId_pedido()).get();
		
		pedidoActualizar.setEstadoEnvio(pedido.getEstadoEnvio());
		
		this.pedidoService.savePedido(pedidoActualizar);
		
		return pedidoActualizar;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/eliminarPedido")
	public void eliminarPedido(@RequestParam("idPedidoEliminar") int id) {
		this.pedidoService.delete(id);
	}
	
}
