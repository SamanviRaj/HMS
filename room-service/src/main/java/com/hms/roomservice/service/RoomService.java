package com.hms.roomservice.service;

import com.hms.roomservice.entity.Room;

import java.util.List;
import java.util.Optional;

public interface RoomService {

    // Create a new room
    Room createRoom(Room room);

    // Retrieve all rooms
    List<Room> getAllRooms();

    // Retrieve a room by ID
    Optional<Room> getRoomById(Long id);

    // Update a room
    Room updateRoom(Long id, Room room);

    // Delete a room
    void deleteRoom(Long id);
}

