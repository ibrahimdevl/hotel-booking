package com.ibrahimdev.hotelbooking.service.impl;

import com.ibrahimdev.hotelbooking.dto.GuestRequest;
import com.ibrahimdev.hotelbooking.dto.PersonDTO;
import com.ibrahimdev.hotelbooking.exception.UserNotFoundException;
import com.ibrahimdev.hotelbooking.model.Guest;
import com.ibrahimdev.hotelbooking.repository.GuestRepository;
import com.ibrahimdev.hotelbooking.repository.PersonRepository;
import com.ibrahimdev.hotelbooking.service.GuestService;
import com.ibrahimdev.hotelbooking.service.UserValidator;
import com.ibrahimdev.hotelbooking.utils.SpecificationUtils;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class GuestServiceImpl extends PersonServiceImpl<Guest> implements GuestService{

    private final GuestRepository guestRepository;

    private final UserValidator userValidator;

    @Override
    public PersonRepository<Guest> getRepository() {
        return guestRepository;
    }

    @Override
    public PersonDTO createGuest(Guest guest) {
        userValidator.validateEmailAvailability(guest.getEmail());
        Guest savedGuest = guestRepository.save(guest);
        return userMapper.convertToDTO(savedGuest);
    }

    @Override
    public List<PersonDTO> searchGuests(GuestRequest searchRequest) {

        Specification<Guest> spec = (root, query, cb) -> {
            List<Predicate> predicates = SpecificationUtils.createPersonPredicates(root, cb, searchRequest.getEmail(), searchRequest.getFirstName(), searchRequest.getLastName(), searchRequest.getPhone(), searchRequest.getAddress());
            return cb.and(predicates.toArray(new Predicate[0]));
        };

        List<Guest> guests = guestRepository.findAll(spec);
        return guests.stream().map(this.userMapper::convertToDTO).toList();
    }

    public PersonDTO findGuestByIdOrEmail(Long id, String email) {
        Guest guest = null;

        if (id != null) {
            guest = guestRepository.findById(id).orElse(null);
        }

        if (guest == null && email != null) {
            guest = guestRepository.findByEmail(email).orElse(null);
        }

        if (guest == null) {
            throw new UserNotFoundException("Guest not found");
        }

        return userMapper.convertToDTO(guest);
    }

    @Override
    public String updatePersonalData(GuestRequest guestRequest) {

       if(guestRequest.getId() == null) {
           Guest guest = guestRepository.findByEmail(guestRequest.getEmail()).orElseThrow(
                   () -> new UserNotFoundException("Guest not found"));

          guestRequest.setId(guest.getId());
       }

        return super.updatePersonalData(guestRequest);
    }

}
