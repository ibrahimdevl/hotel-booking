package com.ibrahimdev.hotelbooking.service;

import com.ibrahimdev.hotelbooking.dto.RoomDTO;
import com.ibrahimdev.hotelbooking.dto.RoomRequest;
import com.ibrahimdev.hotelbooking.model.Room;

import java.time.LocalDate;
import java.util.List;

public interface RoomService {

    List<RoomDTO> getAll();
    RoomDTO getRoomById(Long id);
    RoomDTO getRoomByNumber(String number);
    String deleteRoom(Long id);
    List<RoomDTO> getAvailableRooms(LocalDate startDate, LocalDate endDate);
    List<RoomDTO> getCurrentlyOccupiedRooms();
    List<RoomDTO> getCurrentlyAvailableRooms();
    Room createRoom(RoomRequest roomRequest);
    String updateRoom(RoomDTO room);

}
