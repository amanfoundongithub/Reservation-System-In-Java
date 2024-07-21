package com.railway.reservation_system.exception;

public class TicketException extends Exception {

    public TicketException(){
        super("Error in finding tickets");
    }
    
    public TicketException(Exception e){
        super(e);
    }
}
