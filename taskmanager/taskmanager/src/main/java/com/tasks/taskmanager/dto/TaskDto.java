package com.tasks.taskmanager.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.tasks.taskmanager.entity.TaskPriority;
import com.tasks.taskmanager.entity.TaskStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {

	private UUID id;

	private String title;

	private String description;

	private LocalDateTime dueDate;

	private TaskStatus status;

	private TaskPriority priority;
}
