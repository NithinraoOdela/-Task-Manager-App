package com.tasks.taskmanager.dto;

import java.util.List;
import java.util.UUID;

import com.tasks.taskmanager.entity.Users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskListDto {
	private UUID id;

	private String title;

	private String description;

	private Integer count;

	private Double progress;

	private List<TaskDto> tasks;

	private UsersDto users;

}
