package com.tasks.taskmanager.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "task_lists")
@Data
@NoArgsConstructor

public class TaskList {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(updatable = false, nullable = false)
	public UUID id;

	@NotEmpty
	@Column(nullable = false)
	public String title;
	@NotEmpty
	@Column(nullable = false)
	public String description;

	@OneToMany(mappedBy = "taskList", cascade = { CascadeType.REMOVE, CascadeType.PERSIST })
	public List<Task> taskList;

	@Column(nullable = false)
	public LocalDateTime created;

	@Column(nullable = false)
	public LocalDateTime upated;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private Users users;
}
