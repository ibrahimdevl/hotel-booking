package com.ibrahimdev.hotelbooking.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class SignIn {
    @NotNull
    @NotEmpty
    @NotBlank
    private String username;
    @NotNull @NotEmpty @NotBlank
    private String password;

}
