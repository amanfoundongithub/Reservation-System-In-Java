package com.railway.reservation_system.Models.Passenger;

/**
 * Auxillary class for Passenger Construction
 * 
 * @author amanfoundongithub 
 * 
 */
public class PassengerBuilder {
    

    private Passenger passenger;

    public PassengerBuilder(){
        this.passenger = new Passenger();
    }

    public void addUniqueConstraints(String email, String password){
        this.passenger.setEmail(email);
        this.passenger.setPassword(password);
    }

    public void addDetails(String firstName, String lastName, String gender, String dateOfBirth){
        this.passenger.setFirstName(firstName);
        this.passenger.setLastName(lastName);
        this.passenger.setDOB(dateOfBirth);
        this.passenger.setGender(gender);
    }

    public Passenger build(){
        return this.passenger;
    }

    public void reset(){
        this.passenger = new Passenger();
    }
}
