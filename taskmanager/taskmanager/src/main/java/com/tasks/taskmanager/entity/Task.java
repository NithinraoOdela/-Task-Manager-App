package com.tasks.taskmanager.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(updatable = false, nullable = false)
	private UUID id;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String description;

	private LocalDateTime dueDate;

	@Column(nullable = false)
	private TaskStatus status;

	@Column(nullable = false)
	private TaskPriority priority;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "task_list_id")
	private TaskList taskList;

	@Column(nullable = false)
	private LocalDateTime created;

	@Column(nullable = false)
	private LocalDateTime updated;

}
