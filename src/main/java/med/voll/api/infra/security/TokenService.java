package med.voll.api.infra.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import med.voll.api.domain.usuario.Usuario;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class TokenService {

    public String gerarToken(Usuario usuario) {
        String secretKey = System.getenv("SECRET_KEY");
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        String jwt = Jwts.builder()
                .issuer("API Voll.med")
                .subject(usuario.getLogin())
                .expiration(dataExpiracao())
                .signWith(key)
                .compact();
        try {
            Jwts.parser().verifyWith(key).build().parseSignedClaims(jwt);
        } catch (JwtException e) {
            throw new JwtException("Erro ao gerar JWT", e);
        }
        return jwt;
    }

    private Date dataExpiracao() {
        Instant dataExp = Instant.now().plus(2, ChronoUnit.HOURS);
        return Date.from(dataExp);
    }
}