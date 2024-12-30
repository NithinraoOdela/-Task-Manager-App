package com.tasks.taskmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tasks.taskmanager.entity.Roles;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer>{

}
