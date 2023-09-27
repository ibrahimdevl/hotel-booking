package com.ibrahimdev.hotelbooking.dto;

public class InvalidLoginException extends RuntimeException {
    public InvalidLoginException(String message) {
        super(message);
    }
}
