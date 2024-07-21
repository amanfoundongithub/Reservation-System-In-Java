package com.railway.reservation_system.payment;

import com.railway.reservation_system.exception.PaymentException;

public class DebitCardPayment implements PaymentStrategy {

    @Override
    public boolean pay(int requestedAmount) throws PaymentException {
        try {
            return true;
        } catch(Exception e) {
            throw new PaymentException();
        }
    }
    
}
