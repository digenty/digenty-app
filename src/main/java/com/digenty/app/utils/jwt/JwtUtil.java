package com.digenty.app.utils.jwt;

import com.digenty.app.api.users.User;
import com.digenty.app.api.users.UserService;
import com.digenty.app.utils.jwt.data.AppAuthentication;
import com.digenty.app.utils.jwt.data.JwtSubject;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtUtil {

    static final String CLAIM_KEY_COMPANY = "iss";
    static final String CLAIM_KEY_USER_ID = "id";
    static final String CLAIM_KEY_EMAIL = "email";
    static final String CLAIM_KEY_NAME = "name";
    static final String CLAIM_KEY_SUB = "sub";
    static final String CLAIM_KEY_APP = "app";
    static final String CLAIM_KEY_ADMIN = "admin";
    static final String CLAIM_KEY_GRANT = "grant";
    static final String CLAIM_KEY_COUNTRY = "country";
    static final String CLAIM_KEY_CREATED = "created";

    private static final String SECRET_KEY = "bXlTZWNyZXRLZXlGb3JKV1RUb2tlbkdlbmVyYXRpb25UaGF0SXNBdExlYXN0MzJDaGFyYWN0ZXJzTG9uZzEyMzQ1Ng";
    private static final SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

    private final int EXPIRATION_TIME = 86400000; // 24 hours
    private final UserService userService;

    public String generateToken(String username, List<String> roles) {
        User user = userService.loadUserByEmail(username);
        if(roles == null || roles.isEmpty()) {
            roles.add("ROLE_USER");
        }
        Map<String, Object> claims = new HashMap<>();
        claims.put("permissions", roles);
        claims.put(CLAIM_KEY_EMAIL, user.getEmail());
        claims.put(CLAIM_KEY_NAME, user.getFirstName());
        claims.put(CLAIM_KEY_USER_ID, user.getId());
        claims.put(CLAIM_KEY_CREATED, new Date().getTime());
        claims.put("isMain", user.isMain());
        claims.put("roles", roles);
        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            log.info("validate token JwtUtil: {}", token);
//            Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
            getClaims(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getUsernameFromToken(String token) {
        return parseToken(token).getSubject();
    }

    @SuppressWarnings("unchecked")
    public List<String> getRolesFromToken(String token) {
        Claims claims = parseToken(token);
        return (List<String>) claims.get("roles");
    }

    public static Claims parseToken(String token) {
        return getClaims(token).getPayload();
    }

    private static Jws<Claims> getClaims(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    ;
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
            throw new RuntimeException("JWT token has expired", e);
        } catch (UnsupportedJwtException e) {
            e.printStackTrace();
            throw new RuntimeException("JWT token is unsupported", e);
        } catch (MalformedJwtException e) {
            e.printStackTrace();
            throw new RuntimeException("JWT token is malformed", e);
        } catch (SecurityException e) {
            e.printStackTrace();
            throw new RuntimeException("JWT signature validation failed", e);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw new RuntimeException("JWT token is null or empty", e);
        }
    }

    public boolean validateToken(String token, String username) {
        final String tokenUsername = extractUsername(token);
        return (tokenUsername.equals(username) && !isTokenExpired(token));
    }

    // Extract username from token
    public String extractUsername(String token) {
        return parseToken(token).getSubject();
    }

    // Check if token is expired
    public boolean isTokenExpired(String token) {
        return parseToken(token).getExpiration().before(new Date());
    }

    public static JwtSubject getDetailsFromTokenStatic(String token) {
        try {
            final Claims claims = parseToken(token);
            if (claims == null) {
                return null;
            }
            log.info("claims from token: {}", claims);
            Long userId = Long.parseLong(claims.get(CLAIM_KEY_USER_ID).toString());
            String authorities = (String) claims.get(CLAIM_KEY_GRANT);
            String email = (String) claims.get(CLAIM_KEY_EMAIL);
            JwtSubject subject = new JwtSubject(userId, authorities,email);
            subject.setTokenCreation((Long) claims.get(CLAIM_KEY_CREATED));
            return subject;
        } catch (Exception e) {
            log.debug("Method: getUsernameFromToken({})[{}]", token, e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public AppAuthentication getDetailsFromToken(String token) {
        JwtSubject subject = getDetailsFromTokenStatic(token);
        AppAuthentication appAuthentication = new AppAuthentication();
        appAuthentication.setUserId(subject.getUserId());
        appAuthentication.setAuthenticated(true);
        appAuthentication.setEmail(subject.getEmail());
        appAuthentication.setToken(token);
        return appAuthentication;

    }

}
