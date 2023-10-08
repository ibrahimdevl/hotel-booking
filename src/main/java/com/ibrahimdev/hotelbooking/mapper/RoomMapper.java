package com.ibrahimdev.hotelbooking.mapper;

import com.ibrahimdev.hotelbooking.dto.RoomDTO;
import com.ibrahimdev.hotelbooking.dto.RoomRequest;
import com.ibrahimdev.hotelbooking.enums.RoomType;
import com.ibrahimdev.hotelbooking.model.Room;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class RoomMapper {

    public RoomDTO convertToDTO(Room room) {
        RoomDTO roomDTO = new RoomDTO();
        BeanUtils.copyProperties(room, roomDTO);
        return roomDTO;
    }

    public Room convertToRoomEntity(RoomDTO roomDTO) {
        Room room = new Room();
        BeanUtils.copyProperties(roomDTO, room);
        return room;
    }

    public Room convertToRoomEntity(RoomRequest roomRequest) {
        Room room = new Room();
        BeanUtils.copyProperties(roomRequest, room);
        room.setType(determinateRoomType(roomRequest.getType()));
        room.setOccupied(false);

        return room;
    }

    private RoomType determinateRoomType(String roomType) {
        return (roomType != null) ? RoomType.valueOf(roomType.toUpperCase()) : RoomType.OTHER;
    }

}
