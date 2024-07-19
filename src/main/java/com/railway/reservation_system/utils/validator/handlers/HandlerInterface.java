package com.railway.reservation_system.utils.validator.handlers;

public interface HandlerInterface {
    
    public boolean handle();

    public void setNextHandler(HandlerInterface handlerInterface); 

}
