package com.ibrahimdev.hotelbooking.dto;

import com.ibrahimdev.hotelbooking.enums.RoomType;

public class RoomDTO {
    private Long id;
    private String number;
    private Integer noOfPerson;
    private RoomType type;
    private double price;
    private Long hotelId;
}
