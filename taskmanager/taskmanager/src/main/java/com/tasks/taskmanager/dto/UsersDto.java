package com.tasks.taskmanager.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsersDto {

	private int id;
	private String username;
	private String password;
}
