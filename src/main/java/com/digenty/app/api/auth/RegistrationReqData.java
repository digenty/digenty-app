package com.digenty.app.api.auth;

import jakarta.validation.constraints.*;

public record RegistrationReqData(
        @NotBlank(message = "User can not have empty first name")
        @Pattern(regexp = "[a-zA-Z]*", message = "Name can only have letters")
        String firstName,

        @NotBlank(message = "User can not have empty last name")
        @Pattern(regexp = "[a-zA-Z]*", message = "Name can only have letters")
        String lastName,

        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
                message = "Password must be at least 8 characters long, include one uppercase letter, one lowercase letter, one number, and one special character.")
        String password,

        @Email(message = "Invalid email format", regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)" +
                "*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
        @NotEmpty(message = "Email cannot be empty")
        String email,

        @NotEmpty(message = "Phone number cannot be empty")
        @Pattern(regexp = "[0-9]*", message = "Phone number can only have digits")
        @Size(min = 10, max = 15, message = "Phone number must be between 10 and 15 digits")
        String phoneNumber,
        Long position,
        String company,
        String country,
        String sector,
        String heardAboutUs,
        boolean privacyPolicy
) {
}
