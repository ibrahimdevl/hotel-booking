package com.ibrahimdev.hotelbooking.repository;

import com.ibrahimdev.hotelbooking.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByGuestId(Long guestId);

}
