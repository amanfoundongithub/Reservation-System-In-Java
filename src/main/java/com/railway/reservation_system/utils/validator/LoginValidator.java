package com.railway.reservation_system.utils.validator;

import com.railway.reservation_system.utils.validator.handlers.EmailHandler;
import com.railway.reservation_system.utils.validator.handlers.HandlerInterface;
import com.railway.reservation_system.utils.validator.handlers.PasswordHandler;

/**
 * Validates the email and password of the person
 * 
 * @author amanfoundongithub 
 * 
 * 
 */
public class LoginValidator {
    

    public LoginValidator(){

    }

    public boolean validate(String email, String password){

        LoginBody loginBody = new LoginBody(email, password);

        HandlerInterface emailHandler = new EmailHandler(loginBody);
        HandlerInterface passwordHandler = new PasswordHandler(loginBody);

        emailHandler.setNextHandler(passwordHandler);

        return emailHandler.handle();
    }
}
