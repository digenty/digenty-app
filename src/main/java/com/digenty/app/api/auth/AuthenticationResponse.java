package com.digenty.app.api.auth;

import lombok.Data;

@Data
public class AuthenticationResponse {
    private String accessToken;
    private String type = "Bearer";
    private String refreshToken;
}
