package com.railway.reservation_system.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.railway.reservation_system.model.Ticket;

import java.util.List;

public interface TicketCRUDInterface extends MongoRepository<Ticket, String>{
    
    @Query("{ 'email': ?0 }")
    List<Ticket> findAllByEmail(String email);
}
