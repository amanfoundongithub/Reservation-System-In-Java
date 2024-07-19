package com.railway.reservation_system.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.railway.reservation_system.Models.Passenger.Passenger;

import java.util.Optional;

public interface PassengerCRUDInterface extends MongoRepository<Passenger, String>{
    
    Optional<Passenger> findByEmailAndPassword(String email, String password);

    Optional<Passenger> findByEmail(String email);
}