package com.railway.reservation_system.Models.payment;

import com.railway.reservation_system.utils.exception.PaymentException;

/**
 * The class that is the interface of the payment
 * 
 * @author amanfoundongithub 
 * 
 */
public class PaymentGateway {
    
    private PaymentStrategy paymentStrategy;

    public PaymentGateway() { 

    }

    public boolean pay() throws PaymentException {
        return paymentStrategy.pay();
        
    }

    public void setPaymentMethod(PaymentStrategy paymentStrategy) { 
        this.paymentStrategy = paymentStrategy;
    }

}
