package com.railway.reservation_system.exception;

public class PaymentException extends Exception {

    public PaymentException(){
        super("Error in Payment Method");
    }

    public PaymentException(String message) {
        super(message);
    }
    
}
