package com.hms.reservationservice.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RoomDTO {
    private Long id;
    private Integer roomNumber;
    private String roomType;
    private Integer maxOccupancy;
    private BigDecimal price;
    private String description;
    private String imageUrl;
}
