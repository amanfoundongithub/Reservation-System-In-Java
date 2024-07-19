package com.railway.reservation_system.utils.exception;

public class InvalidDateException extends Exception{

    public InvalidDateException(){
        super("Invalid Date Format. Use DD-MM-YYYY format...");
    }
    
}
