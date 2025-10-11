package com.digenty.app.utils.jwt.data;

import lombok.Data;

@Data
public class User {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String country;
    private String phoneNumber;
}
