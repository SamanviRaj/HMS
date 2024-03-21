package com.hms.guestservice.serviceimpl;

import com.hms.guestservice.entity.Guest;
import com.hms.guestservice.repository.GuestRepository;
import com.hms.guestservice.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuestServiceImpl implements GuestService {

    @Autowired
    private GuestRepository guestRepository;

    @Override
    public Guest createGuest(Guest guest) {
        return guestRepository.save(guest);
    }

    @Override
    public List<Guest> getAllGuests() {
        return guestRepository.findAll();
    }

    @Override
    public Optional<Guest> getGuestById(Long id) {
        return guestRepository.findById(id);
    }

    @Override
    public Guest updateGuest(Long id, Guest guest) {
        Guest existingGuest = guestRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Guest not found with id: " + id));

        // Update existing guest properties with the provided guest object
        existingGuest.setFirstName(guest.getFirstName());
        existingGuest.setLastName(guest.getLastName());
        existingGuest.setEmail(guest.getEmail());
        existingGuest.setPhoneNumber(guest.getPhoneNumber());
        existingGuest.setAddress(guest.getAddress());

        return guestRepository.save(existingGuest);
    }

    @Override
    public void deleteGuest(Long id) {
        guestRepository.deleteById(id);
    }
}

