package com.railway.reservation_system.factory;

import com.railway.reservation_system.utils.station.StationName;
import com.railway.reservation_system.utils.train.TrainType;
import com.railway.reservation_system.builder.TrainBuilder;
import com.railway.reservation_system.model.Train;

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
