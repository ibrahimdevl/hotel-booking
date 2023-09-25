package com.ibrahimdev.hotelbooking.dto;

import com.ibrahimdev.hotelbooking.enums.PaymentType;
import com.ibrahimdev.hotelbooking.enums.ReservationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {
    private Long id;
    private PersonDTO guest;
    private RoomDTO room;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer noOfPerson;
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;
    @Enumerated(EnumType.STRING)
    private PaymentType payment;
}
