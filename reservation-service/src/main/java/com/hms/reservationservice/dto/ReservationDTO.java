package com.hms.reservationservice.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ReservationDTO {
    private Long id;
    private GuestDTO guestDTO;
    private RoomDTO roomDTO;
    private Date checkInDate;
    private Date checkOutDate;
    private Integer numberOfGuests;
    private BigDecimal totalPrice;
    private String status;
}

