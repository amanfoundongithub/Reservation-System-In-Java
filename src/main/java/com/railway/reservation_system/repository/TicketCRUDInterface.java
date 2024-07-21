package com.railway.reservation_system.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.railway.reservation_system.model.Ticket;

public interface TicketCRUDInterface extends MongoRepository<Ticket, String>{
    
}
