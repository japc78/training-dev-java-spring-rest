package com.japcdev.userapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.japcdev.userapp.entities.UserInRole;
import com.japcdev.userapp.services.UserInRoleService;

@RestController
@RequestMapping("/v1/users/{userId}/roles")
public class UserInRoleController {

  @Autowired
  private UserInRoleService userInRoleService;

  @GetMapping
  public ResponseEntity<UserInRole> getRolesByUserId(@PathVariable("userId") Integer userid) {
    return new ResponseEntity<UserInRole>(userInRoleService.getByUserId(userid), HttpStatus.OK);
  }

  @PostMapping("/{roleId}")
  public ResponseEntity<UserInRole> create(@PathVariable("userId") Integer userid, @PathVariable("roleId") Integer roleId ) {
    return new ResponseEntity<UserInRole>(userInRoleService.create(userid, roleId), HttpStatus.CREATED);
  }

  @DeleteMapping("/{userInRoleId}")
  public ResponseEntity<Void> deleteUserInRole(@PathVariable("userInRoleId") Integer userInRoleId) {
    userInRoleService.delete(userInRoleId);
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}
