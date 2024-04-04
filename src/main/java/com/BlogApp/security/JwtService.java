package com.BlogApp.security;

import com.BlogApp.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import static com.BlogApp.config.AppConstants.SECRET_KEY;

@Service
public class JwtService {
public String generateToken(User user){
    return Jwts
            .builder().
            subject(user.getUsername())
            .issuedAt(new Date(System.currentTimeMillis()))
            .expiration(new Date(System.currentTimeMillis()+24*60*60*1000))
            .signWith(getSignKey())
            .compact();
}
private Claims extractAllClaims(String token){
    return Jwts
            .parser()
            .verifyWith(getSignKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
}
public <T> T extractClaims(String token, Function<Claims,T> resolver){
    Claims claims = extractAllClaims(token);
    return resolver.apply(claims);
}

    private SecretKey getSignKey() {
    byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
    return Keys.hmacShaKeyFor(keyBytes);
    }
    public String extractUsername(String token){
    return extractClaims(token,Claims::getSubject);
    }
    public boolean isTokenExpired(String token){
    return extractExpTime(token).before(new Date());
    }
    public Date extractExpTime(String token){
    return extractClaims(token,Claims::getExpiration);
    }
    public boolean isValid(String token , UserDetails user){
    String username = extractUsername(token);
    return username.equals(user.getUsername());
    }
}
