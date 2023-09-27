package com.ibrahimdev.hotelbooking.dto;

public class AccessDeniedException extends RuntimeException {
    public AccessDeniedException(String message) {
        super(message);
    }
}
