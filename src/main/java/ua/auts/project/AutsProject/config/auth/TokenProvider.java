package ua.auts.project.AutsProject.config.auth;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import ua.auts.project.AutsProject.entities.User;

@Service
public class TokenProvider {
    @Value("${jwt.secret}")
    private String JWT_SECRET;

    public String generateAccesToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
            return JWT.create()
                .withSubject(user.getUsername())
                .withClaim("username", user.getUsername())
                .withExpiresAt(genAccesExpirationDate())
                .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new JWTCreationException("Error while generating token", exception);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
            return JWT.require(algorithm)
                .build()
                .verify(token)
                .getSubject();
        } catch (JWTVerificationException exception) {
            throw new JWTVerificationException("Error while validating token", exception); 
        }
    }

    public Instant genAccesExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("+03:00"));
    }
}
