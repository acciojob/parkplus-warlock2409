package com.driver.services.impl;

import com.driver.extention.InsufficentAmountException;
import com.driver.extention.PaymentNotValid;
import com.driver.model.Payment;
import com.driver.model.PaymentMode;
import com.driver.model.Reservation;
import com.driver.repository.PaymentRepository;
import com.driver.repository.ReservationRepository;
import com.driver.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    ReservationRepository reservationRepository2;
    @Autowired
    PaymentRepository paymentRepository2;

    @Override
    public Payment pay(Integer reservationId, int amountSent, String mode) throws Exception {

        //Attempt a payment of amountSent for reservationId using the given mode ("cASh", "card", or "upi")
        //If the amountSent is less than bill, throw "Insufficient Amount" exception, otherwise update payment attributes
        //If the mode contains a string other than "cash", "card", or "upi" (any character in uppercase or lowercase), throw "Payment mode not detected" exception.
        //Note that the reservationId always exists

        PaymentMode paymentMode = null;
        String lowerCaseMode = mode.toLowerCase();  // Convert the mode to lowercase once

        if (lowerCaseMode.equals("card") || lowerCaseMode.equals("upi") || lowerCaseMode.equals("cash")) {
            if (lowerCaseMode.equals("card")) {
                paymentMode = PaymentMode.CARD;
            } else if (lowerCaseMode.equals("upi")) {
                paymentMode = PaymentMode.UPI;
            } else if (lowerCaseMode.equals("cash")) {
                paymentMode = PaymentMode.CASH;
            }
        } else {
            throw new PaymentNotValid("Payment mode not detected");
        }
        Optional<Reservation> reservation = reservationRepository2.findById(reservationId);
        Reservation reservation1 = reservation.orElseThrow(()-> new IllegalArgumentException("reservation not found"));

        int price = reservation1.getSpot().getPricePerHour() * reservation1.getNumberOfHours();
        if(amountSent < price){
            throw new InsufficentAmountException("Insufficient Amount");
        }
        Payment payment= reservation1.getPayment();
        payment.setPaymentMode(paymentMode);
        payment.setPaymentCompleted(true);

       Reservation reservation2= reservationRepository2.save(reservation1);



        return reservation2.getPayment();
    }
}
