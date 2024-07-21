package com.railway.reservation_system.utils.exception;

public class TrainBookingException extends Exception{

    public TrainBookingException(){
        super("Error in booking Train");
    }

    public TrainBookingException(Exception e){
        super(e);
    }
    
}
