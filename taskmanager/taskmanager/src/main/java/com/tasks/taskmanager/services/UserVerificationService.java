package com.tasks.taskmanager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.tasks.taskmanager.dto.UsersDto;

@Service
public class UserVerificationService {

	@Autowired
	public AuthenticationManager authenticationManager;

	@Autowired
	public JWTService jwtService;



	// verifying the user
	public String verify(UsersDto user) {
		Authentication authenticate = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

		if (authenticate.isAuthenticated()) {
			return jwtService.generateToken(user.getUsername());
		}

		return "failed";
	}
}
