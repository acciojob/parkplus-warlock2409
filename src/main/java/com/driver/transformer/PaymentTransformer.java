package com.driver.transformer;

import com.driver.model.Payment;
import com.driver.model.Reservation;

public class PaymentTransformer {
    public static Payment buildPayment(Reservation reservation) {
        return Payment.builder()
                .reservation(reservation)
                .paymentCompleted(false)
                .build();
    }
}
