package com.matheus.api_auto_report.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class JwtTokenService {

    private static final String SECRET_KEY = "java";

    private static final String ISSUER = "pizzurg-api";

    public String generateToken(UserDetailsImpl userDetails) {
        try {
            // Define o algoritmo HMAC SHA256 para criar a assinatura do token passando a chave secreta definida
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            return JWT.create()
                    .withIssuer(ISSUER) // Define o emissor do token
                    .withIssuedAt(creationDate()) // Define a data de emissão do token
                    .withExpiresAt(expirationDate()) //Define a data de expiração do token
                    .withSubject(userDetails.getUsername()) //Define o assunto do token, neste caso o nome do usuário
                    .withClaim("userId", userDetails.getUser().getId())
                    .sign(algorithm); //assina o token utiliando o algoritmo HMAC256.
        } catch (JWTCreationException ex) {
            throw new JWTCreationException("Error on generating JWT TOKEN.", ex);
        }
    }

    public String getSubjectFromToken(String token) {
        try {
            // Define o algoritmo HMAC SHA256 para verificar a assinatura do token passando a chave secreta definida
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            return JWT.require(algorithm)
                    .withIssuer(ISSUER) // Define o emissor do token
                    .build()
                    .verify(token) // Verifica a validade do token
                    .getSubject(); // Obtém o assunto (neste caso, o nome de usuário) do token
        } catch (JWTVerificationException ex) {
            throw new JWTVerificationException("Invalid Token or expired", ex);
        }
    }

    public JwtUserPayload getUserDataFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            var decoded = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(token);

            Long userId = decoded.getClaim("userId").asLong();
            String username = decoded.getSubject();

            return new JwtUserPayload(userId, username);
        } catch (JWTVerificationException ex) {
            throw new JWTVerificationException("Invalid token or expired", ex);
        }
    }


    private Instant creationDate() {
        return ZonedDateTime.now(ZoneId.of("America/Brasilia")).toInstant();
    }

    private Instant expirationDate() {
        return ZonedDateTime.now(ZoneId.of("America/Brasilia")).plusHours(4).toInstant();
    }
}
