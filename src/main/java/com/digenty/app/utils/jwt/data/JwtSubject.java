package com.digenty.app.utils.jwt.data;

import lombok.Data;
import org.springframework.util.Assert;

@Data
public class JwtSubject {
    private long tokenCreation;
    private Long userId;
    private String email;
    private String authorities;

    public JwtSubject(Long userId, String email) {
        Assert.notNull(userId, "cannot create a JwtSubject without a userId");
        this.userId = userId;
        this.email = email;
        this.tokenCreation = System.currentTimeMillis();
    }

    public JwtSubject(Long userId,  String authorities, String email) {
        Assert.notNull(userId, "cannot create a JwtSubject without a userId");
        this.userId = userId;
        this.authorities = authorities;
        this.tokenCreation = System.currentTimeMillis();
        this.email = email;
    }
}
