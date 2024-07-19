package com.railway.reservation_system.Models.Train.Implementation.Factory;

import com.railway.reservation_system.utils.station.StationName;
import com.railway.reservation_system.Models.Train.Train;
import com.railway.reservation_system.Models.Train.TrainBuilder;
import com.railway.reservation_system.Models.Train.TrainType;
import com.railway.reservation_system.Models.Train.Implementation.Interface.TrainFactoryInterface;

/**
 * Factory for constructing Rajdhani Express
 * 
 * @author amanfoundongithub 
 * 
 */
public class RajdhaniFactory implements TrainFactoryInterface {

    private TrainBuilder trainBuilder = new TrainBuilder();

    public RajdhaniFactory() {
        
    }

    public Train createTrain(int trainNumber,
            StationName openingStation,
            StationName closingStation) {
        
        trainBuilder.reset();
        trainBuilder.addTrainFeatures(15, 250);
        trainBuilder.addTrainDetails(27.5, "WXM-MWS", TrainType.RAJDHANI);
        trainBuilder.addTrainIdentifiers(trainNumber, openingStation, closingStation);

        return trainBuilder.build();
    }

}
