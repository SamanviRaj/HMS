package com.hms.reservationservice.client;

import com.hms.reservationservice.dto.GuestDTO;
import com.hms.reservationservice.dto.RoomDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "room-service", url = "${room-service.url}")
public interface RoomServiceFeignClient {

    @GetMapping("/api/rooms/{id}")
    RoomDTO getRoomById(@PathVariable("id") Long id);

    @PostMapping("/api/rooms")
    RoomDTO createRoom(RoomDTO roomDTO);

    // Add other methods for room service endpoints as needed
}

