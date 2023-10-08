package com.ibrahimdev.hotelbooking.service;

import com.ibrahimdev.hotelbooking.dto.PersonDTO;
import com.ibrahimdev.hotelbooking.dto.PersonRequest;

import java.util.List;


public interface HotelService {
    PersonDTO findGuestById(Long id);
    PersonDTO findGuestByIdOrEmailOrUsername(Long id, String email, String username);
    List<PersonDTO> searchGuests(PersonRequest searchRequest);
    List<PersonDTO> getAllGuests();
}
