package com.hms.reservationservice.client;

import com.hms.reservationservice.dto.GuestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "guest-service", url = "${guest-service.url}")
public interface GuestServiceFeignClient {

    @GetMapping("/api/guests/{id}")
    GuestDTO getGuestById(@PathVariable("id") Long id);

    @PostMapping("/api/guests")
    GuestDTO createGuest(GuestDTO guestDTO);

    // Add other methods for guest service endpoints as needed
}

