package com.japcdev.userapp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.japcdev.userapp.entities.Role;
import com.japcdev.userapp.entities.User;
import com.japcdev.userapp.entities.UserInRole;
import com.japcdev.userapp.repositories.RoleRepository;
import com.japcdev.userapp.repositories.UserInRoleRepository;
import com.japcdev.userapp.repositories.UserRepository;

@Service
public class UserInRoleService {

  @Autowired
  UserInRoleRepository userInRolerepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;

  public UserInRole getByUserId(Integer userId) {
    Optional<User> user = userRepository.findById(userId);

    if (!user.isPresent()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User %d not found", userId));
    }

    return userInRolerepository.findByUserId(userId).orElseThrow(()-> new ResponseStatusException( HttpStatus.NOT_FOUND,
      String.format("User with id %d has not any roles", userId))
    );
  }

  public UserInRole create(Integer userId, Integer roleId) {
    Optional<User> user = userRepository.findById(userId);
    Optional<Role> role = roleRepository.findById(roleId);

    if (!user.isPresent() || !role.isPresent() ) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User %d not found", userId));
    }

    UserInRole userInRole = new UserInRole();
    userInRole.setUser(user.get());
    userInRole.setRole(role.get());

    return userInRolerepository.save(userInRole);
  }

  public void delete(Integer userInRoleId) {
    Optional<UserInRole> result = userInRolerepository.findById(userInRoleId);

    if (!result.isPresent()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("UserInRole %d not found", userInRoleId));
    }

    userInRolerepository.delete(result.get());
  }

}
