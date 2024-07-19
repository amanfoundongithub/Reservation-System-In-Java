package com.railway.reservation_system.utils.validator.handlers;

import com.railway.reservation_system.utils.validator.LoginBody;

public class PasswordHandler implements HandlerInterface{

    private HandlerInterface next; 

    private LoginBody loginBody;

    public PasswordHandler(LoginBody loginBody){
        this.loginBody = loginBody;
    }

    public void setNextHandler(HandlerInterface next){
        this.next = next;
    }

    public boolean handle(){
        String password = loginBody.getPassword();


        if(password.length() < 8){
            return false;
        }
        else{
            if(next != null){
                return next.handle();
            }
            else{
                return true;
            }
        }
    }
    
}