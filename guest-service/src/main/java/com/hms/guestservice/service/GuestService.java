package com.hms.guestservice.service;

import com.hms.guestservice.entity.Guest;

import java.util.List;
import java.util.Optional;

public interface GuestService {

    // Create a new guest
    Guest createGuest(Guest guest);

    // Retrieve all guests
    List<Guest> getAllGuests();

    // Retrieve a guest by ID
    Optional<Guest> getGuestById(Long id);

    // Update a guest
    Guest updateGuest(Long id, Guest guest);

    // Delete a guest
    void deleteGuest(Long id);
}

