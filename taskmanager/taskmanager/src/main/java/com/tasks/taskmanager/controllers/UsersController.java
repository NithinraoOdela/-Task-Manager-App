package com.tasks.taskmanager.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tasks.taskmanager.Exception.ResourseNotFoundException;
import com.tasks.taskmanager.dto.UsersDto;
import com.tasks.taskmanager.entity.Users;
import com.tasks.taskmanager.repositories.UsersRepository;
import com.tasks.taskmanager.services.UserVerificationService;
import com.tasks.taskmanager.services.UsersService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("task-manager-user")
public class UsersController {

	int userId;

	private UsersRepository usersRepository;
	private UsersService usersService;

	private UserVerificationService userVerificationService;

	public UsersController(UsersService usersService, UserVerificationService userVerificationService) {
		this.usersService = usersService;
		this.userVerificationService = userVerificationService;
	}

	@PostMapping("register")
	public ResponseEntity<String> createUser(@RequestBody @Valid UsersDto usersDto) throws Exception {
		try {
			usersService.createUser(usersDto);
			return ResponseEntity.ok("Successfully registered, Please check your mail box to verify your accout");
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping()
	public List<UsersDto> getAllUser() {
		return usersService.getAllUser();
	}

	@PostMapping("login")
	public ResponseEntity<String> login(@RequestBody UsersDto user) {

		return ResponseEntity.ok(userVerificationService.verify(user));

	}

	@GetMapping("/verify-email")
	public ResponseEntity<String> verifyUser(@RequestParam("emailToken") String emailToken) {

		if (usersService.verifyUser(emailToken)) {
			return ResponseEntity.ok("Your account is registered now!");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Failed to register your account");

	}
}
