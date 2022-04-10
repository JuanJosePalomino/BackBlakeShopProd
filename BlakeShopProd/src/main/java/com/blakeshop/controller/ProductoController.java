package com.blakeshop.controller;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.imageio.ImageIO;
import javax.validation.Valid;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blakeshop.dto.ProductoDto;
import com.blakeshop.entity.Categoria;
import com.blakeshop.entity.Imagen;
import com.blakeshop.entity.ImagenPrincipal;
import com.blakeshop.entity.Marca;
import com.blakeshop.entity.Producto;
import com.blakeshop.entity.TallaProducto;
import com.blakeshop.repository.TallaProductoRepository;
import com.blakeshop.service.CategoriaService;
import com.blakeshop.service.CloudinaryService;
import com.blakeshop.service.ImagenPrincipalService;
import com.blakeshop.service.ImagenService;
import com.blakeshop.service.MarcaService;
import com.blakeshop.service.ProductoService;
import com.blakeshop.service.TallaProductoService;

import net.bytebuddy.asm.Advice.This;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/blakeshop")
public class ProductoController {

	@Autowired
	ProductoService productoService;
	
	@Autowired
	CloudinaryService cloudinaryService;
	
	@Autowired
	CategoriaService categoriaService;
	
	@Autowired
	ImagenService imagenService;
	
	@Autowired
	ImagenPrincipalService imagenPrincipalService;
	
	@Autowired
	MarcaService marcaService;
	
	@Autowired
	TallaProductoService tallaProductoService;
	
	@GetMapping("/productos")
	public ResponseEntity<Page<Producto>> productos(@RequestParam(defaultValue = "0") int page,
													@RequestParam(defaultValue = "2000") int size,
													@RequestParam(defaultValue = "nombre") String order,
													@RequestParam(defaultValue = "true") boolean asc,
													@RequestParam(required = false) String seccion, 
													@RequestParam(required = false) String marca, 
													@RequestParam(required = false) String categoria,
													@RequestParam(required = false) String oferta ) {
		
		Page<Producto> productos;
		
		PageRequest pageRequest = PageRequest.of(page, size,Sort.by(order));
		
		if(!asc) {
			pageRequest = PageRequest.of(page, size,Sort.by(order).descending());
		}
		
		
		
		if( seccion == null && marca == null && categoria == null && oferta == null) {
			
			productos = this.productoService.listAll(pageRequest);
			
		}else if ( seccion == null && marca != null && categoria != null) {
			
			productos = this.productoService.filtroCategoriaMarca(pageRequest, categoria, marca);
			
		}else if( seccion != null && marca == null && categoria !=null ) {
			
			productos = this.productoService.filtroCategoriaSeccion(pageRequest, categoria, seccion);
			
		}else if( seccion != null && marca != null && categoria == null) {
			
			productos = this.productoService.filtroMarcaSeccion(pageRequest, marca, seccion);
			
		}else if( seccion !=null && marca == null && categoria == null) {
			
			productos = this.productoService.filtroSeccion(pageRequest, seccion);
			
		}else if( seccion == null && marca != null && categoria == null) {
			
			productos = this.productoService.filtroMarca(pageRequest, marca);
			
		}else if ( seccion == null && marca == null && categoria != null) {
			
			productos = this.productoService.filtroCategoria(pageRequest, categoria);
			
		}else if (oferta.equals("oferta")){
			
			if(order.equals("precioUnitario") || order.equals("fechaCreacion")) {
				
				 pageRequest = PageRequest.of(page, size,Sort.by(this.productoService.convertString(order)));
				
				if(!asc) {
					pageRequest = PageRequest.of(page, size,Sort.by(this.productoService.convertString(order)).descending());
				}
				
			}
			
			productos = this.productoService.filtroOferta(pageRequest,0);
			
		}else {
			productos = this.productoService.filtroCategoriaMarcaSeccion(pageRequest, categoria, marca, seccion);
		}
		
		
		
		/*
		if(marca == null) {
			Page<Producto> productos= this.productoService.listAll(PageRequest.of(page, size, Sort.by(order)));
			
			if(!asc) {
				productos = this.productoService.listAll(PageRequest.of(page, size,Sort.by(order).descending()));
			}
			
			return ResponseEntity.ok(productos);
		}
		
		Page<Producto> productos = this.productoService.filtroMarca(PageRequest.of(page, size, Sort.by(order)), marca);

			if(!asc) {
				productos = this.productoService.filtroMarca(PageRequest.of(page, size,Sort.by(order).descending()), marca);
			}
		return ResponseEntity.ok(productos);
		*/
		
		return ResponseEntity.ok(productos);
		
	}
	
	
	
	@GetMapping("/producto")
	public ResponseEntity<Producto> producto(@RequestParam("idProductoBuscar") int id){
		Optional<Producto> producto = this.productoService.getOne(id);
		
		if(producto.isPresent()) {
			return ResponseEntity.ok(producto.get());
		}else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(value = "/insertarProducto",consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<Producto> insertarProducto(@Valid @RequestPart String producto,BindingResult bindingResult ,@RequestPart MultipartFile multipartfile,@RequestPart List<MultipartFile> multipartfiles,@RequestParam("idMarca") int idMarca, @RequestParam("idCategoria") int idCategoria) throws IOException {
				
		if(bindingResult.hasErrors())
		
			return new ResponseEntity("Error en los campos", HttpStatus.BAD_REQUEST);
		
		BufferedImage bi = ImageIO.read(multipartfile.getInputStream());
		
		if(bi == null) {
			return new ResponseEntity("El arhivo subido no es válido",HttpStatus.BAD_REQUEST);
		}
		
		if(productoService.getJson(producto) == null) {
			return new ResponseEntity("El stock total de las tallas superan el stock del producto",HttpStatus.BAD_REQUEST);
		}
		
		Map result = cloudinaryService.upload(multipartfile);
		
		ImagenPrincipal imagenPrincipal = new ImagenPrincipal((String)result.get("original_filename"), (String)result.get("url"),(String)result.get("public_id"));
	
		Producto productoJson = productoService.getJson(producto);
		
		Optional<Marca> marca = this.marcaService.getOne(idMarca);
		
		Optional<Categoria> categoria = this.categoriaService.getOne(idCategoria);
		
		List<Imagen> imagenes= new ArrayList();
		
		List<Map> resultMultiplesImages = this.cloudinaryService.uploadMultipleFiles(multipartfiles);
		
		for(Map resultImage: resultMultiplesImages) {
			
			Imagen imagen = new Imagen((String)resultImage.get("original_filename"),(String)resultImage.get("url"),(String)resultImage.get("public_id"));
			
			imagenes.add(imagen);
		}
		
		productoJson.setMarca(marca.get());
		
		productoJson.setCategoria(categoria.get());
		
		productoJson.setImagenPrincipal(imagenPrincipal);
		
		productoJson.setImagenes(imagenes);
		
		Date fechaCreacion = new Date(new java.util.Date().getTime());
		
		productoJson.setFechaCreacion(fechaCreacion);
		
		
		this.productoService.saveProducto(productoJson);
		
		for(TallaProducto tallaProducto:productoJson.getTallasProductos()) {
			tallaProducto.setProducto(productoJson);
			this.tallaProductoService.saveTallaProducto(tallaProducto);
		}
		
		return new ResponseEntity(productoJson, HttpStatus.OK);
		
		
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/actualizarProducto", method = RequestMethod.PUT,consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<Producto> actualizarProducto(@RequestPart String producto,@RequestPart(name="multipartfile", required = false) MultipartFile multipartfile,@RequestPart(name="multipartfiles", required = false) List<MultipartFile> multipartfiles)throws IOException {
		
		
		Producto productoJson = productoService.getJson(producto);
		
		
		ImagenPrincipal antiguaImagenPrincipal = productoJson.getImagenPrincipal();
		
		if(!(multipartfile==null)) {
			
			this.cloudinaryService.delete(antiguaImagenPrincipal.getImagen_principal_id());
			
			Map result = this.cloudinaryService.upload(multipartfile);
			
			ImagenPrincipal nuevaImagenPrincipal = new ImagenPrincipal((String)result.get("original_filename"), (String)result.get("url"),(String)result.get("public_id"));
			
			productoJson.setImagenPrincipal(nuevaImagenPrincipal);
			
		}
		
		if(!(multipartfiles==null)) {
			
			for(Imagen imagen: productoJson.getImagenes()) {
				
				this.imagenService.delete(imagen.getId_imagen());
				
				this.cloudinaryService.delete(imagen.getImagen_id());
			}
			
			List<Imagen> imagenes= new ArrayList();
			
			List<Map> resultMultiplesImages = this.cloudinaryService.uploadMultipleFiles(multipartfiles);
			
			
			for(Map resultImage: resultMultiplesImages) {
				
				Imagen imagen = new Imagen((String)resultImage.get("original_filename"),(String)resultImage.get("url"),(String)resultImage.get("public_id"));
				
				imagenes.add(imagen);
			}
			
			productoJson.setImagenes(imagenes);
			
		}
		
		
		// Eliminamos las tallas actuales de la tabla talla_producto y guardamos las nuevas.
		if(!(productoJson.getTallasProductos()==null)) {
			
			Optional<Producto> productoActual = productoService.getOne(productoJson.getId_producto());
			
			if(productoActual.isPresent()) {
				List<TallaProducto> tallasProductoActual = productoActual.get().getTallasProductos();
				if(!tallasProductoActual.isEmpty()) {
					for(TallaProducto tallaProductoActual:tallasProductoActual) {
						tallaProductoActual.setProducto(null);
					}
				}
			}
			
			tallaProductoService.deleteByProducto(null);
			
			
			for(TallaProducto tallaProducto:productoJson.getTallasProductos()) {
				tallaProducto.setProducto(productoJson);
				tallaProductoService.saveTallaProducto(tallaProducto);
			}
		}
		
		
		
		this.productoService.saveProducto(productoJson);
		
		this.imagenPrincipalService.delete(antiguaImagenPrincipal.getId_imagen_principal());
		
		return new ResponseEntity(productoJson, HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/eliminarProducto")
	public ResponseEntity<?> eliminarProducto(@RequestParam("idProductoEliminar")int id)throws IOException {
		
		if(!productoService.exists(id)) {
			return new ResponseEntity("El producto no fué encontrado",HttpStatus.NOT_FOUND);
		}
		
		Producto producto = productoService.getOne(id).get();
		
		cloudinaryService.delete(producto.getImagenPrincipal().getImagen_principal_id());
		
		for(Imagen imagen: producto.getImagenes()) {
			cloudinaryService.delete(imagen.getImagen_id());
		}
		
		this.productoService.delete(id);
		
		return new ResponseEntity(HttpStatus.OK);
	}
}
