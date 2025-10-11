package com.digenty.app.api.auth;

import com.digenty.app.api.permissions.Permission;
import com.digenty.app.api.permissions.PermissionService;
import com.digenty.app.api.permissions.UserPermissionRepository;
import com.digenty.app.api.users.User;
import com.digenty.app.api.users.UserRepository;
import com.digenty.app.api.users.UserService;
import com.digenty.app.security.UsernamePasswordAuthProvider;
import com.digenty.app.utils.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationService {
    final UserRepository userRepository;
    private final UsernamePasswordAuthProvider authenticationProvider;
    private final JwtUtil jwtTokenUtil;
    private final UserService userService;
    private final PermissionService permissionService;
    private final UserPermissionRepository userPermissionRepository;

    private final PasswordEncoder passwordEncoder;


    public Map<String, Object> login(LoginRequest authenticationRequest) throws BadCredentialsException {
        Authentication authentication = authenticationProvider
                .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.email(),
                        authenticationRequest.password()));

        User user = (User) authentication.getPrincipal();

        Map<String, Object> response = new HashMap<>();
        response.put("token", jwtTokenUtil.generateToken(user.getEmail(), loadAuthorities2(user.getId())));

        return response;
    }


    private String loadAuthorities(Long user) {
        List<Permission> allowedPermissions = userPermissionRepository.findPermissionByUser(user);
        final List<String> userPermissions = allowedPermissions.stream()
                .map(Permission::getName).collect(Collectors.toList());
        Set<GrantedAuthority> galist = userPermissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
        return userPermissions.isEmpty() ? "" : String.join(",", userPermissions);
    }

    private List<String> loadAuthorities2(Long user) {
        List<Permission> allowedPermissions = userPermissionRepository.findPermissionByUser(user);
        final List<String> userPermissions = allowedPermissions.stream()
                .map(Permission::getName).collect(Collectors.toList());
        Set<GrantedAuthority> galist = userPermissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
//        return userPermissions.isEmpty() ? "" : String.join(",", userPermissions);
        return userPermissions;
    }

    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> register(RegistrationData registrationData) {
        if (Objects.isNull(registrationData)) {
            throw new IllegalArgumentException("Registration data is null. Please pass in necessary fields.");
        }
        try {
            User user = userService.onboardUser(registrationData.toUser());

            log.info("Registered user: " + user.getFirstName() + " " + user.getLastName());
            return Map.of("message", "User Created successfully");
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }
}
