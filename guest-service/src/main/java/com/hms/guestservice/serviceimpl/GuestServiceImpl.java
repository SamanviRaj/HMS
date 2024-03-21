package com.hms.guestservice.serviceimpl;

import com.hms.guestservice.entity.Guest;
import com.hms.guestservice.service.GuestService;

import java.util.List;
import java.util.Optional;

public class GuestServiceImpl implements GuestService {

    @Override
    public Guest createGuest(Guest guest) {
        return null;
    }

    @Override
    public List<Guest> getAllGuests() {
        return null;
    }

    @Override
    public Optional<Guest> getGuestById(Long id) {
        return Optional.empty();
    }

    @Override
    public Guest updateGuest(Long id, Guest guest) {
        return null;
    }

    @Override
    public void deleteGuest(Long id) {

    }
}
