package com.sages4it.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.sages4it.cursomc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);

	void sendEmail(SimpleMailMessage msg);

}
