package com.railway.reservation_system.payment;

import com.railway.reservation_system.exception.PaymentException;

public class CreditCardPayment implements PaymentStrategy {

    @Override
    public boolean pay(float requestedAmount) throws PaymentException {
        try {
            return true;
        } catch(Exception e){
            throw new PaymentException();
        }
    }
    
}
