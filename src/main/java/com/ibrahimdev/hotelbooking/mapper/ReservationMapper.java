package com.ibrahimdev.hotelbooking.mapper;

import com.ibrahimdev.hotelbooking.dto.PersonDTO;
import com.ibrahimdev.hotelbooking.dto.ReservationDTO;
import com.ibrahimdev.hotelbooking.dto.RoomDTO;
import com.ibrahimdev.hotelbooking.model.Reservation;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ReservationMapper {
    @Autowired
    UserMapper userMapper;
    @Autowired
    RoomMapper roomMapper;

    public ReservationDTO convertToDTO(Reservation reservation) {
        ReservationDTO reservationDTO = new ReservationDTO();
        BeanUtils.copyProperties(reservation, reservationDTO);
        if (reservation.getGuest() != null) {
            PersonDTO personDTO = userMapper.convertToDTO(reservation.getGuest());
            reservationDTO.setGuest(personDTO);
        }
        if (reservation.getRoom() != null) {
            RoomDTO roomDTO = roomMapper.convertToDTO(reservation.getRoom());
            reservationDTO.setRoom(roomDTO);
        }
        return reservationDTO;
    }
}

