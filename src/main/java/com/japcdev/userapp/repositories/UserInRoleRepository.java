package com.japcdev.userapp.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.japcdev.userapp.entities.User;
import com.japcdev.userapp.entities.UserInRole;

public interface UserInRoleRepository extends JpaRepository<UserInRole, Integer> {

  public Optional<UserInRole> findByUserId(Integer userId);

  @Query("SELECT u.user FROM UserInRole u Where u.role.name =?1")
  public List<User> findUsersByRole(String roleName);

  public List<UserInRole> findByUser(User user);

}
