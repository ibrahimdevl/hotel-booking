package com.ibrahimdev.hotelbooking.repository;

import com.ibrahimdev.hotelbooking.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
