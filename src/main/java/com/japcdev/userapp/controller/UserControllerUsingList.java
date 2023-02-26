package com.japcdev.userapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.japcdev.userapp.models.User;
import com.japcdev.userapp.services.UserServiceUsingList;

@RestController
@RequestMapping("/users")
public class UserControllerUsingList {

  @Autowired
  private UserServiceUsingList userService;

  @GetMapping
  public ResponseEntity<List<User>> getUsers(@RequestParam(required = false) String startWith) {
    return new ResponseEntity<>(userService.getUsers(startWith), HttpStatus.OK);
  }

  @GetMapping(value="/{username}")
  public ResponseEntity<User> getUserByUserName(@PathVariable("username") String username) {
    return new ResponseEntity<User>(userService.getUserByUserName(username), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody User user) {
    return new ResponseEntity<User>(userService.createUser(user), HttpStatus.OK);
  }

  @PutMapping(value="/{username}")
  public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable String username) {
    return new ResponseEntity<User>(userService.updateUser(user, username), HttpStatus.OK);
  }

  @DeleteMapping(value="/{username}")
  public ResponseEntity<User> deleteUser(@PathVariable String username) {
    userService.deleteUser(username);
    return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
  }
}
