package com.tasks.taskmanager.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.tasks.taskmanager.Exception.ResourseNotFoundException;
import com.tasks.taskmanager.dto.TaskDto;
import com.tasks.taskmanager.dto.TaskListDto;
import com.tasks.taskmanager.entity.Task;
import com.tasks.taskmanager.entity.TaskList;
import com.tasks.taskmanager.entity.TaskStatus;
import com.tasks.taskmanager.entity.Users;
import com.tasks.taskmanager.repositories.TaskListRepository;
import com.tasks.taskmanager.repositories.UsersRepository;
import com.tasks.taskmanager.services.TaskListService;

@Service
public class TaskListServiceImpl implements TaskListService {

	private final TaskListRepository taskListRepository;

	private final ModelMapper modelMapper;

	private final UsersRepository usersRepository;

	public TaskListServiceImpl(TaskListRepository taskListRepository, ModelMapper modelMapper,
			UsersRepository usersRepository) {
		super();
		this.taskListRepository = taskListRepository;
		this.modelMapper = modelMapper;
		this.usersRepository = usersRepository;
	}

	@Override
	public List<TaskListDto> listTaskLists(Integer userId) throws Exception {
		if (userId == null || !usersRepository.existsById(userId)) {
			throw new Exception("No User Id");
		}

		List<TaskList> taskList = taskListRepository.findByUsersId(userId);

		return taskList.stream().map(task -> {
			TaskListDto taskListDto = modelMapper.map(task, TaskListDto.class);

			taskListDto.setCount(Optional.ofNullable(task.getTaskList()).map(List::size).orElse(0));

			taskListDto.setProgress(calculateTaskListProgress(task.getTaskList()));

			List<TaskDto> tasks = Optional.ofNullable(task.getTaskList()).map(taskEntities -> taskEntities.stream()
					.map(t -> modelMapper.map(t, TaskDto.class)).collect(Collectors.toList())).orElse(null);

			taskListDto.setTasks(tasks);

			return taskListDto;
		}).collect(Collectors.toList());
	}

	private Double calculateTaskListProgress(List<Task> tasks) {
		if (tasks == null || tasks.isEmpty()) {
			return 0.0;
		}

		long closedTaskCount = tasks.stream().filter(task -> TaskStatus.CLOSED == task.getStatus()).count();

		return (double) closedTaskCount / tasks.size();
	}

	@Override
	public TaskListDto createTaskLists(TaskListDto taskListDto, Integer userId) throws Exception {

		if (userId == null) {
			throw new Exception("No User Id");
		}

		Users user = usersRepository.findById(userId).orElseThrow(() -> new ResourseNotFoundException());

		if (taskListDto.getId() != null) {
			throw new Exception("Already exists");
		}

		TaskList task = modelMapper.map(taskListDto, TaskList.class);
		LocalDateTime now = LocalDateTime.now();
		task.setCreated(now);
		task.setUpated(now);
		task.setUsers(user);

		TaskList taskList = taskListRepository.save(task);
		taskListDto = modelMapper.map(taskList, TaskListDto.class);

		taskListDto.setCount(Optional.ofNullable(taskList.getTaskList()).map(List::size).orElse(0));

		taskListDto.setProgress(calculateTaskListProgress(taskList.getTaskList()));

		List<TaskDto> tasks = Optional.ofNullable(taskList.getTaskList()).map(taskEntities -> taskEntities.stream()
				.map(t -> modelMapper.map(t, TaskDto.class)).collect(Collectors.toList())).orElse(null);

		taskListDto.setTasks(tasks);

		return taskListDto;
	}

	@Override
	public void deleteTaskLists(UUID id, Integer userId) throws Exception {
		if (userId == null || !usersRepository.existsById(userId)) {
			throw new Exception("No User Id");
		}
		if (!taskListRepository.existsByUsersIdAndId(userId, id)) {
			throw new ResourseNotFoundException(id, "No taskList");
		}
		taskListRepository.deleteByUsersIdAndId(userId, id);
	}

	@Override
	public TaskListDto getTaskList(UUID id, Integer userId) throws Exception {

		if (userId == null || !usersRepository.existsById(userId)) {
			throw new Exception("No User Id");
		}

		TaskList taskList = taskListRepository.findByUsersIdAndId(userId, id)
				.orElseThrow(() -> new ResourseNotFoundException(id, "Not Found"));

		TaskListDto taskListDto = modelMapper.map(taskList, TaskListDto.class);

		taskListDto.setCount(Optional.ofNullable(taskList.getTaskList()).map(List::size).orElse(0));

		taskListDto.setProgress(calculateTaskListProgress(taskList.getTaskList()));

		List<TaskDto> tasks = Optional.ofNullable(taskList.getTaskList()).map(taskEntities -> taskEntities.stream()
				.map(t -> modelMapper.map(t, TaskDto.class)).collect(Collectors.toList())).orElse(null);

		taskListDto.setTasks(tasks);

		return taskListDto;
	}

	@Override
	public TaskListDto updateTaskLists(UUID id, TaskListDto taskListDto, Integer userId) throws Exception {
		if (userId == null || !usersRepository.existsById(userId)) {
			throw new Exception("No User Id");
		}

		if (id == null || !Objects.equals(taskListDto.getId(), id)) {
			throw new Exception("Wrong Id");
		}
		TaskList taskList = taskListRepository.findByUsersIdAndId(userId, id)
				.orElseThrow(() -> new ResourseNotFoundException(id, "Not found"));

		taskList.setTitle(taskListDto.getTitle());
		taskList.setDescription(taskListDto.getDescription());

		return modelMapper.map(taskListRepository.save(taskList), TaskListDto.class);
	}

}
