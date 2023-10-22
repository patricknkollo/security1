package com.security.example.secu.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

/**
 * cette classe est le service contenant quasi toutes les methodes dont nous aurons besoin dans notre filtre
 * JwtAuthenticationFilter.
 */
@Service
public class JwtService {

    private static final String SECRET_KEY = "5ba7599032960e07d3650f41cff9fb928061b21268e21667824511fa37e59ecb";
    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public String extractIssuer(String token){
        return extractClaim(token, Claims::getIssuer);
    }

    public Date extractDateOfIssue(String token){
        return extractClaim(token, Claims::getIssuedAt);
    }

    public Date extractDateOfExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     *
     * @param token
     * @param claimsTFunction
     * @return
     * @param <T>
     *
     *     les claims sont les données de l'utlisateur contenu dans le token
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsTFunction){
        Claims claims = extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails){
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+1000*30))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenExpired(String token){
        Date expiration = extractDateOfExpiration(token);
        return (expiration.getTime() - System.currentTimeMillis()>1000*30);
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        Date expiration = extractDateOfExpiration(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }
}
