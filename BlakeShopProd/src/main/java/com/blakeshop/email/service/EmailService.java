package com.blakeshop.email.service;

import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.blakeshop.email.dto.EmailValuesDto;



@Service
public class EmailService {

	@Autowired
	JavaMailSender javaMailSender;
	
	@Autowired
	TemplateEngine templateEngine;
	
	@Value("${mail.urlFront}")
	private String urlFront;
	
	
	public void sendEmailTemplate(EmailValuesDto emailValuesDto) {
		MimeMessage message = javaMailSender.createMimeMessage();
		
		try {
			
			MimeMessageHelper helper = new MimeMessageHelper(message,true);
			
			Context context = new Context();
			
			Map<String, Object> model = new HashMap<String, Object>();
			
			model.put("nombreUsuario", emailValuesDto.getNombreUsuario());
			
			model.put("url", urlFront + emailValuesDto.getToken());
			
			context.setVariables(model);
			
			String htmlText = templateEngine.process("email-template", context);
			
			helper.setFrom(emailValuesDto.getEmailFrom());
			
			helper.setTo(emailValuesDto.getEmailTo());
			
			helper.setSubject(emailValuesDto.getSubject());
			
			helper.setText(htmlText,true);
			
			javaMailSender.send(message);
			
		}catch(MessagingException e) {
			
		}
	}
}
