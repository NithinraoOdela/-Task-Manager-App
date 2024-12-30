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

import com.tasks.taskmanager.dto.TaskListDto;
import com.tasks.taskmanager.services.TaskListService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/task-lists/{user-id}")
public class TaskListController {

	private final TaskListService taskListService;

	public TaskListController(TaskListService taskListService) {
		this.taskListService = taskListService;
	}

	@GetMapping()
	public List<TaskListDto> getAllTaskLists(@PathVariable(name = "user-id") Integer userId) throws Exception {
		return taskListService.listTaskLists(userId);
	}

	@GetMapping("{id}")
	public TaskListDto getTaskList(@PathVariable(name = "id") UUID id, @PathVariable(name = "user-id") Integer userId)
			throws Exception {

		return taskListService.getTaskList(id, userId);
	}

	@PostMapping()
	public TaskListDto createTaskLists(@RequestBody @Valid TaskListDto taskListDto,
			@PathVariable(name = "user-id") Integer userId) throws Exception {

		return taskListService.createTaskLists(taskListDto, userId);
	}

	@DeleteMapping("{id}")
	public void DeleteTaskLists(@PathVariable UUID id, @PathVariable(name = "user-id") Integer userId) throws Exception {

		taskListService.deleteTaskLists(id, userId);
	}

	@PutMapping("{id}")
	public TaskListDto updateTaskLists(@PathVariable UUID id, @RequestBody TaskListDto taskListDto,
			@PathVariable(name = "user-id") Integer userId) throws Exception {
		return taskListService.updateTaskLists(id, taskListDto, userId);
	}

}
