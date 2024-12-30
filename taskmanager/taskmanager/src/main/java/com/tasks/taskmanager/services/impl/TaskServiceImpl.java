package com.tasks.taskmanager.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.tasks.taskmanager.Exception.ResourseNotFoundException;
import com.tasks.taskmanager.dto.TaskDto;
import com.tasks.taskmanager.entity.Task;
import com.tasks.taskmanager.entity.TaskList;
import com.tasks.taskmanager.entity.TaskStatus;
import com.tasks.taskmanager.repositories.TaskListRepository;
import com.tasks.taskmanager.repositories.TaskRepository;
import com.tasks.taskmanager.services.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

	private ModelMapper modelMapper;

	public TaskServiceImpl(ModelMapper modelMapper, TaskRepository taskRepository,
			TaskListRepository taskListRepository) {
		this.modelMapper = modelMapper;
		this.taskRepository = taskRepository;
		this.taskListRepository = taskListRepository;
	}

	private TaskRepository taskRepository;

	private TaskListRepository taskListRepository;

	@Override
	public List<TaskDto> listTasks(UUID taskListId) {
		return taskRepository.findByTaskListId(taskListId).stream().map(task -> modelMapper.map(task, TaskDto.class))
				.toList();
	}

	@Override
	public TaskDto createTask(UUID taskListId, TaskDto taskDto) throws Exception {

		if (taskDto.getId() != null) {
			throw new Exception("Already exists");
		}

		Task task = modelMapper.map(taskDto, Task.class);
		LocalDateTime now = LocalDateTime.now();
		task.setCreated(now);
		task.setUpdated(now);		
		task.setStatus(TaskStatus.OPEN);

		TaskList taskList = taskListRepository.getById(taskListId);
		task.setTaskList(taskList);
		return modelMapper.map(taskRepository.save(task), TaskDto.class);
	}

	@Override
	public void deleteTask(UUID taskListId, UUID id) {

		taskRepository.deleteById(id);

	}

	@Override
	public TaskDto getTask(UUID taskListId, UUID id) {

		return modelMapper.map(taskRepository.findByTaskListIdAndId(taskListId, id), TaskDto.class);
	}

	@Override
	public TaskDto updateTask(UUID taskListId, UUID id, TaskDto taskDto) {

		if (id == null || !Objects.equals(id, taskDto.getId())) {
			throw new ResourseNotFoundException(id, "Not found");
		}
		Task task = taskRepository.findByTaskListIdAndId(taskListId, id)
				.orElseThrow(() -> new ResourseNotFoundException(id, "Not found"));

		task.setTitle(taskDto.getTitle());
		task.setDescription(taskDto.getDescription());
		task.setDueDate(taskDto.getDueDate());
		task.setPriority(taskDto.getPriority());
		task.setUpdated(LocalDateTime.now());
		task.setStatus(taskDto.getStatus());

		return modelMapper.map(taskRepository.save(task), TaskDto.class);
	}
}
