package com.driver.transformer;

import com.driver.model.User;

public class UserTransformer {

    public static User buildUser(String name, String phoneNumber, String password){

        return User.builder()
                .name(name)
                .phoneNumber(phoneNumber)
                .password(password)
                .build();
    }

    public static User userToResponseDto(User responseUser) {
        return User.builder()
                .name(responseUser.getName())
                .phoneNumber(responseUser.getPhoneNumber())
                .reservationList(responseUser.getReservationList())
                .id(responseUser.getId())
                .password(responseUser.getPassword())
                .build();
    }
}
