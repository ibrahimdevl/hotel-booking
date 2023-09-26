package com.ibrahimdev.hotelbooking.services;


import com.ibrahimdev.hotelbooking.dto.PersonDTO;
import com.ibrahimdev.hotelbooking.dto.PersonRequest;

import java.util.List;

public interface PersonService {
    String updatePersonalData(PersonRequest personRequest);

    List<PersonDTO> getAll();
}
