package com.railway.reservation_system.Models.Train.Implementation.Factory;

import com.railway.reservation_system.utils.station.StationName;
import com.railway.reservation_system.Models.Train.Train;
import com.railway.reservation_system.Models.Train.TrainBuilder;
import com.railway.reservation_system.Models.Train.TrainType;
import com.railway.reservation_system.Models.Train.Implementation.Interface.TrainFactoryInterface;

public class DurontoFactory implements TrainFactoryInterface {

    private TrainBuilder trainBuilder = new TrainBuilder();

    public DurontoFactory() {
        
    }

    public Train createTrain(int trainNumber,
            StationName openingStation,
            StationName closingStation) {
        
        trainBuilder.reset();
        trainBuilder.addTrainFeatures(10, 150);
        trainBuilder.addTrainDetails(40.15, "RXS-WED", TrainType.DURONTO);
        trainBuilder.addTrainIdentifiers(trainNumber, openingStation, closingStation, 120);

        return trainBuilder.build();
    }
    
}
