package com.ibrahimdev.hotelbooking.services;

import com.ibrahimdev.hotelbooking.dto.GuestRequest;
import com.ibrahimdev.hotelbooking.dto.PersonDTO;
import com.ibrahimdev.hotelbooking.model.Guest;

import java.util.List;

public interface GuestService extends PersonService {
    PersonDTO createGuest(Guest guest);
    List<PersonDTO> searchGuests(GuestRequest searchRequest);
    PersonDTO findGuestByIdOrEmail(Long id, String email);
    String updatePersonalData(GuestRequest guestRequest);
}
