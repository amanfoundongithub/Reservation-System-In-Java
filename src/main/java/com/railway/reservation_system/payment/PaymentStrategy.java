package com.railway.reservation_system.payment;

import com.railway.reservation_system.exception.PaymentException;

public interface PaymentStrategy {
    
    public boolean pay(float requestedAmount) throws PaymentException;

}
