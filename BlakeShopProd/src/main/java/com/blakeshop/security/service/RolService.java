package com.blakeshop.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blakeshop.security.entity.Rol;
import com.blakeshop.security.enums.RolNombre;
import com.blakeshop.security.repository.RolRepository;

@Service
@Transactional
public class RolService {

	@Autowired
	RolRepository rolRepository;
	
	public Optional<Rol> getByRolNombre(RolNombre rolNombre){
		return rolRepository.findByRolNombre(rolNombre);
	}
	
	public void saveRol(Rol rol) {
		rolRepository.save(rol);
	}
	
}
