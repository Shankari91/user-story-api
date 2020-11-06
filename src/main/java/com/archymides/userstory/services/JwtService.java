package com.archymides.userstory.services;

import com.archymides.userstory.Exceptions.UnauthorizedException;
import com.archymides.userstory.entities.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("jwtService")
public
class JwtService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    String generateJwtToken(User user) {
        String token = Jwts.builder()
                .setSubject(String.valueOf(user.getId()))
                .setExpiration(new Date(System.currentTimeMillis() + 864000))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
        return token;
    }

    public String getUser(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (Exception e) {
            throw new UnauthorizedException();
        }
    }
}
