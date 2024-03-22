package com.hms.reservationservice.serviceimpl;

import com.hms.reservationservice.client.GuestServiceFeignClient;
import com.hms.reservationservice.client.RoomServiceFeignClient;
import com.hms.reservationservice.dto.GuestDTO;
import com.hms.reservationservice.dto.ReservationDTO;
import com.hms.reservationservice.dto.RoomDTO;
import com.hms.reservationservice.entity.Reservation;
import com.hms.reservationservice.repository.ReservationRepository;
import com.hms.reservationservice.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private GuestServiceFeignClient guestServiceFeignClient;

    @Autowired
    private RoomServiceFeignClient roomServiceFeignClient;

    @Override
    public Reservation createReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public ReservationDTO saveReservation(ReservationDTO reservationDTO) {
        // Check if guest exists, otherwise create
        GuestDTO guestDTO = reservationDTO.getGuestDTO();
        if (guestDTO.getId() == null) {
            guestDTO = createGuest(guestDTO);
        } else {
            guestDTO = guestServiceFeignClient.getGuestById(guestDTO.getId());
        }

        // Check if room exists, otherwise create
        RoomDTO roomDTO = reservationDTO.getRoomDTO();
        if (roomDTO.getId() == null) {
            roomDTO = createRoom(roomDTO);
        } else {
            roomDTO = roomServiceFeignClient.getRoomById(roomDTO.getId());
        }

        // Create Reservation entity
        Reservation reservation = new Reservation();
        reservation.setCheckInDate(reservationDTO.getCheckInDate());
        reservation.setCheckOutDate(reservationDTO.getCheckOutDate());
        reservation.setNumberOfGuests(reservationDTO.getNumberOfGuests());
        reservation.setTotalPrice(reservationDTO.getTotalPrice());
        reservation.setStatus(reservationDTO.getStatus());
        reservation.setGuestId(guestDTO.getId());
        reservation.setRoomId(roomDTO.getId());

        // Save the reservation
        reservation = reservationRepository.save(reservation);

        // Map Reservation entity back to DTO for response
        return mapReservationToDTO(reservation, guestDTO, roomDTO);
    }

    // Helper method to map Reservation entity to DTO
    private ReservationDTO mapReservationToDTO(Reservation reservation, GuestDTO guestDTO, RoomDTO roomDTO) {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setId(reservation.getId());
        reservationDTO.setCheckInDate(reservation.getCheckInDate());
        reservationDTO.setCheckOutDate(reservation.getCheckOutDate());
        reservationDTO.setNumberOfGuests(reservation.getNumberOfGuests());
        reservationDTO.setTotalPrice(reservation.getTotalPrice());
        reservationDTO.setStatus(reservation.getStatus());
        reservationDTO.setGuestDTO(guestDTO);
        reservationDTO.setRoomDTO(roomDTO);
        return reservationDTO;
    }

    // Helper method to create room
    private RoomDTO createRoom(RoomDTO roomDTO) {
        // Implement the logic to create room using Feign client
        return roomServiceFeignClient.createRoom(roomDTO);
    }

    // Helper method to create guest
    private GuestDTO createGuest(GuestDTO guestDTO) {
        // Implement the logic to create guest using Feign client
        return guestServiceFeignClient.createGuest(guestDTO);
    }

    @Override
    public List<ReservationDTO> getReservationsByGuestId(Long guestId) {
        List<Reservation> reservations = reservationRepository.findByGuestId(guestId);
        List<ReservationDTO> reservationDTOs = new ArrayList<>();

        for (Reservation reservation : reservations) {
            ReservationDTO reservationDTO = new ReservationDTO();
            reservationDTO.setId(reservation.getId());
            reservationDTO.setCheckInDate(reservation.getCheckInDate());
            reservationDTO.setCheckOutDate(reservation.getCheckOutDate());
            reservationDTO.setNumberOfGuests(reservation.getNumberOfGuests());
            reservationDTO.setTotalPrice(reservation.getTotalPrice());
            reservationDTO.setStatus(reservation.getStatus());

            // Fetch guest details using Feign client
            GuestDTO guestDTO = guestServiceFeignClient.getGuestById(reservation.getGuestId());

            // Fetch room details using Feign client
            RoomDTO roomDTO = roomServiceFeignClient.getRoomById(reservation.getRoomId());

            // Set guest and room details in reservation DTO
            reservationDTO.setGuestDTO(guestDTO);
            reservationDTO.setRoomDTO(roomDTO);

            reservationDTOs.add(reservationDTO);
        }

        return reservationDTOs;
    }

    @Override
    public List<Reservation> getReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Optional<Reservation> getReservationById(Long id) {
        return reservationRepository.findById(id);
    }

    @Override
    public Reservation updateReservation(Long id, Reservation reservation) {
        Reservation existingReservation = reservationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found with id: " + id));

        // Update existing reservation properties with the provided reservation object
        existingReservation.setCheckInDate(reservation.getCheckInDate());
        existingReservation.setCheckOutDate(reservation.getCheckOutDate());
        existingReservation.setNumberOfGuests(reservation.getNumberOfGuests());
        existingReservation.setTotalPrice(reservation.getTotalPrice());
        existingReservation.setStatus(reservation.getStatus());

        return reservationRepository.save(existingReservation);
    }

    @Override
    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}
