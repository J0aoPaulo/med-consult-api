package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.DadosCadastroConsulta;
import med.voll.api.domain.consulta.DadosConsulta;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consultas")
public class ConsultaController {

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarConsulta(@RequestBody @Valid DadosConsulta dados) {
        System.out.println(dados);
        return ResponseEntity.ok(new DadosCadastroConsulta(null, null, null, null));

        /*var consulta = new Consulta(consultaDTO.idMedico(), consultaDTO.idPaciente());
        consultaRepository.save(consulta);*/

        /*URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/consulta")
                .buildAndExpand(consulta.getId())
                .toUri();*/

        //return ResponseEntity.created(uri).body(new DadosCadastroConsulta(consulta));
    }
}