package com.railway.reservation_system.exception;

public class TrainSearchException extends Exception {

    public TrainSearchException(){
        super("Error in searching for trains");
    }

    public TrainSearchException(Exception e) {
        super(e);
    }
}
