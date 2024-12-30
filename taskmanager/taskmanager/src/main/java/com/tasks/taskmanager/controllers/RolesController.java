package com.tasks.taskmanager.controllers;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tasks.taskmanager.dto.RolesDto;
import com.tasks.taskmanager.services.RolesService;

@RestController
@RequestMapping("task-manager-roles")
public class RolesController {

	private RolesService rolesService;

	public RolesController(RolesService rolesService) {
		super();
		this.rolesService = rolesService;
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping()
	public RolesDto createRole(@RequestBody RolesDto rolesDto) {
		return rolesService.createRole(rolesDto);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping()
	public List<RolesDto> getAllRoles() {
		return rolesService.getAllRoles();
	}

}
