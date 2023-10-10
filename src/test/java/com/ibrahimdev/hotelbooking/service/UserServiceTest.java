package com.ibrahimdev.hotelbooking.service;

import com.ibrahimdev.hotelbooking.dto.PersonDTO;
import com.ibrahimdev.hotelbooking.dto.SignUp;
import com.ibrahimdev.hotelbooking.mapper.UserMapper;
import com.ibrahimdev.hotelbooking.model.User;
import com.ibrahimdev.hotelbooking.repository.UserRepository;
import com.ibrahimdev.hotelbooking.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserValidator userValidator;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        userMapper = mock(UserMapper.class);
        userService.setUserMapper(userMapper);

    }

    @Test
    public void testRegisterUser() {
        SignUp signUpDto = new SignUp();
        signUpDto.setFirstName("Houssem");
        signUpDto.setLastName("Ibrahim");
        signUpDto.setUsername("ibrahimdev");
        signUpDto.setEmail("ibrahimdev@example.com");
        signUpDto.setPassword(passwordEncoder.encode("password"));

        when(userRepository.save(any())).thenAnswer(invocation -> {
            User user = invocation.getArgument(0);
            user.setId(1L);
            return user;
        });

        when(userMapper.convertToEntity(any())).thenCallRealMethod();
        when(userMapper.convertToDTO(any())).thenCallRealMethod();

        PersonDTO result = userService.registerUser(signUpDto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Houssem", result.getFirstName());
        assertEquals("Ibrahim", result.getLastName());
        assertEquals("ibrahimdev", result.getUsername());
        assertEquals("ibrahimdev@example.com", result.getEmail());

        verify(userRepository, times(1)).save(any());
    }
}
