package com.tasks.taskmanager.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.tasks.taskmanager.dto.TaskListDto;

import jakarta.transaction.Transactional;

@Service
public interface TaskListService {

	List<TaskListDto> listTaskLists(Integer userId) throws Exception;

	@Transactional
	TaskListDto createTaskLists(TaskListDto taskListDto, Integer userId) throws Exception;

	@Transactional
	void deleteTaskLists(UUID id, Integer userId) throws Exception;

	TaskListDto getTaskList(UUID id, Integer userId) throws Exception;

	@Transactional
	TaskListDto updateTaskLists(UUID id, TaskListDto taskListDto, Integer userId) throws Exception;
}
