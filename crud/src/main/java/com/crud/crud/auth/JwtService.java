package com.crud.crud.auth;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    private static final String SECRET_KEY="mySecretKeyForJWTTokenGenerationAndValidation12345";
    private static final long EXPIRATION_TIME=86400000; 


    public String generateToken(String username){ // generate a token for a username
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String subject){
        return Jwts.builder()
        .setClaims(claims)
        .setSubject(subject) // username
        .setIssuedAt(new Date()) // when token created
        .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME)) // when token expires
        .signWith(getSigningKey(), SignatureAlgorithm.HS256) // sign with secret
        .compact();
    }

    private Key getSigningKey(){ // create a signing key from secret key
        byte[] keyBytes = SECRET_KEY.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String token){ // get username from token
        return extractAllClaims(token).getSubject();
    }
    private Claims extractAllClaims(String token){ // get all information from token
        return Jwts.parserBuilder()
        .setSigningKey(getSigningKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
    }

    private Boolean isTokenExpired(String token){
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    public Boolean validateToken(String token, String username){
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    /*
     ex:
     Token: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huIiwiZXhwIjoxNjk...

    Contains:
    - Username: "john"
    - Issued at: 2025-11-13 20:00:00
    - Expires: 2025-11-14 20:00:00 (24 hours later)
    - Signature: (proves it's legit, can't be faked)
     */

    
}
