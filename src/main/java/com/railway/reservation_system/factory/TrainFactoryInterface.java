package com.railway.reservation_system.factory;

import com.railway.reservation_system.model.Train;
import com.railway.reservation_system.utils.station.StationName;

/**
 * Interface for factory to construct the Trains
 * 
 * @author amanfoundongithub 
 * 
 */
public interface TrainFactoryInterface {
    
    public Train createTrain(int trainNumber, 
    StationName openingStation,
    StationName closingStation);
}
