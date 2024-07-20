package com.railway.reservation_system.Models.payment;

import com.railway.reservation_system.utils.exception.PaymentException;

public interface PaymentStrategy {
    
    public boolean pay() throws PaymentException;

}
