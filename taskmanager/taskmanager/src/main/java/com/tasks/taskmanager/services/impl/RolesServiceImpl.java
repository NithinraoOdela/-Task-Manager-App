package com.tasks.taskmanager.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.tasks.taskmanager.dto.RolesDto;
import com.tasks.taskmanager.entity.Roles;
import com.tasks.taskmanager.repositories.RolesRepository;
import com.tasks.taskmanager.services.RolesService;

@Service
public class RolesServiceImpl implements RolesService {

	public RolesRepository rolesRepository;

	public ModelMapper modelMapper;

	public RolesServiceImpl(RolesRepository rolesRepository, ModelMapper modelMapper) {
		super();
		this.rolesRepository = rolesRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public RolesDto createRole(RolesDto rolesDto) {
		return modelMapper.map(rolesRepository.save(modelMapper.map(rolesDto, Roles.class)), RolesDto.class);
	}

	@Override
	public List<RolesDto> getAllRoles() {
		return rolesRepository.findAll().stream().map(role-> modelMapper.map(role, RolesDto.class)).toList();
	}

}
