package com.railway.reservation_system.Models.payment;

import com.railway.reservation_system.utils.exception.PaymentException;

public class UpiPayment implements PaymentStrategy {

    public boolean pay() throws PaymentException {
        try { 
            return true;
        } catch (Exception e) { 
            throw new PaymentException();
        }
    }
    
}
