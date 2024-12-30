package com.tasks.taskmanager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tasks.taskmanager.Exception.ResourseNotFoundException;
import com.tasks.taskmanager.entity.Users;
import com.tasks.taskmanager.repositories.UsersRepository;

@Service
public class UserDetailsSer implements UserDetailsService {

	@Autowired
	private UsersRepository usersRepository;

	@Override
	public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {

		Users users = usersRepository.findByUsername(username).orElseThrow(() -> new ResourseNotFoundException());

		return users;
	}

}
