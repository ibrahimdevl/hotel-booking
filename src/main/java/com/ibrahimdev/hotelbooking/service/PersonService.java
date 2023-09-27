package com.ibrahimdev.hotelbooking.service;


import com.ibrahimdev.hotelbooking.dto.PersonDTO;
import com.ibrahimdev.hotelbooking.dto.PersonRequest;

import java.util.List;

public interface PersonService {
    String updatePersonalData(PersonRequest personRequest);

    List<PersonDTO> getAll();
}
