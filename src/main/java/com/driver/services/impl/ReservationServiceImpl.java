package com.driver.services.impl;

import com.driver.model.*;
import com.driver.repository.ParkingLotRepository;
import com.driver.repository.ReservationRepository;
import com.driver.repository.SpotRepository;
import com.driver.repository.UserRepository;
import com.driver.services.ReservationService;
import com.driver.transformer.PaymentTransformer;
import com.driver.transformer.ReservationTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    UserRepository userRepository3;
    @Autowired
    SpotRepository spotRepository3;
    @Autowired
    ReservationRepository reservationRepository3;
    @Autowired
    ParkingLotRepository parkingLotRepository3;

    @Override
    public Reservation reserveSpot(Integer userId, Integer parkingLotId, Integer timeInHours, Integer numberOfWheels) throws Exception {
        //If parkingLot is not found, user is not found, or no spot is available, throw "Cannot make reservation" exception.
        Optional<ParkingLot> parkingLotOptional = parkingLotRepository3.findById(parkingLotId);
        ParkingLot parkingLot = parkingLotOptional.orElseThrow(()-> new Exception("Cannot make reservation"));

        Optional<User> userOptional  = userRepository3.findById(userId);
        User user = userOptional.orElseThrow(()-> new Exception("Cannot make reservation"));

        Spot spot=null;
        SpotType spotType;
        int noOfWheels = numberOfWheels.intValue();

        if(noOfWheels == 2 || noOfWheels < 2){
            spotType = SpotType.TWO_WHEELER;
        } else if (noOfWheels == 4 || noOfWheels < 4) {
            spotType=SpotType.FOUR_WHEELER;
        }else{
            spotType=SpotType.OTHERS;
        }

        for(Spot spot1 : parkingLot.getSpotList()){
            if(spot1.isOccupied() == false && spot1.getSpotType() == spotType){
                spot1.setOccupied(true);
                spot=spot1;
            }
        }

        if(spot!=null){
            Reservation reservation = ReservationTransformer.buildReservation(timeInHours,user,spot);
            Payment payment = PaymentTransformer.buildPayment(reservation);
            reservation.setPayment(payment);
            return reservationRepository3.save(reservation);
        }else{
            throw new Exception("Cannot make reservation");
        }

    }
}
