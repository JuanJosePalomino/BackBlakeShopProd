package com.blakeshop.email.controller;

import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blakeshop.email.dto.ChangePasswordDto;
import com.blakeshop.email.dto.EmailValuesDto;
import com.blakeshop.email.service.EmailService;
import com.blakeshop.security.entity.Usuario;
import com.blakeshop.security.service.UsuarioService;



@RestController
@RequestMapping("/blakeshop")
@CrossOrigin
public class EmailController {

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	UsuarioService usuarioService;
	
	@Value("${spring.mail.username}")
	private String mailFrom;
	
	
	private static final String mailSubject = "Cambio de Contraseña BlakeShop";
	
	@PostMapping("/sendEmail")
	public ResponseEntity<?> sendEmailTemplate(@RequestBody EmailValuesDto emailValuesDto){
		
		Optional<Usuario> usuarioOpt = usuarioService.getByNombreUsuarioOrEmail(emailValuesDto.getEmailTo());
		
		if(!usuarioOpt.isPresent()) {
			return new ResponseEntity("Error, no existe ningun usuario con esas credenciales", HttpStatus.NOT_FOUND);
		}
		
		Usuario usuario = usuarioOpt.get();
		
		emailValuesDto.setEmailFrom(mailFrom);
		
		emailValuesDto.setEmailTo(usuario.getEmail());
		
		emailValuesDto.setNombreUsuario(usuario.getNombreUsuario());
		
		emailValuesDto.setSubject(mailSubject);
		
		
		
		UUID uuid = UUID.randomUUID();
		
		String token = uuid.toString();
		
		emailValuesDto.setToken(token);
		
		usuario.setTokenPassword(token);
		
		usuarioService.saveUsuario(usuario);
		
		this.emailService.sendEmailTemplate(emailValuesDto);
		
		return new ResponseEntity(HttpStatus.OK);
		
	}
	
	@PostMapping("/changePassword")
	public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordDto changePasswordDto, BindingResult bindingResult){
		
		if(bindingResult.hasErrors()) {
			return new ResponseEntity("Campos mal puestos", HttpStatus.BAD_REQUEST);
		}
		
		if(!changePasswordDto.getPassword().equals(changePasswordDto.getConfirmPassword())) {
			return new ResponseEntity("Las contraseñas no coinciden", HttpStatus.BAD_REQUEST);
		}
		
		Optional<Usuario> usuarioOpt = usuarioService.getByTokenPassword(changePasswordDto.getTokenPassword());
		
		if(!usuarioOpt.isPresent()) {
			return new ResponseEntity("Error, no existe ningun usuario con esas credenciales o token inválido", HttpStatus.NOT_FOUND);
		}
		
		Usuario usuario = usuarioOpt.get();
		
		String newPassword = passwordEncoder.encode(changePasswordDto.getPassword());
		
		usuario.setPassword(newPassword);
		
		usuario.setTokenPassword(null);
		
		usuarioService.saveUsuario(usuario);
		
		return new ResponseEntity(HttpStatus.OK);
	} 
	
}
