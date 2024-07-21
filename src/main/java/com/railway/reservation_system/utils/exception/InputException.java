package com.railway.reservation_system.utils.exception;

public class InputException extends Exception {

    public InputException(Exception e) {
        super(e);
    }

    public InputException() {
        super("Invalid Input");
    }
    
}
