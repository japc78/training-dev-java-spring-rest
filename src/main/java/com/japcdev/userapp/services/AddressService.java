package com.japcdev.userapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.japcdev.userapp.entities.Address;
import com.japcdev.userapp.entities.Profile;
import com.japcdev.userapp.repositories.AddressRepository;
import com.japcdev.userapp.repositories.ProfileRepository;

@Service
public class AddressService {

  @Autowired
  private AddressRepository addressRepository;

  @Autowired
  private ProfileRepository profileRepository;

  public List<Address> findAddressesByProfileAndUserId(Integer userId, Integer profileId) {
    return addressRepository.findByProfileId(userId, profileId);
  }

  public Address createAddress(Integer userId, Integer profileId, Address address) {
    Optional<Profile> result = profileRepository.findByUserIdAndProfileId(userId, profileId);

    if (!result.isPresent()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Profile %d  and user %d not found", profileId, userId));
    }

    address.setProfile(result.get());
    return addressRepository.save(address);
  }
}
