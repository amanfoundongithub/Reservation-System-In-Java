package com.railway.reservation_system.payment;

import com.railway.reservation_system.utils.exception.PaymentException;

public interface PaymentStrategy {
    
    public boolean pay(int requestedAmount) throws PaymentException;
    
}
