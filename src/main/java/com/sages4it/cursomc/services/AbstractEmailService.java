package com.sages4it.cursomc.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.sages4it.cursomc.domain.Pedido;

public abstract class AbstractEmailService implements EmailService{
	@Value("${default.sender}")
	private String sender;
	
	@Override
	public void sendOrderConfirmationEmail(Pedido obj) {
		SimpleMailMessage sm = prepareSimleMailMessageFromPedido(obj);
			sendEmail(sm);
		}
	
		protected SimpleMailMessage prepareSimleMailMessageFromPedido(Pedido obj) {
			SimpleMailMessage sm = new SimpleMailMessage();
			sm.setTo(obj.getCliente().getEmail());
			sm.setFrom(sender);
			sm.setSubject("Pedido Confirmado");
			sm.setSentDate(new Date(System.currentTimeMillis()));
			sm.setText(obj.toString());
			
			return sm;
		}
	}


	


