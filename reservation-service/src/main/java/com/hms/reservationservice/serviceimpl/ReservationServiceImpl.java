package com.hms.reservationservice.serviceimpl;

import com.hms.reservationservice.entity.Reservation;
import com.hms.reservationservice.service.ReservationService;

import java.util.List;
import java.util.Optional;

public class ReservationServiceImpl implements ReservationService {
    @Override
    public Reservation createRservation(Reservation reservation) {
        return null;
    }

    @Override
    public List<Reservation> getReservations() {
        return null;
    }

    @Override
    public Optional<Reservation> getRservationById(Long id) {
        return Optional.empty();
    }

    @Override
    public Reservation updateRservation(Long id, Reservation reservation) {
        return null;
    }

    @Override
    public void deleteReservation(Long id) {

    }
}
