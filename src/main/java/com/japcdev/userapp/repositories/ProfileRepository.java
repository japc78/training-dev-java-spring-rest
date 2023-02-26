package com.japcdev.userapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.japcdev.userapp.entities.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {

}
