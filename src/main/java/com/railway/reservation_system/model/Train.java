package com.railway.reservation_system.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

import com.railway.reservation_system.utils.station.StationName;
import com.railway.reservation_system.utils.train.TrainType;

/**
 * Train base class 
 * 
 * @author amanfoundongithub
 */
@Document("train") 
public class Train {
    
    @Id
    private String id;

    // Train unique identifiers 
    private int trainNumber; 
    private String trainModel;
    public TrainType trainType;
    
    // Train other features : Numerical 
    private int noOfBogies;
    private int capacity;
    private int availableSeats;

    // Train location and speed 
    private double trainSpeed;

    // Opening and closing station 
    private StationName openingStation;
    private StationName closingStation;

    // Ticket price
    private float ticketPrice;

    // List of passengers
    private List<String> Tickets = new ArrayList<>();
    

    // Constructor of the base class of the train 
    public Train(){
    }

    // Updation 
    public void reset(){
        Tickets.clear();
        availableSeats = capacity;
    }

    public boolean add(Ticket Ticket){
        availableSeats -= Ticket.getNoOfTickets();
        return Tickets.add(Ticket.getId());
    }

    public List<String> getListOfTickets(){
        return this.Tickets;
    }

    public String getId(){
        return this.id;
    }

    // Getters and Setters
    public int getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(int trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getTrainModel() {
        return trainModel;
    }

    public void setTrainModel(String trainModel) {
        this.trainModel = trainModel;
    }

    public TrainType getTrainType() {
        return trainType;
    }

    public void setTrainType(TrainType trainType) {
        this.trainType = trainType;
    }

    public int getNoOfBogies() {
        return noOfBogies;
    }

    public void setNoOfBogies(int noOfBogies) {
        this.noOfBogies = noOfBogies;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public double getTrainSpeed() {
        return trainSpeed;
    }

    public void setTrainSpeed(double trainSpeed) {
        this.trainSpeed = trainSpeed;
    }

    public StationName getOpeningStation() {
        return openingStation;
    }

    public void setOpeningStation(StationName openingStation) {
        this.openingStation = openingStation;
    }

    public StationName getClosingStation() {
        return closingStation;
    }

    public void setClosingStation(StationName closingStation) {
        this.closingStation = closingStation;
    }

    public void setTicketPrice(float ticketPrice) { 
        this.ticketPrice = ticketPrice;
    }

    public float getTicketPrice() { 
        return ticketPrice;
    }
}
