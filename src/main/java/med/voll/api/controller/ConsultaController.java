package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("consultas")
public class ConsultaController {

    @Autowired
    ConsultaService consultaService;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosCadastroConsulta> cadastrarConsulta(@RequestBody @Valid DadosConsulta consultaDto) {
        var consultaAgendada = consultaService.agendarConsulta(consultaDto);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/consulta")
                .buildAndExpand(consultaAgendada.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new DadosCadastroConsulta(consultaAgendada));
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<DadosCancelarConsulta> cancelarConsulta(@RequestBody @Valid DadosCancelarConsulta dados) {
        consultaService.cancelarConsulta(dados);
        return ResponseEntity.noContent().build();
    }
}