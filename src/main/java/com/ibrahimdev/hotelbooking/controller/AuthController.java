package com.ibrahimdev.hotelbooking.controller;


import com.ibrahimdev.hotelbooking.dto.AuthenticationResponse;
import com.ibrahimdev.hotelbooking.dto.PersonDTO;
import com.ibrahimdev.hotelbooking.dto.SignIn;
import com.ibrahimdev.hotelbooking.dto.SignUp;
import com.ibrahimdev.hotelbooking.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@Tag(name = "Authentication")
public class AuthController {

     private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<PersonDTO> registerUser(@RequestBody @Valid SignUp signUpDto){

        PersonDTO res;
        res = userService.registerUser(signUpDto);

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid SignIn loginDto) {

        AuthenticationResponse res;
        res = userService.authenticateUser(loginDto);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
