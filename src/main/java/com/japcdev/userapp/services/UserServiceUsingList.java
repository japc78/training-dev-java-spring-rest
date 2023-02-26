package com.japcdev.userapp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.github.javafaker.Faker;
import com.japcdev.userapp.models.User;

@Service
public class UserServiceUsingList {

  @Autowired
  private Faker faker;

  private List<User> users = new ArrayList<>();

  @PostConstruct
  public void init() {
    for (int i = 0; i < 100; i++) {
      users.add(new User(faker.funnyName().name(), faker.dragonBall().character(), faker.name().username()));
    }
  }

  /**
   * @return the users
   */
  public List<User> getUsers(String startWith) {
    if (startWith == null) {
      return users;
    }
    return users.stream().filter(u -> u.getUserName().startsWith(startWith)).collect(Collectors.toList());
  }

  public User getUserByUserName(String username) {
    return users.stream()
      .filter(user -> user.getUserName().equals(username))
      .findAny()
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User %s not Found", username)));
  }

  public User createUser(User user) {
    if (users.stream().anyMatch(u-> u.getUserName().equals(user.getUserName()))) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("User %s already exists", user.getUserName()));
    }

    users.add(user);
    return user;
  }

  public User updateUser(User user, String username) {
    User userToBeUpdated = getUserByUserName(username);
    userToBeUpdated.setNickName(user.getNickName());
    userToBeUpdated.setPassword(user.getPassword());
    userToBeUpdated.setUserName(user.getUserName());

    return userToBeUpdated;
  }

  public void deleteUser(String username) {
    User userByUsername = getUserByUserName(username);
    users.remove(userByUsername);
  }
}
