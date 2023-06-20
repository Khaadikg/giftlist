package com.peaksoft.giftlistm5.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class JwtTokenUtil {
    @Value("java_moscow5")
    private String jwtSecret;
    private final long JWT_TOKEN_VALIDITY = 24*7*60*60*100l; //ONE WEEK

    public String createToken(Map<String,Object> claims, String subject){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt( new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+JWT_TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.ES512,jwtSecret)
                .compact();
    }

    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims = new HashMap<>();
        return createToken(claims,userDetails.getUsername());
    }

    public Date getExpirationDateToken(String token){
        return getClaimFromToken(token, Claims::getExpiration);
    }
    private <T> T getClaimFromToken(String token, Function<Claims,T> function){
        final Claims claims = getAllClaimsfromToken(token);
        return function.apply(claims);
    }
    private Claims getAllClaimsfromToken(String token){
        return  Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
    }
    private Boolean isTokenExpiration(String token){
        final Date expiration = getExpirationDateToken(token);
        return  expiration.before(new Date());
    }

    public String getUserNameFromToken(String token){
        return getClaimFromToken(token,Claims::getSubject);
    }

    public Boolean validationToken(String token, UserDetails userDetails){
        final String username = getUserNameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpiration(token));
    }
}
