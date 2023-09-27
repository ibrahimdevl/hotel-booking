package com.ibrahimdev.hotelbooking.dto;

import com.ibrahimdev.hotelbooking.utils.ValidPassword;
import lombok.Data;

import javax.validation.constraints.*;

@Data
public class SignUp {
    @NotNull
    @NotEmpty
    @NotBlank
    private String firstName;
    @NotNull @NotEmpty @NotBlank
    private String lastName;
    @NotNull @NotEmpty @NotBlank
    private String username;
    @NotNull @NotEmpty @NotBlank @Email
    private String email;
    @NotNull @ValidPassword
    private String password;
    @Pattern(regexp = "^ROLE_[A-Z_]+$")
    private String role;
}
