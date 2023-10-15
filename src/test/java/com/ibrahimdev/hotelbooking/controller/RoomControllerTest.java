package com.ibrahimdev.hotelbooking.controller;

import com.ibrahimdev.hotelbooking.dto.RoomDTO;
import com.ibrahimdev.hotelbooking.dto.RoomRequest;
import com.ibrahimdev.hotelbooking.enums.RoomType;
import com.ibrahimdev.hotelbooking.model.Room;
import com.ibrahimdev.hotelbooking.service.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class RoomControllerTest {

    @Mock
    private RoomService roomService;

    @InjectMocks
    private RoomController roomController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAvailableRooms() {
        LocalDate startDate = LocalDate.of(2023, 10, 1);
        LocalDate endDate = LocalDate.of(2023, 10, 10);
        List<RoomDTO> availableRooms = Collections.singletonList(new RoomDTO());

        when(roomService.getAvailableRooms(startDate, endDate)).thenReturn(availableRooms);

        List<RoomDTO> result = roomController.getAvailableRooms(startDate, endDate);

        assertEquals(availableRooms, result);
    }

    @Test
    public void testGetRoomById() {
        Long roomId = 1L;
        RoomDTO roomDTO = new RoomDTO();
        when(roomService.getRoomById(roomId)).thenReturn(roomDTO);

        RoomDTO result = roomController.getRoomById(roomId);

        assertEquals(roomDTO, result);
    }

    @Test
    public void testGetRoomByNumber() {
        String roomNumber = "101";
        RoomDTO roomDTO = new RoomDTO();
        when(roomService.getRoomByNumber(roomNumber)).thenReturn(roomDTO);

        RoomDTO result = roomController.getRoomByNumber(roomNumber);

        assertEquals(roomDTO, result);
    }

    @Test
    public void testGetAll() {
        List<RoomDTO> rooms = Collections.singletonList(new RoomDTO());
        when(roomService.getAll()).thenReturn(rooms);

        ResponseEntity<List<RoomDTO>> result = roomController.getAll();

        assertEquals(rooms, result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void testGetOccupied() {
        List<RoomDTO> rooms = Collections.singletonList(new RoomDTO());
        when(roomService.getCurrentlyOccupiedRooms()).thenReturn(rooms);

        ResponseEntity<List<RoomDTO>> result = roomController.getOccupied();

        assertEquals(rooms, result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void testGetAvailable() {
        List<RoomDTO> rooms = Collections.singletonList(new RoomDTO());
        when(roomService.getCurrentlyAvailableRooms()).thenReturn(rooms);

        ResponseEntity<List<RoomDTO>> result = roomController.getAvailable();

        assertEquals(rooms, result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void testCreateRoom() {
        RoomRequest roomRequest = new RoomRequest();
        Room createdRoom = new Room();
        when(roomService.createRoom(roomRequest)).thenReturn(createdRoom);

        ResponseEntity<Room> result = roomController.createRoom(roomRequest);

        assertEquals(createdRoom, result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void testUpdateRoom() {
        RoomDTO roomDTO = new RoomDTO();
        String message = "Room updated successfully";
        when(roomService.updateRoom(roomDTO)).thenReturn(message);

        ResponseEntity<String> result = roomController.updateRoom(roomDTO);

        assertEquals(message, result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    public void testDeleteRoom() {
        Long roomId = 1L;
        String message = "Room deleted successfully";
        when(roomService.deleteRoom(roomId)).thenReturn(message);

        ResponseEntity<String> result = roomController.deleteRoom(roomId);

        assertEquals(message, result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
}
