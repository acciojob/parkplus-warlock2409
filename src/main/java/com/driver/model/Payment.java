package com.driver.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Payment {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    int id;
    boolean paymentCompleted;
    @Enumerated(value = EnumType.STRING)
    PaymentMode paymentMode;

    @OneToOne
    @JoinColumn
    @JsonBackReference
    Reservation reservation;

}
