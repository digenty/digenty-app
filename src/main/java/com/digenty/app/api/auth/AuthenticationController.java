package com.digenty.app.api.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Slf4j
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest data) {
        try {
            return ResponseEntity.ok(
                    authenticationService.login(data));
        } catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> onboard(@RequestBody @Valid RegistrationData data) {
        return ResponseEntity.ok(authenticationService.register(data));
    }

//    @GetMapping("/validate/{api-key:[a-zA-Z0-9=]*}")
//    public ResponseEntity<?> validateApiKey(@PathVariable("api-key") String apiKey) {
//        try {
//            return ResponseEntity.ok(authenticationService.validateApiKey(apiKey));
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//
//    }

}
