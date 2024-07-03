package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.paciente.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteRepository pacienteRepository;

    public PacienteController(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosCompletosPaciente> salvarPaciente(@RequestBody @Valid DadosPaciente dadosPaciente) {
        Paciente paciente = new Paciente(dadosPaciente);
        pacienteRepository.save(paciente);

        var uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{/id}")
                .buildAndExpand(paciente.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new DadosCompletosPaciente(paciente));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosCompletosPaciente> listarPaciente(@PathVariable Long id) {
        var paciente = pacienteRepository.getReferenceById(id);

        return ResponseEntity.ok(new DadosCompletosPaciente(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<DadosCompletosPaciente>> listarTodosPacientes(Pageable pageable) {
        var page = pacienteRepository.findAll(pageable).map(DadosCompletosPaciente::new);

        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosCompletosPaciente> atualizarPaciente(@RequestBody @Valid DadosAtualizadosPaciente dadosPaciente) {
        var paciente = pacienteRepository.getReferenceById(dadosPaciente.id());
        paciente.atualizarDados(dadosPaciente);

        return ResponseEntity.ok(new DadosCompletosPaciente(paciente));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluirPaciente(@PathVariable Long id) {
        var paciente = pacienteRepository.getReferenceById(id);
        pacienteRepository.delete(paciente);

        return ResponseEntity.noContent().build();
    }
}