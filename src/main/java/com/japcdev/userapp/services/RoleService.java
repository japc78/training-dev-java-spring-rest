package com.japcdev.userapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.japcdev.userapp.entities.Role;
import com.japcdev.userapp.repositories.RoleRepository;

@Service
public class RoleService {

  @Autowired
  private RoleRepository repository;

  public List<Role> getRoles() {
    return repository.findAll();
  }

  public Role createRole(Role role) {
    return repository.save(role);
  }

  public Role updateRole(Integer roleId, Role role) {
    Optional<Role> findById = repository.findById(roleId);

    if (!findById.isPresent()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Role id %d doesn't exists", roleId));
    }

    return repository.save(role);
  }

    public void deleteRole(Integer roleId) {
      Optional<Role> findById = repository.findById(roleId);

      if (!findById.isPresent()) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Role id %d doesn't exists", roleId));
      }

      repository.delete(findById.get());
    }

}
