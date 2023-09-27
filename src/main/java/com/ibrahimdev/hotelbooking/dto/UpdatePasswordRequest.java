package com.ibrahimdev.hotelbooking.dto;


import com.ibrahimdev.hotelbooking.utils.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePasswordRequest {

    @NotNull
    @ValidPassword
    private String newPassword;

    @NotNull
    private String currentPassword;

}
