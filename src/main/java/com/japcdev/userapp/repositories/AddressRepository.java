package com.japcdev.userapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.japcdev.userapp.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Integer>{

  @Query("SELECT a FROM Address a WHERE a.profile.user.id = ?1 AND a.profile.id = ?2")
  List<Address> findByProfileId(Integer userId, Integer profileId);


}
