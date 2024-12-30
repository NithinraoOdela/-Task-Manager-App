package com.tasks.taskmanager.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tasks.taskmanager.dto.UsersDto;

@Service
public interface UsersService {

	UsersDto createUser(UsersDto usersDto) throws Exception;

	List<UsersDto> getAllUser();

	boolean verifyUser(String emailToken);

}
