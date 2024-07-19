package com.railway.reservation_system.Models.Train.Implementation.Interface;

import com.railway.reservation_system.utils.station.StationName;
import com.railway.reservation_system.Models.Train.Train;

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
