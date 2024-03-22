package com.hms.reservationservice.controller;

import com.hms.reservationservice.dto.ReservationDTO;
import com.hms.reservationservice.entity.Reservation;
import com.hms.reservationservice.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
        Reservation createdReservation = reservationService.createReservation(reservation);
        return new ResponseEntity<>(createdReservation, HttpStatus.CREATED);
    }

    @PostMapping("/save")
    public ResponseEntity<ReservationDTO> saveReservation(@RequestBody ReservationDTO reservationDTO) {
        // Convert ReservationDTO to Reservation entity
        Reservation reservation = convertToReservationEntity(reservationDTO);

        // Save the reservation
        ReservationDTO savedReservation = reservationService.saveReservation(reservationDTO);


        return new ResponseEntity<>(savedReservation, HttpStatus.CREATED);
    }

    @GetMapping("/guest/{guestId}")
    public ResponseEntity<List<ReservationDTO>> getReservationsByGuestId(@PathVariable Long guestId) {
        List<ReservationDTO> reservations = reservationService.getReservationsByGuestId(guestId);
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

// Method to convert ReservationDTO to Reservation entity
    private Reservation convertToReservationEntity(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation();
        reservation.setCheckInDate(reservationDTO.getCheckInDate());
        reservation.setCheckOutDate(reservationDTO.getCheckOutDate());
        reservation.setNumberOfGuests(reservationDTO.getNumberOfGuests());
        reservation.setTotalPrice(reservationDTO.getTotalPrice());
        reservation.setStatus(reservationDTO.getStatus());
        // Populate other fields as needed
        return reservation;
    }

    // Method to convert Reservation entity to ReservationDTO
    private ReservationDTO convertToReservationDTO(Reservation reservation) {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setCheckInDate(reservation.getCheckInDate());
        reservationDTO.setCheckOutDate(reservation.getCheckOutDate());
        reservationDTO.setNumberOfGuests(reservation.getNumberOfGuests());
        reservationDTO.setTotalPrice(reservation.getTotalPrice());
        reservationDTO.setStatus(reservation.getStatus());
        // Populate other fields as needed
        return reservationDTO;
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        List<Reservation> reservations = reservationService.getReservations();
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable("id") Long id) {
        Optional<Reservation> reservationOptional = reservationService.getReservationById(id);
        return reservationOptional.map(reservation -> new ResponseEntity<>(reservation, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable("id") Long id, @RequestBody Reservation reservation) {
        Reservation updatedReservation = reservationService.updateReservation(id, reservation);
        return new ResponseEntity<>(updatedReservation, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable("id") Long id) {
        reservationService.deleteReservation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

