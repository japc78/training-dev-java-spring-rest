package com.japcdev.userapp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.japcdev.userapp.entities.Profile;
import com.japcdev.userapp.entities.User;
import com.japcdev.userapp.repositories.ProfileRepository;
import com.japcdev.userapp.repositories.UserRepository;

@Service
public class ProfileService extends AppService{

  @Autowired
  private ProfileRepository profileRepository;

  @Autowired
  private UserRepository userRepository;

  public Profile getByUserIdAndProfileById(Integer userId, Integer profileId) {
    return profileRepository.findByUserIdAndProfileId(userId, profileId)
      .orElseThrow(()-> new ResponseStatusException( HttpStatus.NOT_FOUND,
        String.format("Profile not found for user %d and profile %d", userId, profileId))
      );
  }

  public Profile create(Integer userId, Profile profile) {
    Optional<User> result = userRepository.findById(userId);
    logger.info("User id: " + userId);
    logger.info("Profile: " + profile.toString());

    if (!result.isPresent()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User %d not found", userId));
    }

    profile.setUser(result.get());
    return profileRepository.save(profile);
  }

}
