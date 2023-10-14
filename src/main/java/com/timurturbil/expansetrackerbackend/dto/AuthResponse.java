package com.timurturbil.expansetrackerbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String accessToken;
}
