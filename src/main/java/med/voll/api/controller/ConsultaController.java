package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.consulta.ConsultaService;
import med.voll.api.domain.consulta.DadosCadastroConsulta;
import med.voll.api.domain.consulta.DadosConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

        return ResponseEntity.created(uri)
                .body(new DadosCadastroConsulta(null,
                        consultaAgendada.getMedico().getId(),
                        consultaAgendada.getPaciente().getId(),
                        consultaAgendada.getDataConsulta()));
    }
}