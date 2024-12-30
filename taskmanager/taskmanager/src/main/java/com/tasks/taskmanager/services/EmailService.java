package com.tasks.taskmanager.services;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {

	void SendEmail(String to, String subject, String body);

}
