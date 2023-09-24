package com.ibrahimdev.hotelbooking.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @NotNull
    @NotEmpty
    @NotBlank
    private String streetAddress;
    @NotNull
    @NotEmpty
    @NotBlank
    private String city;

    @NotNull
    @NotEmpty
    @NotBlank
    private String postalCode;

    @NotNull
    @NotEmpty
    @NotBlank
    private String state;

    @NotNull
    @NotEmpty
    @NotBlank
    private String country;

}