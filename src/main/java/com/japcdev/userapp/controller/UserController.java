package com.japcdev.userapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.japcdev.userapp.entities.User;
import com.japcdev.userapp.services.UserService;

import io.micrometer.core.annotation.Timed;

@RestController
@RequestMapping("/v1/users")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping(value= {"/addRandom", "/addRandom/{numberOfUser}"})
  public ResponseEntity<List<User>> addRamdonUsers(@PathVariable() Optional<Integer> numberOfUser) {
    Integer total = numberOfUser.orElse(10);
    return new ResponseEntity<>(userService.addRandomUsers(total), HttpStatus.OK);
  }

  @GetMapping
  @Timed("get.users")
  public ResponseEntity<Page<User>> getUsers(
    @RequestParam(required = false, value="page", defaultValue = "0") int page,
    @RequestParam(required=false, value="size", defaultValue= "25") int size) {

    return new ResponseEntity<>(userService.getUsers(page, size), HttpStatus.OK);
  }

  @GetMapping("/usernames")
  public ResponseEntity<Page<String>> getUserNames(
    @RequestParam(required = false, value="page", defaultValue = "0") int page,
    @RequestParam(required=false, value="size", defaultValue= "25") int size) {

    return new ResponseEntity<>(userService.getUserNames(page, size), HttpStatus.OK);
  }

  @GetMapping("/{userId}")
  public ResponseEntity<User> getUserByid(@PathVariable("userId") Integer userId)  {
    return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
  }

  @GetMapping("/username/{username}")
  public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username)  {
    return new ResponseEntity<>(userService.getUserByUserName(username), HttpStatus.OK);
  }



  @PostMapping
  public ResponseEntity<User> authenticate(@RequestBody User user) {
    return new ResponseEntity<>(userService.getUserByUserNameAndPassword(user.getUsername(), user.getPassword()), HttpStatus.OK);
  }

  // @GetMapping(value="/{username}")
  // public ResponseEntity<User> getUserByUserName(@PathVariable("username") String username) {
  //   return new ResponseEntity<User>(userService.getUserByUserName(username), HttpStatus.OK);
  // }

  // @PostMapping
  // public ResponseEntity<User> createUser(@RequestBody User user) {
  //   return new ResponseEntity<User>(userService.createUser(user), HttpStatus.OK);
  // }

  // @PutMapping(value="/{username}")
  // public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable String username) {
  //   return new ResponseEntity<User>(userService.updateUser(user, username), HttpStatus.OK);
  // }

  // @DeleteMapping(value="/{username}")
  // public ResponseEntity<User> deleteUser(@PathVariable String username) {
  //   userService.deleteUser(username);
  //   return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
  // }
}
