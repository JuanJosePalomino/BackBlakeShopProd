package com.blakeshop.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blakeshop.repository.ProductoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.blakeshop.dto.ProductoDto;
import com.blakeshop.entity.Producto;
import com.blakeshop.entity.TallaProducto;

@Service
public class ProductoService {

	@Autowired
	ProductoRepository productoRepository;
	
	public Page<Producto> listAll(Pageable page){
		return this.productoRepository.findAll(page);
		
	}
	
	public Page<Producto> filtroSeccion(Pageable page, String seccion){
		return productoRepository.findBySeccion(page,seccion);
	}
	
	public Page<Producto> filtroMarca(Pageable page, String marca){
		return productoRepository.filtroMarca(page,marca);
	}
	
	public Page<Producto> filtroCategoria(Pageable page, String categoria){
		return productoRepository.filtroCategoria(page,categoria);
	}
	
	public Page<Producto> filtroMarcaSeccion(Pageable page, String marca, String seccion){
		return productoRepository.filtroMarcaSeccion(page, marca, seccion);
	}
	
	public Page<Producto> filtroCategoriaSeccion(Pageable page, String categoria, String seccion){
		return productoRepository.filtroCategoriaSeccion(page, categoria, seccion);
	}
	
	public Page<Producto> filtroCategoriaMarca(Pageable page, String categoria, String marca){
		return productoRepository.filtroCategoriaMarca(page, categoria, marca);
	}
	
	public Page<Producto> filtroCategoriaMarcaSeccion(Pageable page, String categoria, String marca, String seccion){
		return productoRepository.filtroCategoriaMarcaSeccion(page, categoria, marca, seccion);
	}
	
	public Page<Producto> filtroOferta(Pageable page, int condicionOferta){
		
		return productoRepository.filtroOferta(page,condicionOferta);
	}
	
	public Optional<Producto> getOne(int id){
		return productoRepository.findById(id);
	}
	
	public boolean exists(int id) {
		return productoRepository.existsById(id);
	}
	
	public void saveProducto(Producto producto) {
		this.productoRepository.save(producto);
	}
	
	public void delete(int id) {
		productoRepository.deleteById(id);
	}
	
	public Producto getJson(String producto) {
		
		Producto productoJson = new Producto();
		int cantidadTotalTallas = 0;
		
		System.out.print(producto);
		
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			
			productoJson = objectMapper.readValue(producto,Producto.class);
			
			for(TallaProducto tallaProducto:productoJson.getTallasProductos()) {
				cantidadTotalTallas += tallaProducto.getStockTallaProducto();
			}
			
		
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		if(cantidadTotalTallas<=productoJson.getStock()) {
			
			return productoJson;
			
		}else {
			return null;
		}

	}
	
	public String convertString(String text) {
		
		String convertedText = text;
		
		if(text.equals("fechaCreacion")) {
			
			convertedText =  "fecha_creacion";
			
		}else if(text.equals("precioUnitario")) {
			
			convertedText = "precio_unitario";
		}
		
		return convertedText;
	}
}
