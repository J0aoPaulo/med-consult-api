package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.usuario.DadosAutenticacao;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class UsuarioController {

    private final AuthenticationManager manager;

    public UsuarioController(AuthenticationManager manager) {
        this.manager = manager;
    }

    @PostMapping
    public ResponseEntity<Void> realizarLogin(@RequestBody @Valid DadosAutenticacao autenticacao) {
        var token = new UsernamePasswordAuthenticationToken(autenticacao.login(), autenticacao.senha());
        var authenticator = manager.authenticate(token);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{senha}")
    public ResponseEntity<String> hash(@PathVariable String senha) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        var novaSenha = passwordEncoder.encode(senha);

        return ResponseEntity.ok().body(novaSenha);
    }
}
