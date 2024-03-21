package com.hms.reservationservice.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long id;

   /* @ManyToOne
    @JoinColumn(name = "guest_id", nullable = false)
    private Guest guest;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;*/

    @Column(name = "check_in_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date checkInDate;

    @Column(name = "check_out_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date checkOutDate;

    @Column(name = "number_of_guests", nullable = false)
    private Integer numberOfGuests;

    @Column(name = "total_price", precision = 10, scale = 2)
    private BigDecimal totalPrice;

    @Column(name = "status")
    private String status;

}

