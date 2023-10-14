package com.timurturbil.expansetrackerbackend.service;


import com.timurturbil.expansetrackerbackend.dto.GenericResponse;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Value;

@Service
public class JwtService {

    @Value("${jwt.secret.key}")
    private String secretKey;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = Jwts
                                .parserBuilder()
                                .setSigningKey(getSignInKey())
                                .build()
                                .parseClaimsJws(token).getBody();
        return claimsResolver.apply(claims);
    }

    public Boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    public GenericResponse<String> generateToken(String username) {
        try {
            //CREATE CLAIMS
            Map<String, Object> claims = new HashMap<>();

            //CREATE TOKEN
            String token = Jwts
                    .builder()
                    .setClaims(claims)
                    .setSubject(username)
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 100)) // 1000 * 60 * 60 * 10 (10 hours)
                    .signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();

            //RETURN RESPONSE
            return new GenericResponse<>("success", "User logged in successfully", token);
        } catch (Exception e) {
            return new GenericResponse<>("error", e.getMessage(), null);
        }
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
