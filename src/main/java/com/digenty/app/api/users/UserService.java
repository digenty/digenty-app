package com.digenty.app.api.users;

import com.digenty.app.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.MDC;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder bcryptEncoder;

    public User loadUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(()->new ResourceNotFoundException("No such user exists with email: " + email));
    }

    public User onboardUser(User user) {
        MDC.put("user", user.getFirstName());
        User createdUser = doCreate(user);
//        log.info("do create ========> {}",createdUser);
        MDC.clear();
        return createdUser;
    }

    public User createUser(User user) {
        return user;
    }

    private User doCreate(User user) {
        verifyEmail(user.getEmail());
        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    private void verifyEmail(String email) {
        if (userRepository.findByEmail(email).isPresent())
            throw new IllegalArgumentException("Email already exists");
    }
}
