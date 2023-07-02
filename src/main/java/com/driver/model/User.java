package com.driver.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    String name;
    String phoneNumber;
    String password;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    List<Reservation> reservationList = new ArrayList<>();



}
