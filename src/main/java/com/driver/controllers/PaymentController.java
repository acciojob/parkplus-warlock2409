package com.driver.controllers;

import com.driver.extention.InsufficentAmountException;
import com.driver.extention.PaymentNotValid;
import com.driver.model.Payment;
import com.driver.services.impl.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/payment")
public class PaymentController {
	
	@Autowired
    PaymentServiceImpl paymentService;

    @PostMapping("/pay")
    public Payment pay(@RequestParam Integer reservationId, @RequestParam Integer amountSent, @RequestParam String mode) throws Exception{
        //Attempt a payment of amountSent for reservationId using the given mode ("cASh", "card", or "upi")
        //If the amountSent is less than bill, throw "Insufficient Amount" exception, otherwise update payment attributes
        //If the mode contains a string other than "cash", "card", or "upi" (any character in uppercase or lowercase), throw "Payment mode not detected" exception.
        //Note that the reservationId always exists
//        try{
//           Payment payment= paymentService.pay(reservationId,amountSent,mode);
//            return payment;
//        }catch (InsufficentAmountException e){
//            throw e;
//        }catch (PaymentNotValid e){
//            throw e;
//        }catch (Exception e) {
//            // Handle any other exceptions
//            throw new Exception("payment failed",e);
//        }
//        return paymentService.pay(reservationId, amountSent, mode);

        try {
            return paymentService.pay(reservationId, amountSent, mode);
        } catch (InsufficentAmountException e) {
            throw e;
        } catch (PaymentNotValid e) {
            throw e;
        } catch (Exception e) {
            throw new Exception("Payment failed", e);
        }


    }
}
