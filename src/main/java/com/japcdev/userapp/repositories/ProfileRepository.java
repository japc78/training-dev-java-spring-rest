package com.japcdev.userapp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.japcdev.userapp.entities.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {
  @Query("SELECT p FROM Profile p WHERE p.user.id = ?1 AND p.id =?2")
  public Optional<Profile> findByUserIdAndProfileId(Integer username, Integer password);
}
