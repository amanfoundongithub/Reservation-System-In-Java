package com.railway.reservation_system.utils.validator.handlers;

import com.railway.reservation_system.utils.validator.LoginBody;

public class EmailHandler implements HandlerInterface{

    private HandlerInterface next; 

    private LoginBody loginBody;

    public EmailHandler(LoginBody loginBody){
        this.loginBody = loginBody;
    }

    public void setNextHandler(HandlerInterface next){
        this.next = next;
    }

    public boolean handle(){
        String email = loginBody.getEmail();

        if(email.contains("@")){
            if(next != null){
                return next.handle();
            }
            else{
                return true;
            }
        }
        else{
            return false;
        }
    }
    
}
