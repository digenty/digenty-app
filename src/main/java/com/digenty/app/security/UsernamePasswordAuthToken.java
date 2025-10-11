package com.digenty.app.security;

import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UsernamePasswordAuthToken extends AbstractAuthenticationToken {
    @Getter
    private final String username;
    private final String password;
    private final Object principal;

    public UsernamePasswordAuthToken(String username, String password) {
        super(null);
        this.username = username;
        this.password = password;
        this.principal = null;
        setAuthenticated(false);
    }

    public UsernamePasswordAuthToken(String username, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.username = username;
        this.password = null;
        this.principal = username;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return password;
    }

    @Override
    public Object getPrincipal() {
        return principal != null ? principal : username;
    }
}
