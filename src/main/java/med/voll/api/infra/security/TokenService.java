package med.voll.api.infra.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import med.voll.api.domain.usuario.Usuario;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class TokenService {

    private SecretKey getKey() {
        return Keys
                .hmacShaKeyFor(Decoders.BASE64
                        .decode(System.getenv("SECRET_KEY")));
    }

    public String gerarToken(Usuario usuario) {
        try {
            return Jwts.builder()
                    .issuer("API Voll.med")
                    .subject(usuario.getLogin())
                    .expiration(dataExpiracao())
                    .signWith(getKey())
                    .compact();
        } catch (JwtException e) {
            throw new JwtException("Erro ao gerar JWT", e);
        }
    }

    public String getSubject(String tokenJWT) {
        try {
            return Jwts.parser()
                    .verifyWith(getKey())
                    .build()
                    .parseSignedClaims(tokenJWT)
                    .getPayload()
                    .getSubject();
        } catch (MalformedJwtException e) {
            throw new MalformedJwtException("Json Web Token nulo", e);
        } catch (SignatureException e) {
            throw  new SignatureException("Json Web Token inválido", e);
        }
    }

    private Date dataExpiracao() {
        int DUAS_HORAS = 7200000;
        return new Date(System.currentTimeMillis() + DUAS_HORAS);
    }
}