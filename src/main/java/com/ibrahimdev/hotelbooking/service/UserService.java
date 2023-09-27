package com.ibrahimdev.hotelbooking.service;

import com.ibrahimdev.hotelbooking.dto.*;

import java.util.List;

public interface UserService extends PersonService {
    PersonDTO registerUser(SignUp signUpDto);
    AuthenticationResponse authenticateUser(SignIn loginDto);
    String updatePassword(UpdatePasswordRequest updatePasswordRequest);
    String updatePersonalData(UserRequest userRequest);
    PersonDTO createUserByAdmin(SignUp signUpDto);
    PersonDTO getUserData();
    String deleteUserByUsername(String username);
    List<PersonDTO> searchUsers(UserSearchRequest searchRequest);
    PersonDTO findUserByIdOrEmailOrUsername(Long id, String email, String username);
}
