package com.railway.reservation_system.factory;

import com.railway.reservation_system.utils.station.StationName;
import com.railway.reservation_system.utils.train.TrainType;
import com.railway.reservation_system.builder.TrainBuilder;
import com.railway.reservation_system.model.Train;

/**
 * Implements the Garib Rath Factory for producing Garib Rath trains
 * 
 * @author amanfoundongithub
 */
public class GaribRathFactory implements TrainFactoryInterface{
    
    private TrainBuilder trainBuilder = new TrainBuilder();

    public GaribRathFactory() {
        
    }

    public Train createTrain(int trainNumber,
            StationName openingStation,
            StationName closingStation) {
        
        trainBuilder.reset();
        trainBuilder.addTrainFeatures(18, 300);
        trainBuilder.addTrainDetails(23.5, "MMT-XMS", TrainType.GARIBRATH);
        trainBuilder.addTrainIdentifiers(trainNumber, openingStation, closingStation, 90);

        return trainBuilder.build();
    }
}
