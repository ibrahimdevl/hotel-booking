package com.danielszulc.roomreserve.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
public class UserRequest extends PersonRequest{
        private String username;
        private String password;
    }
