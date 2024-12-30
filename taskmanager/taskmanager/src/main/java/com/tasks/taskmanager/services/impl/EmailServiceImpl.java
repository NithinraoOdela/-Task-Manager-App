package com.tasks.taskmanager.services.impl;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.tasks.taskmanager.services.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

	public EmailServiceImpl(JavaMailSender javaMailSender) {
		super();
		this.javaMailSender = javaMailSender;
	}

	private JavaMailSender javaMailSender;

	@Override
	public void SendEmail(String to, String subject, String body) {

		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setTo(to);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(body);
		simpleMailMessage.setFrom("nithin@demomailtrap.com");
		javaMailSender.send(simpleMailMessage);
	}

	public String getLinkForEmailVerfication(String emailToken) {
		String link = "http://localhost:8080/task-manager-user/verify-email?emailToken=" + emailToken;
		return link;
	}

}
