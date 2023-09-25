package com.ibrahimdev.hotelbooking.model;

import com.ibrahimdev.hotelbooking.enums.RoomType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String number;
    private Integer noOfPerson;
    @Enumerated(EnumType.STRING)
    private RoomType type;
    private double price;
    private boolean isOccupied;
    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
    @OneToMany(mappedBy = "room", fetch = FetchType.EAGER)
    private List<Reservation> reservations;
}

