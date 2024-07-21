package com.railway.reservation_system.exception;

public class LoginException extends Exception {

    public LoginException(){
        super("Error in Logging In to the server");
    }

    public LoginException(Exception e){
        super(e);
    }
    
}
