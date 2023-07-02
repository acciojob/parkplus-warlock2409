package com.driver.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Spot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Enumerated(value = EnumType.STRING)
    SpotType spotType;
    int pricePerHour;

    public boolean getOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    boolean occupied;

    @OneToMany(mappedBy = "spot",cascade = CascadeType.ALL)
    List<Reservation> reservationList = new ArrayList<>();

    @ManyToOne
    @JoinColumn
    @JsonBackReference
    ParkingLot parkingLot;


}
