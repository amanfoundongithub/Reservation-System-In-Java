package com.railway.reservation_system.Models.Train;

import com.railway.reservation_system.utils.station.StationName;

/**
 * Builder for the Train Base Class
 * 
 * @author amanfoundongithub 
 * 
 */
public class TrainBuilder {
    
    private Train Train;

    public TrainBuilder(){
        this.Train = new Train();
    }

    public void reset(){
        this.Train = new Train();
    }

    public void addTrainFeatures(int noOfBogies, int capacity){
        this.Train.setNoOfBogies(noOfBogies);
        this.Train.setCapacity(capacity);
        this.Train.setAvailableSeats(capacity);
    }

    public void addTrainDetails(double trainSpeed, String trainModel, TrainType trainType){
        this.Train.setTrainSpeed(trainSpeed);
        this.Train.setTrainModel(trainModel);
        this.Train.setTrainType(trainType);
    }

    public void addTrainIdentifiers(int trainNumber,StationName openingStation,StationName closingStation){
        this.Train.setTrainNumber(trainNumber);
        this.Train.setOpeningStation(openingStation);
        this.Train.setClosingStation(closingStation);
    }

    public Train build(){
        return Train;
    }

}
