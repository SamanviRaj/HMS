package com.hms.reservationservice.service;

import com.hms.reservationservice.entity.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationService {

    Reservation createRservation(Reservation reservation);

    List<Reservation> getReservations();

    Optional<Reservation> getRservationById(Long id);

    Reservation updateRservation(Long id,Reservation reservation);

    void deleteReservation(Long id);
}
