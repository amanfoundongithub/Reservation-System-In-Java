package com.railway.reservation_system.factory;

import com.railway.reservation_system.utils.station.StationName;
import com.railway.reservation_system.utils.train.TrainType;
import com.railway.reservation_system.builder.TrainBuilder;
import com.railway.reservation_system.model.Train;

public class ShatabdiFactory implements TrainFactoryInterface{
    private TrainBuilder trainBuilder = new TrainBuilder();

    public ShatabdiFactory() {
        
    }

    public Train createTrain(int trainNumber,
            StationName openingStation,
            StationName closingStation) {
        
        trainBuilder.reset();
        trainBuilder.addTrainFeatures(10, 120);
        trainBuilder.addTrainDetails(42.55, "WXM-AWS", TrainType.SHATABDI);
        trainBuilder.addTrainIdentifiers(trainNumber, openingStation, closingStation, 200);

        return trainBuilder.build();
    }
    
}
