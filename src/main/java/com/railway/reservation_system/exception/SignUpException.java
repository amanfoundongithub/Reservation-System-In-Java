package com.railway.reservation_system.exception;

public class SignUpException extends Exception {

    public SignUpException(){
        super("Error in Signing Up");
    }

    public SignUpException(Exception e){
        super(e);
    }
    
}
