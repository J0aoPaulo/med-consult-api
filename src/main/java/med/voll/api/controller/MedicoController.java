package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.medico.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    private final MedicoRepository medicoRepository;

    public MedicoController (MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosCompletosMedico> cadastrar(@RequestBody @Valid DadosCadastroMedico dados) {
        var medico = new Medico(dados);
        medicoRepository.save(medico);

        var uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(medico.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new DadosCompletosMedico(medico));
    }

    @GetMapping
    public ResponseEntity<Page<ListagemDadosMedicos>> listarMedicos(Pageable pageable) {
        var page =  medicoRepository.findAllByAtivoTrue(pageable).map(ListagemDadosMedicos::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosCompletosMedico> listarUnicoMedico(@PathVariable Long id) {
        var medico = medicoRepository.getReferenceById(id);

        return ResponseEntity.ok(new DadosCompletosMedico(medico));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosCompletosMedico> atualizar(@RequestBody @Valid DadosAtualizadosMedico dados) {
        var medico = medicoRepository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosCompletosMedico(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        var medico = medicoRepository.getReferenceById(id);
        medico.inativar();

        return ResponseEntity.noContent().build();
    }
}