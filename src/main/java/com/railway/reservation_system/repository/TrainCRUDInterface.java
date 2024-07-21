package com.railway.reservation_system.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.railway.reservation_system.model.Train;
import com.railway.reservation_system.utils.station.StationName;

import java.util.*;

/**
 * Implements MongoDB CRUD Interface for Train Class
 * 
 * @author amanfoundongithub 
 * 
 */
@Repository
public interface TrainCRUDInterface extends MongoRepository<Train, String>{


    Optional<Train> findByTrainNumber(int trainNumber);

    @Query("{ 'openingStation': ?0, 'closingStation': ?1 }")
    List<Train> findAllopeningStationAndclosingStation(StationName openingStation, StationName closingStation);
    
} 