package com.ibrahimdev.hotelbooking.service.impl;

import org.springframework.security.core.Authentication;

public interface AuthenticationService {
    Authentication authenticateWithCredentials(String username, String password);
}
