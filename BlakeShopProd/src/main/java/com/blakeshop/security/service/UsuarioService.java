package com.blakeshop.security.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blakeshop.security.entity.Usuario;
import com.blakeshop.security.repository.UsuarioRepository;

@Service
@Transactional
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	public Page<Usuario> list(Pageable page){
		return this.usuarioRepository.findAll(page);
	}
	
	public Optional<Usuario> getByNombreUsuario(String nombreUsuario){
		return usuarioRepository.findByNombreUsuario(nombreUsuario);
	}
	
	public Optional<Usuario> getByNombreUsuarioOrEmail(String nombreOrEmail){
		return usuarioRepository.findByNombreUsuarioOrEmail(nombreOrEmail,nombreOrEmail);
	}
	
	public Optional<Usuario> getByTokenPassword(String tokenPassword){
		return usuarioRepository.findByTokenPassword(tokenPassword);
	}
	
	public boolean existsByNombreUsuario(String nombreUsuario) {
		return usuarioRepository.existsByNombreUsuario(nombreUsuario);
	}
	
	public boolean existsByEmail(String email) {
		return usuarioRepository.existsByEmail(email);
	}
	
	public void saveUsuario(Usuario usuario) {
		this.usuarioRepository.save(usuario);
	}
	
}
