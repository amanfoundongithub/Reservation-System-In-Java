package com.railway.reservation_system.exception;

public class PriceHikeException extends Exception {

    public PriceHikeException(){
        super("Error in Hiking Prices");
    }

    public PriceHikeException(Exception e){
        super(e);
    }
    
}
