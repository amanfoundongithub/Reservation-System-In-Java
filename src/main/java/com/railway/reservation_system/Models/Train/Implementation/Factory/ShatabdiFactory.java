package com.railway.reservation_system.Models.Train.Implementation.Factory;

import com.railway.reservation_system.utils.station.StationName;
import com.railway.reservation_system.Models.Train.Train;
import com.railway.reservation_system.Models.Train.TrainBuilder;
import com.railway.reservation_system.Models.Train.TrainType;
import com.railway.reservation_system.Models.Train.Implementation.Interface.TrainFactoryInterface;

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
        trainBuilder.addTrainIdentifiers(trainNumber, openingStation, closingStation);

        return trainBuilder.build();
    }
    
}
