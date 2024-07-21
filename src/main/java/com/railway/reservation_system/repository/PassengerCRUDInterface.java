package com.railway.reservation_system.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.railway.reservation_system.Models.Passenger.Passenger;

import java.util.Optional;

/**
 * Passenger CRUD interface 
 * 
 * @author amanfoundongithub
 * 
 */
@Repository
public interface PassengerCRUDInterface extends MongoRepository<Passenger, String>{
    
    Optional<Passenger> findByEmailAndPassword(String email, String password);

    Optional<Passenger> findByEmail(String email);
}