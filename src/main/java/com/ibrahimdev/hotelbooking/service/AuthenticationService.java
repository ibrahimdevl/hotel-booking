package com.ibrahimdev.hotelbooking.service;

import org.springframework.security.core.Authentication;

public interface AuthenticationService {
    Authentication authenticateWithCredentials(String username, String password);
}
