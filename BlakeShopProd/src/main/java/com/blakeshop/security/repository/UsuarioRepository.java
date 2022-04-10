package com.blakeshop.security.repository;

import java.awt.print.Pageable;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blakeshop.security.entity.Usuario;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer>{
	
	Optional<Usuario> findByNombreUsuario(String nombreUsuario);
	Optional<Usuario> findByNombreUsuarioOrEmail(String nombreUsuario, String email);
	Optional<Usuario> findByTokenPassword(String tokenPassword);
	boolean existsByNombreUsuario(String nombreUsuario);
	boolean existsByEmail(String email);
}
