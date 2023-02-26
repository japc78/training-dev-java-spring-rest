package com.japcdev.userapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.japcdev.userapp.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Integer>{

}
