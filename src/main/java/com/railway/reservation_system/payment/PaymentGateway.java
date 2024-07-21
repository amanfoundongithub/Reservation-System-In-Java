package com.railway.reservation_system.payment;

import com.railway.reservation_system.exception.PaymentException;

public class PaymentGateway {
    
    private PaymentStrategy paymentStrategy;

    public PaymentGateway() {

    }

    public void setPaymentMethod(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public boolean pay(float requestedAmount) throws PaymentException { 
        return this.paymentStrategy.pay(requestedAmount);
    }
}
