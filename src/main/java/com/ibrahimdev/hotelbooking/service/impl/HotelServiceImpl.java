package com.ibrahimdev.hotelbooking.service.impl;

import com.ibrahimdev.hotelbooking.dto.GuestRequest;
import com.ibrahimdev.hotelbooking.dto.PersonDTO;
import com.ibrahimdev.hotelbooking.dto.PersonRequest;
import com.ibrahimdev.hotelbooking.dto.UserSearchRequest;
import com.ibrahimdev.hotelbooking.exception.UserNotFoundException;
import com.ibrahimdev.hotelbooking.service.GuestService;
import com.ibrahimdev.hotelbooking.service.HotelService;
import com.ibrahimdev.hotelbooking.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class HotelServiceImpl implements HotelService {

    @Autowired
    GuestService guestService;
    @Autowired
    UserService userService;

    @Override
    public PersonDTO findGuestById(Long id) {
        return findGuestByIdOrEmailOrUsername(id, null, null);
    }
    @Override
    public PersonDTO findGuestByIdOrEmailOrUsername(Long id, String email, String username) {
        PersonDTO personDTO;
        try {
            personDTO = userService.findUserByIdOrEmailOrUsername(id, email, username);
        }catch (UserNotFoundException e) {
            personDTO = guestService.findGuestByIdOrEmail(id, email);
        }
        return personDTO;
    }

    @Override
    public List<PersonDTO> searchGuests(PersonRequest searchRequest) {
        List<PersonDTO> persons = new ArrayList<>();

        UserSearchRequest userSearchRequest = new UserSearchRequest();
        BeanUtils.copyProperties(searchRequest, userSearchRequest);

        GuestRequest guestRequest = new GuestRequest();
        BeanUtils.copyProperties(searchRequest, guestRequest);

        persons.addAll(userService.searchUsers(userSearchRequest));

        persons.addAll(guestService.searchGuests(guestRequest));

        return persons;
    }

    @Override
    public List<PersonDTO> getAllGuests() {
        List<PersonDTO> persons = new ArrayList<>();
        persons.addAll(userService.getAll());
        persons.addAll(guestService.getAll());
        return persons;
    }


}
