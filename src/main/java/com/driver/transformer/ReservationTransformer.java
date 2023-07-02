package com.driver.transformer;

import com.driver.model.Reservation;
import com.driver.model.Spot;
import com.driver.model.User;

public class ReservationTransformer {

    public static Reservation buildReservation(Integer timeInHours, User user, Spot spot) {
        return Reservation.builder()
                .numberOfHours(timeInHours.intValue())
                .user(user)
                .spot(spot)
                .build();
    }
}
