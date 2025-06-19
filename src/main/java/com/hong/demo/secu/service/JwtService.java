package com.hong.demo.secu.service; 

import java.util.Date;
import java.security.Key;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.io.Decoders;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;

@Service
public class JwtService {

    @Value("${security.jwt.secret-key}")
    private String secretKey;

    @Value("${security.jwt.expiration-time}")
    private long jwtExpiration;
    
    // static final long EXPIRATIONTIME = 86400000; // 1 day in ms. Should be shorter in production.
    
    // Generate secret key. Only for the demonstration purposes.
    // In production, you should read it from the application configuration.
    // static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Generate signed JWT token
    public String getToken(String username) {
        String token = Jwts.builder()
                .setSubject(username)
                // .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                // .signWith(key)
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSignInKey())
                .compact();
        return token;
    }

    // verify thea token, and get username
    public String getAuthUser(String token) {
        String username = 
                Jwts.parserBuilder()
                // .setSigningKey(key)
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody().getSubject();

        if (username != null)
                return username;
        return null;
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
