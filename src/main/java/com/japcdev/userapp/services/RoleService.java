package com.japcdev.userapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.japcdev.userapp.entities.Role;
import com.japcdev.userapp.repositories.RoleRepository;

@Service
public class RoleService extends AppService{

  @Autowired
  private RoleRepository repository;

  @Cacheable("roles")
  public List<Role> getRoles() {
    logger.info("Getting roles from cache");
    return repository.findAll();
  }

  @CacheEvict(value = "roles" , allEntries = true)
  public Role createRole(Role role) {
    return repository.save(role);
  }

  @CacheEvict(value = "roles" , allEntries = true)
  public Role updateRole(Integer roleId, Role role) {
    logger.info("Update roles to cache");
    Optional<Role> findById = repository.findById(roleId);

    if (!findById.isPresent()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Role id %d doesn't exists", roleId));
    }

    return repository.save(role);
  }

  @CacheEvict(value = "roles" , allEntries = true)
  public void deleteRole(Integer roleId) {
    logger.info("Delete role to cache");
    Optional<Role> findById = repository.findById(roleId);

    if (!findById.isPresent()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Role id %d doesn't exists", roleId));
    }

    repository.delete(findById.get());
  }

}
