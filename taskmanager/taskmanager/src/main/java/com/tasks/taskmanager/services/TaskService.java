package com.tasks.taskmanager.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.tasks.taskmanager.dto.TaskDto;

import jakarta.transaction.Transactional;

@Service
public interface TaskService {

	List<TaskDto> listTasks(UUID taskListId);

	@Transactional
	TaskDto createTask(UUID taskListId, TaskDto task) throws Exception;

	@Transactional
	void deleteTask(UUID taskListId, UUID id);

	TaskDto getTask(UUID taskListId, UUID id);

	@Transactional
	TaskDto updateTask(UUID taskListId, UUID id, TaskDto task);
}
