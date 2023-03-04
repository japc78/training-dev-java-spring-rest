package com.japcdev.userapp.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.japcdev.userapp.entities.UserInRole;

public interface UserInRoleRepository extends JpaRepository<UserInRole, Integer> {

  public Optional<UserInRole> findByUserId(Integer userId);

}
