package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.usuario.DadosAutenticacao;
import med.voll.api.domain.usuario.Usuario;
import med.voll.api.infra.security.DadosToken;
import med.voll.api.infra.security.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    private final AuthenticationManager manager;
    private final TokenService tokenService;

    public AutenticacaoController(AuthenticationManager manager, TokenService token) {
        this.manager = manager;
        this.tokenService = token;
    }

    @PostMapping
    public ResponseEntity<DadosToken> realizarLogin(@RequestBody @Valid DadosAutenticacao autenticacao) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(autenticacao.login(), autenticacao.senha());
        var authenticator = manager.authenticate(authenticationToken);
        var tokenJWT = tokenService.gerarToken((Usuario) authenticator.getPrincipal());

        return ResponseEntity.ok(new DadosToken(tokenJWT));
    }
}
