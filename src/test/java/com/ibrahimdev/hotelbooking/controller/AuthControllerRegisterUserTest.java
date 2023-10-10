package com.ibrahimdev.hotelbooking.controller;

import static org.mockito.Mockito.when;

import com.ibrahimdev.hotelbooking.dto.AuthenticationResponse;
import com.ibrahimdev.hotelbooking.dto.PersonDTO;
import com.ibrahimdev.hotelbooking.dto.SignIn;
import com.ibrahimdev.hotelbooking.dto.SignUp;
import com.ibrahimdev.hotelbooking.service.UserService;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AuthControllerRegisterUserTest {

    @Mock
    private UserService userServiceMock;

    @Test
    public void shouldRegisterUserSuccessfully() {
        SignUp signUpDto = new SignUp();
        signUpDto.setFirstName("Houssem");
        signUpDto.setLastName("Ibrahim");
        signUpDto.setUsername("ibrahimdev");
        signUpDto.setEmail("ibrahimdev@example.com");
        signUpDto.setPassword("password");


        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(1L);
        personDTO.setUsername("ibrahimdev");
        personDTO.setEmail("ibrahimdev@example.com");
        personDTO.setFirstName("Houssem");
        personDTO.setLastName("Ibrahim");

        when(userServiceMock.registerUser(signUpDto)).thenReturn(personDTO);

        AuthController authController = new AuthController(userServiceMock);
        ResponseEntity<PersonDTO> responseEntity = authController.registerUser(signUpDto);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody()).isEqualTo(personDTO);
    }

    @Test
    public void shouldLoginUserSuccessfully() {
        SignIn signInDto = new SignIn();
        signInDto.setUsername("ibrahimdev");
        signInDto.setPassword("password");

        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        authenticationResponse.setEmail("ibrahimdev@example.com");
        authenticationResponse.setToken("test_token");

        when(userServiceMock.authenticateUser(signInDto)).thenReturn(authenticationResponse);

        AuthController authController = new AuthController(userServiceMock);
        ResponseEntity<AuthenticationResponse> responseEntity = authController.login(signInDto);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(authenticationResponse);
    }
}
