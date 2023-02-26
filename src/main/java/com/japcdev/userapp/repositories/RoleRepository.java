package com.japcdev.userapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.japcdev.userapp.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
