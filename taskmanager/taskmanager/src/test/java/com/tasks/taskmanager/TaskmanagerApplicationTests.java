package com.tasks.taskmanager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tasks.taskmanager.services.EmailService;

@SpringBootTest
class TaskmanagerApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private EmailService emailService;

	@Test
	public void TestEmail() {
		emailService.SendEmail("kimkoikim29@gmail.com", "sdfs", "dsf");
	}

}
