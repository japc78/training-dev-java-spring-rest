package com.japcdev.userapp.config;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.japcdev.userapp.entities.User;
import com.japcdev.userapp.entities.UserInRole;
import com.japcdev.userapp.repositories.UserInRoleRepository;
import com.japcdev.userapp.repositories.UserRepository;

@Service
public class AppUserDetailsService implements UserDetailsService{

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserInRoleRepository userInRoleRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> result = userRepository.findByUsername(username);

    if (!result.isPresent()) {
      throw new UnsupportedOperationException("Unimplemented method 'loadUserByUsername'");
    }

    User user = result.get();
    List<UserInRole> userInRole = userInRoleRepository.findByUser(user);
    String[] roles = userInRole.stream().map(r -> r.getRole().getName()).toArray(String[]::new);

    return org.springframework.security.core.userdetails.User
      .withUsername(user.getUsername())
      .password(passwordEncoder.encode(user.getPassword()))
      .roles(roles).build();
  }
}
