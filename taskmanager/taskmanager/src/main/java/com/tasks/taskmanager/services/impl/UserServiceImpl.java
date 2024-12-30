package com.tasks.taskmanager.services.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tasks.taskmanager.Exception.ResourseNotFoundException;
import com.tasks.taskmanager.dto.UsersDto;
import com.tasks.taskmanager.entity.Roles;
import com.tasks.taskmanager.entity.Users;
import com.tasks.taskmanager.repositories.RolesRepository;
import com.tasks.taskmanager.repositories.UsersRepository;
import com.tasks.taskmanager.services.UsersService;

@Service
public class UserServiceImpl implements UsersService {

	private UsersRepository usersRepository;

	private EmailServiceImpl emailServiceImpl;

	private RolesRepository rolesRepository;

	private ModelMapper modelMapper;

	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

	public UserServiceImpl(UsersRepository usersRepository, EmailServiceImpl emailServiceImpl,
			RolesRepository rolesRepository, ModelMapper modelMapper) {
		super();
		this.usersRepository = usersRepository;
		this.emailServiceImpl = emailServiceImpl;
		this.rolesRepository = rolesRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public UsersDto createUser(UsersDto usersDto) throws Exception {
		if (usersRepository.existsByUsername(usersDto.getUsername())) {
			throw new Exception("Already user exists");
		}

		Users user = modelMapper.map(usersDto, Users.class);
		Roles role = rolesRepository.findById(2).orElseThrow(() -> new ResourseNotFoundException());
		Set<Roles> roles = new HashSet<>();
		roles.add(role);
		user.setRoles(roles);
		user.setEnabled(false);
		user.setEmailVerified(false);
		user.setPassword(bCryptPasswordEncoder.encode(usersDto.getPassword()));

		String emailToken = UUID.randomUUID().toString();
		user.setEmailToken(emailToken);
		String emailLink = emailServiceImpl.getLinkForEmailVerfication(emailToken);
		emailServiceImpl.SendEmail(user.getUsername(), "Verify  your Task list account",
				"verification link:- " + emailLink);

		Users users = usersRepository.save(user);
		return modelMapper.map(users, UsersDto.class);
	}

	@Override
	public List<UsersDto> getAllUser() {
		return usersRepository.findAll().stream().map(user -> modelMapper.map(user, UsersDto.class)).toList();
	}

	@Override
	public boolean verifyUser(String emailToken) {
		Users user = usersRepository.findByEmailToken(emailToken);
		if (user == null) {
			return false;
		}
		user.setEnabled(true);
		user.setEmailVerified(true);
		usersRepository.save(user);
		return true;
	}

}
