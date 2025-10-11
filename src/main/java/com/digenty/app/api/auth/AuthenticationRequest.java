package com.digenty.app.api.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
public class AuthenticationRequest {

    @NotBlank(message = "Email cannot be empty")
    String email;

    @NotBlank(message = "Password cannot be empty")
    String password;
}
