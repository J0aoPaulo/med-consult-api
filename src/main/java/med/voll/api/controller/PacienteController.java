package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.paciente.DadosCompletosPaciente;
import med.voll.api.paciente.DadosPaciente;
import med.voll.api.paciente.Paciente;
import med.voll.api.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    PacienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosCompletosPaciente> salvarPaciente(@RequestBody @Valid DadosPaciente dadosPaciente) {
        Paciente paciente = new Paciente(dadosPaciente);
        repository.save(paciente);

        var uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{/id}")
                .buildAndExpand(paciente.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new DadosCompletosPaciente(paciente));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosCompletosPaciente> listarPaciente(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);

        return ResponseEntity.ok(new DadosCompletosPaciente(paciente));
    }
}