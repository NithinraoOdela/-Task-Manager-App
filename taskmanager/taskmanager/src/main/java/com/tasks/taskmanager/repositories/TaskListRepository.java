package com.tasks.taskmanager.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.stereotype.Repository;

import com.tasks.taskmanager.entity.TaskList;

import jakarta.transaction.Transactional;

@Repository
public interface TaskListRepository extends JpaRepository<TaskList, UUID> {

	@NativeQuery(name = "SELECT * FROM taks_list WHERE user_id = :userId AND id=:id")
	Optional<TaskList> findByUsersIdAndId(Integer userId, UUID id);

	List<TaskList> findByUsersId(Integer userId);

	@Transactional
	@NativeQuery(name = "DELETE FROM task_lists WHERE user_id = :userId AND id=:id")
	void deleteByUsersIdAndId(Integer userId, UUID id);

	boolean existsByUsersIdAndId(Integer userId, UUID id);
}
