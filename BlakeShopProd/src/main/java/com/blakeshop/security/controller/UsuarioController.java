package com.blakeshop.security.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blakeshop.security.dto.JwtDto;
import com.blakeshop.security.dto.UsuarioBuscar;
import com.blakeshop.security.entity.Usuario;
import com.blakeshop.security.repository.UsuarioRepository;
import com.blakeshop.security.service.UsuarioService;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/blakeshop")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/usuarios")
	public ResponseEntity<Page<Usuario>> usuarios(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "2000") int size,
			@RequestParam(defaultValue = "nombre") String order,
			@RequestParam(defaultValue = "true") boolean asc){
		
		
		Page<Usuario> usuarios = usuarioService.list(PageRequest.of(page, size, Sort.by(order)));
		
		if(!asc) {
			usuarios = usuarioService.list(PageRequest.of(page, size, Sort.by(order).descending()));
		}
		
		return ResponseEntity.ok(usuarios);
	}
	
	
	@PreAuthorize("hasRole('USER')")
	@PostMapping("/usuario")
	public ResponseEntity<Usuario> usuario(@RequestBody UsuarioBuscar usuarioBuscar){
		
		Optional<Usuario> usuario = this.usuarioRepository.findByNombreUsuario(usuarioBuscar.getNombreUsuario());
		
		if(usuario.isPresent()) {
			return new ResponseEntity<Usuario>(usuario.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity("Error, usuario no encontrado", HttpStatus.NOT_FOUND);
		}
		
		
		
		
		
	}
	
}
