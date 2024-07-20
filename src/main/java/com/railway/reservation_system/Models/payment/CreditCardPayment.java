package com.railway.reservation_system.Models.payment;

import com.railway.reservation_system.utils.exception.PaymentException;

public class CreditCardPayment implements PaymentStrategy {


    public boolean pay(int requestAmount) throws PaymentException {

        try {
            return true;
        } catch(Exception e) {
            throw new PaymentException();
        }
    }
    
}
