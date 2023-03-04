package com.japcdev.userapp.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.github.javafaker.Faker;
import com.japcdev.userapp.entities.User;
import com.japcdev.userapp.repositories.UserRepository;

@Service
public class UserService {

  @Autowired
  private Faker faker;

  @Autowired
  private UserRepository repository;

  public List<User> getRoles() {
    return repository.findAll();
  }

  public List<User> addRandomUsers(Integer numberOfUser) {
    List<User> users = new ArrayList<>();

    for (int i = 0; i < numberOfUser; i++) {
      User user = new User();
      user.setUsername(faker.name().username());
      user.setPassword( faker.dragonBall().character());
      users.add(user);
    }
    return repository.saveAll(users);
  }

  /**
   * @return the users
   */
  public Page<User> getUsers(int page, int size) {
    return repository.findAll(PageRequest.of(page, size));
  }


  public Page<String> getUserNames(int page, int size) {
    return repository.findUserNames(PageRequest.of(page, size));
  }

  public User getUserById(Integer userId) {
    return repository.findById(userId).orElseThrow(
      ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User %d not found", userId)));
  }

  public User getUserByUserName(String username) {
    return repository.findByUsername(username).orElseThrow(
      ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User name %s not found", username)));
  }

  public User getUserByUserNameAndPassword(String username, String password) {
    return repository.findByUsernameAndPassword(username, password).orElseThrow(
      ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Login error, user name %s not found", username)));
  }


  // public User getUserByUserName(String username) {
  //   return users.stream()
  //     .filter(user -> user.getUserName().equals(username))
  //     .findAny()
  //     .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User %s not Found", username)));
  // }

  // public User createUser(User user) {
  //   if (users.stream().anyMatch(u-> u.getUserName().equals(user.getUserName()))) {
  //     throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("User %s already exists", user.getUserName()));
  //   }

  //   users.add(user);
  //   return user;
  // }

  // public User updateUser(User user, String username) {
  //   User userToBeUpdated = getUserByUserName(username);
  //   userToBeUpdated.setNickName(user.getNickName());
  //   userToBeUpdated.setPassword(user.getPassword());
  //   userToBeUpdated.setUserName(user.getUserName());

  //   return userToBeUpdated;
  // }

  // public void deleteUser(String username) {
  //   User userByUsername = getUserByUserName(username);
  //   users.remove(userByUsername);
  // }
}
