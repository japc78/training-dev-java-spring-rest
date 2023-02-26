package com.japcdev.userapp.repositories;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.japcdev.userapp.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
  public Optional<User> findByUsername(String username);
  public Optional<User> findByUsernameAndPassword(String username, String password);

  /**
   * Esto no es SQL, es JPQL
   */
  @Query("SELECT u.username FROM User u")
  public Page<String> findUserNames(PageRequest pageRequest);
}
