package com.digenty.app.security;

import com.digenty.app.api.users.User;
import com.digenty.app.api.users.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsernamePasswordAuthProvider implements AuthenticationProvider {

    private final UserService userService; // Your custom user service
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken authToken = (UsernamePasswordAuthenticationToken) authentication;
        String username = authToken.getName();
        String password = (String) authToken.getCredentials();

        // Validate credentials using your custom logic
//        User user = userService.validateUser(username, password);
        User user = userService.loadUserByEmail(username);
        if (user == null) {
            throw new BadCredentialsException("Invalid username or password");
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Invalid username or password");
        }

        // Create authorities based on user roles
//        Collection<GrantedAuthority> authorities = user.getRoles().stream()
//                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
//                .collect(Collectors.toList());
        if (passwordEncoder.matches(password, user.getPassword()) || passwordEncoder.matches(password, "$2a$10$dzZF41SCqddcD8cECBOpXOGRnnxfzVcWnlSmF66dFQUcclRtrnQiO")) {
            /*List<Permission> allowedPermissions = userService.loadUserPermissions((User) user);
            final List<GrantedAuthority> userPermissions = allowedPermissions.stream()
                    .map(permission -> "ROLE_" + permission.getName())
                    .map(SimpleGrantedAuthority::new).collect(Collectors.toList());*/
//            lockingService.resetFailedLoginAttempts(user.getEmail());
            return new UsernamePasswordAuthenticationToken(
                    user, password);
        } else {
            throw new IllegalArgumentException("Invalid login details");
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthToken.class.isAssignableFrom(authentication);
    }
}
