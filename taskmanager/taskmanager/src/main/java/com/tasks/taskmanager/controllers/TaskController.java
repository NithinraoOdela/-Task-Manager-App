package com.tasks.taskmanager.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tasks.taskmanager.dto.TaskDto;
import com.tasks.taskmanager.services.TaskService;

@RestController
@RequestMapping("/task-lists/{task_list_id}/tasks")
public class TaskController {

	private TaskService taskService;

	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}

	@GetMapping()
	public List<TaskDto> listTasks(@PathVariable("task_list_id") UUID taskListId) {
		return taskService.listTasks(taskListId);
	}

	@GetMapping("{id}")
	public TaskDto getTask(@PathVariable("task_list_id") UUID taskListId, @PathVariable UUID id) {
		return taskService.getTask(taskListId, id);
	}

	@PostMapping()
	public TaskDto createTask(@PathVariable("task_list_id") UUID taskListId, @RequestBody TaskDto task) throws Exception {
		return taskService.createTask(taskListId, task);
	}

	@PutMapping("{id}")
	public TaskDto updateTask(@PathVariable("task_list_id") UUID taskListId, @PathVariable UUID id,
			@RequestBody TaskDto task) {
		return taskService.updateTask(taskListId, id,task);
	}

	@DeleteMapping("{id}")
	public void deleteTask(@PathVariable("task_list_id") UUID taskListId, @PathVariable UUID id) {
		taskService.deleteTask(taskListId, id);
	}
}
