package com.digenty.app.utils.jwt;//package com.termii.gogo_client.utils.jwt;
//
//import com.termii.gogo_client.utils.jwt.data.JwtSubject;
//import io.jsonwebtoken.*;
//import io.jsonwebtoken.lang.Assert;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import javax.crypto.SecretKey;
//import java.security.Key;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//
//@Component
//@Slf4j
//public class JwtTokenUtil {
//
//    static final String CLAIM_KEY_COMPANY = "iss";
//    static final String CLAIM_KEY_EMAIL = "email";
//    static final String CLAIM_KEY_SUB = "sub";
//    static final String CLAIM_KEY_APP = "app";
//    static final String CLAIM_KEY_ADMIN = "admin";
//    static final String CLAIM_KEY_GRANT = "grant";
//    static final String CLAIM_KEY_COUNTRY = "country";
//    static final String CLAIM_KEY_CREATED = "created";
//    static final String CLAIM_KEY_DEVICE_ID = "deviceId";
//    static final String SECRET_KEY_BASE64 = "671491AE98362741F722202EED3288E8FF2508B35315ADBF75EEB3195A926B40";
//
//
//    @Value("${security.jwt.token.secret:XuO1khigOzIMjZjdHQH24S3R8BegBe0Mu2Z6K9SE2un0W0GmtJZhCZy8G4MbPRG2}")
//    private static final String jwtSecret = "zdtlD3JK56m6wTTgsNFhqzjqP";
//
//    private static Key secretKey;
//    @Value("${security.jwt.token.expire:3600000}")
//    private long validityInMilliseconds = 3600000;
//    @Value("${security.jwt.refresh.expire:3600000}")
//    private long refreshValidityInMilliseconds = 259200000;
//    @Value("${security.admin.id:1}")
//    private long termiiId;
//
//    public String generateToken(JwtSubject subject) {
//        Map<String, Object> claims = new HashMap<>();
//        claims.put(CLAIM_KEY_SUB, subject.getUserId());
//        claims.put(CLAIM_KEY_EMAIL, subject.getEmail());
//        claims.put(CLAIM_KEY_CREATED, subject.getTokenCreation());
//        claims.put(CLAIM_KEY_GRANT, subject.getAuthorities());
//        claims.put(CLAIM_KEY_COMPANY, subject.getCompanyId());
//        claims.put(CLAIM_KEY_ADMIN, subject.getAdminId());
//        claims.put(CLAIM_KEY_APP, subject.getApplicationId());
//        claims.put(CLAIM_KEY_COUNTRY, subject.getCountry());
//        claims.put(CLAIM_KEY_DEVICE_ID, subject.getDeviceId());
//        return doGenerateToken(claims);
//    }
//
//    public String doGenerateToken(Map<String, Object> claims) {
//        String jwtIssuer = "kawa.com";
//        System.out.println("SECRET GENERATE " + secretKey);
//        Date expiration = new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000);
//        claims.put("expiration", expiration);
//        return Jwts.builder()
//                .setIssuer(jwtIssuer)
//                .setClaims(claims)
//                .setIssuedAt(new Date())
//                .setExpiration(expiration)
////                .signWith(secretKey, SignatureAlgorithm.HS512).compact();
//                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
//    }
////    private Key getSignKey() {
////        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY_BASE64);
////        return Keys.hmacShaKeyFor(keyBytes);
////    }
//
//
//    public String generateRefreshToken() {
//        return null;
//    }
//
////    public String getUserId(String token) {
////        Claims claims = Jwts.parserBuilder()
////                .setSigningKey(secretKey)
////                .build()
////                .parseClaimsJws(token)
////                .getBody();
////
////        return claims.getSubject().split(",")[0];
////    }
//
////    public Long getCompanyId(String token) {
////        Claims claims = Jwts.parserBuilder()
////                .setSigningKey(secretKey)
////                .build()
////                .parseClaimsJws(token)
////                .getBody();
////
////        return Long.valueOf(claims.getIssuer().split(",")[0]);
////    }
//
//    public JwtSubject getDetailsFromToken(String token) {
//        try {
//            System.out.println(" DETAILS TOKEN: " + jwtSecret);
//
//            final Claims claims = getClaimsFromToken(token);
//            if (claims == null){
//                return null;
//            }
//            Long userId = Long.valueOf(claims.getSubject());
//            Long companyId = Long.valueOf(claims.getIssuer());
//            String authorities = (String) claims.get(CLAIM_KEY_GRANT);
//            String email = (String) claims.get(CLAIM_KEY_EMAIL);
//            Long applicationId = Long.valueOf((Integer) claims.get(CLAIM_KEY_APP));
//            Long adminId = Long.valueOf((Integer) claims.get(CLAIM_KEY_ADMIN));
//            String country = (String) claims.get(CLAIM_KEY_COUNTRY);
//            JwtSubject subject = new JwtSubject(userId, adminId, applicationId, companyId, authorities, country, email);
//            subject.setTokenCreation((Long) claims.get(CLAIM_KEY_CREATED));
//            subject.setDeviceId((String) claims.get(CLAIM_KEY_DEVICE_ID));
//            return subject;
//        } catch (Exception e) {
////            e.printStackTrace();
//            return null;
//        }
//    }
//
//    public static JwtSubject getDetailsFromTokenStatic(String token) {
//        try {
//            final Claims claims = getClaimsFromTokenStatic(token);
//            if (claims == null){
//                return null;
//            }
//            Long userId = Long.valueOf(claims.getSubject());
//            Long companyId = Long.valueOf(claims.getIssuer());
//            String authorities = (String) claims.get(CLAIM_KEY_GRANT);
//            Long applicationId = Long.valueOf((Integer) claims.get(CLAIM_KEY_APP));
//            JwtSubject subject = new JwtSubject(userId, applicationId, companyId, authorities);
//            subject.setTokenCreation((Long) claims.get(CLAIM_KEY_CREATED));
//            return subject;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    public static boolean isTerminatorStatic(String token) {
//        JwtSubject subject = getDetailsFromTokenStatic(token);
//        assert subject != null;
//        return subject.getCompanyId().equals(1L);
//    }
//
//    public boolean isTerminator(String token) {
//        JwtSubject subject = getDetailsFromToken(token);
//        Assert.notNull(subject, "Not allowed");
//        return subject.getCompanyId().equals(termiiId);
//    }
//
//
//    private Claims getClaimsFromToken(String token) {
//        Claims claims;
//        try {
//            claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
//        } catch (Exception e) {
////            e.printStackTrace();
//            claims = null;
//        }
//        return claims;
//    }
//
//    private static Claims getClaimsFromTokenStatic(String token) {
//        Claims claims;
//        try {
//            claims = Jwts.parser()
//                    .verifyWith(getSecretKey())
//                    .build()
//                    .parseSignedClaims(token)
//                    .getPayload();
//        } catch (Exception e) {
////            e.printStackTrace();
//            claims = null;
//        }
//        return claims;
//    }
//
//    private static SecretKey getSecretKey() {
//        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
//    }
//
//    public Date getExpirationDate(String token) {
//        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
//
//        return claims.getExpiration();
//    }
//
//    public boolean validate(String token) {
//        try {
////            System.out.println("SECRET VALIDATE :: " + jwtSecret +"SECRET TOKEN :: "+token);
//            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
//            return true;
//        } catch (SignatureException ex) {
//            ex.printStackTrace();
////            logger.error("Invalid JWT signature - {}", ex.getMessage());
//        } catch (MalformedJwtException ex) {
//            ex.printStackTrace();
////            logger.error("Invalid JWT token - {}", ex.getMessage());
//        } catch (ExpiredJwtException ex) {
//            ex.printStackTrace();
////            logger.error("Expired JWT token - {}", ex.getMessage());
//        } catch (UnsupportedJwtException ex) {
//            ex.printStackTrace();
////            logger.error("Unsupported JWT token - {}", ex.getMessage());
//        } catch (IllegalArgumentException ex) {
//            ex.printStackTrace();
////            logger.error("JWT claims string is empty - {}", ex.getMessage());
//        }
//        return false;
//    }
//
//    public static boolean validateStatic(String token) {
//        try {
////            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
//            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
//            return true;
//        } catch (SignatureException ex) {
//            ex.printStackTrace();
////            logger.error("Invalid JWT signature - {}", ex.getMessage());
//        } catch (MalformedJwtException ex) {
//            ex.printStackTrace();
////            logger.error("Invalid JWT token - {}", ex.getMessage());
//        } catch (ExpiredJwtException ex) {
//            ex.printStackTrace();
////            logger.error("Expired JWT token - {}", ex.getMessage());
//        } catch (UnsupportedJwtException ex) {
//            ex.printStackTrace();
////            logger.error("Unsupported JWT token - {}", ex.getMessage());
//        } catch (IllegalArgumentException ex) {
//            ex.printStackTrace();
////            logger.error("JWT claims string is empty - {}", ex.getMessage());
//        }
//        return false;
//    }
//
//}
