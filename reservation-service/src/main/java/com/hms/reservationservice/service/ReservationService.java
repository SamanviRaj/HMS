package com.hms.reservationservice.service;

import com.hms.reservationservice.dto.ReservationDTO;
import com.hms.reservationservice.entity.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationService {

    Reservation createReservation(Reservation reservation);

    ReservationDTO saveReservation(ReservationDTO reservationDTO);

    List<ReservationDTO> getReservationsByGuestId(Long guestId);

    List<Reservation> getReservations();

    Optional<Reservation> getReservationById(Long id);

    Reservation updateReservation(Long id, Reservation reservation);

    void deleteReservation(Long id);
}
