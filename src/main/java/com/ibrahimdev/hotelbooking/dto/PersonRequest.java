package com.ibrahimdev.hotelbooking.dto;

import com.ibrahimdev.hotelbooking.model.Address;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonRequest {
    private Long id;
    @Email
    private String email;
    private String firstName;
    private String lastName;
    @Size(min = 9)
    private String phone;
    private Address address;
    @Enumerated(EnumType.STRING)
    private String gender;
}
