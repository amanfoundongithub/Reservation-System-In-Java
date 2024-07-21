package com.railway.reservation_system.utils.train;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.railway.reservation_system.model.Passenger;
import com.railway.reservation_system.utils.date.Date;

@Document("passengerjourney")
public class PassengerJourney {
    
    @Id
    private String id;

    /**
     * Credentials to identify trip
     * 
     */
    private String email;

    private int noOfTickets;

    private Date dateOfJourney;

    public PassengerJourney(){

    }

    public PassengerJourney(Passenger passenger, int noOfTickets,Date dateOfJourney){
        this.email = passenger.getEmail();
        this.noOfTickets = noOfTickets;
        this.dateOfJourney = dateOfJourney;
    }

    public PassengerJourney(String email, int noOfTickets,Date dateOfJourney){
        this.email = email;
        this.noOfTickets = noOfTickets;
        this.dateOfJourney = dateOfJourney;
    }

    // Getter of ID
    public String getId(){
        return this.id;
    }

    // Getter for email
    public String getEmail() {
        return email;
    }

    // Setter for email
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter for noOfTickets
    public int getNoOfTickets() {
        return noOfTickets;
    }

    // Setter for noOfTickets
    public void setNoOfTickets(int noOfTickets) {
        this.noOfTickets = noOfTickets;
    }

    public void setDOJ(Date date){
        this.dateOfJourney = date;
    }

    public Date getDOJ(){
        return this.dateOfJourney;
    }
}
