package com.tasks.taskmanager.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tasks.taskmanager.dto.RolesDto;

@Service
public interface RolesService {

	RolesDto createRole(RolesDto rolesDto);

	List<RolesDto> getAllRoles();

}
