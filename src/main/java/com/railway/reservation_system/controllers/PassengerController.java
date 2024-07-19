package com.railway.reservation_system.controllers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.railway.reservation_system.Models.Passenger.Passenger;
import com.railway.reservation_system.repository.PassengerCRUDInterface;

@RestController
@CrossOrigin
public class PassengerController {
    /**
     * Logger to log any requests to the Server 
     */
    private static final Logger logger = LoggerFactory.getLogger(PassengerController.class);

    @Autowired
    private PassengerCRUDInterface controller;
    
    /**
     * Adds a passenger to the database
     * 
     * @param Request Body containing passenger details
     * @return ID of the passenger 
     * @throws Exception
     */
    @PostMapping("/passenger/create")
    
    public String createPassenger(@RequestBody Passenger passenger) throws Exception {
        try{
            logger.info("Recieved request on server POST ADD PASSENGER");
            return controller.save(passenger).getId();
        }
        catch(Exception e){
            throw new Exception(e);
        }
        
    }
    /**
     * Finds a passenger by using the unique ID 
     * 
     * @param id
     * @return Passenger 
     * @throws Exception
     */
    @GetMapping("/passenger/find")
    public Passenger findPassengerById(@RequestParam String id) throws Exception {
        try{
            logger.info("Recieved request on server GET FIND BY ID");
            Optional<Passenger> passenger = controller.findById(id);

            return passenger.orElse(null);
        }
        catch(Exception e){
            throw new Exception(e);
        }
    }
    
    /**
     * Deletes a passenger by using the unique ID 
     * 
     * @param id
     * @return "Success" if deleted 
     * @throws Exception
     */
    @DeleteMapping("/passenger/delete")
    public String deletePassengerById(@RequestParam String id) throws Exception {
        try{
            logger.info("Recieved request to DELETE Passenger");
            controller.deleteById(id);
            return "Success";
        }
        catch(Exception e){
            throw new Exception(e);
        }
    }
    
    /**
     * Updates the details of the user 
     * 
     * @param passenger
     * @return "Success" if updated 
     * @throws Exception
     */
    @PutMapping("/passenger/update")
    public String updatePassengerDetails(@RequestBody Passenger passenger) throws Exception {
        try{
            logger.info("Recieved request to UPDATE Passenger Details");
            controller.deleteById(passenger.getId());
            controller.save(passenger);

            return "Success";
        }
        catch(Exception e){
            throw new Exception(e);
        }
    }

    /**
     * Facilitates the Login Resources 
     * 
     * @param email
     * @param password
     * @return Boolean response if 
     * @throws Exception
     */
    @GetMapping("/passenger/login")
    public ResponseEntity<Passenger> findPassengerByLogin(@RequestParam String email,@RequestParam String password) throws Exception{
        try{
            logger.info("New Login Request to Server");

            return controller.findByEmailAndPassword(email, password)
            .map(ResponseEntity :: ok)
            .orElseGet(() -> ResponseEntity.status(201).build());
        }
        catch(Exception e){
            throw new Exception(e);
        }
    }
}
