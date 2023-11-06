package com.timurturbil.expansetrackerbackend.service;


import com.timurturbil.expansetrackerbackend.entity.User;
import com.timurturbil.expansetrackerbackend.utils.Constants;
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
import org.springframework.beans.factory.annotation.Value;

@Service
public class JwtService {

    @Value("${jwt.secret.key}")
    private String secretKey;

    public int extractUserId(String bearerToken) {
        String rawToken = bearerToken.split(" ")[1];
        return extractClaim(rawToken, "id", Integer.class);
    }

    public String extractUsername(String token) {
        return extractClaim(token, "username", String.class);
    }

    public Date extractExpirationDate(String token) {
        return extractClaim(token, "exp", Date.class);
    }

    public <T> T extractClaim(String token, String claimName, Class<T> requiredType) {
        final Claims claims = Jwts
                                .parserBuilder()
                                .setSigningKey(getSignInKey())
                                .build()
                                .parseClaimsJws(token).getBody();
        return claims.get(claimName, requiredType);
    }

    public Boolean isTokenExpired(String token) {
        return extractExpirationDate(token).before(new Date());
    }

    public GenericResponse<String> generateToken(User user) {
        try {
            //CREATE CLAIMS
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", user.getId());
            claims.put("username", user.getUsername());
            claims.put("email", user.getEmail());
            claims.put("firstName", user.getFirstName());
            claims.put("lastName", user.getLastName());

            //CREATE TOKEN
            String token = Jwts
                    .builder()
                    .setClaims(claims)
                    .setSubject(user.getUsername())
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 100)) // 1000 * 60 * 60 * 10 (10 hours)
                    .signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();

            //RETURN RESPONSE
            return new GenericResponse<>(Constants.SUCCESS, "User logged in successfully", token);
        } catch (Exception e) {
            return new GenericResponse<>(Constants.ERROR, e.getMessage(), null);
        }
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
