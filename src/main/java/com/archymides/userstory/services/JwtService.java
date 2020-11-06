package com.archymides.userstory.services;

import com.archymides.userstory.entities.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;

@Service("jwtService")
class JwtService {

    String generateJwtToken(User user) {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String jws = Jwts.builder().setSubject(String.valueOf(user.getId())).signWith(key).compact();
        return jws;

    }
}
