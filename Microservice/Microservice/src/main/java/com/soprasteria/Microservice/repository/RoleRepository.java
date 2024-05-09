package com.soprasteria.Microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soprasteria.Microservice.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
