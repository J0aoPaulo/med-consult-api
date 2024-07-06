package med.voll.api.domain.consulta.validacoes;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosConsulta;
import org.springframework.stereotype.Component;

@Component
public class ValidarDuasConsultasNoDia implements ValidarAgendamentoConsulta {

    private final ConsultaRepository consultaRepository;

    public ValidarDuasConsultasNoDia(ConsultaRepository consulta) {
        this.consultaRepository = consulta;
    }

    public void validar(DadosConsulta dados) {
        var consultaIndisponivel = consultaRepository
                .existsByPacienteIdAndDataConsulta(dados.idPaciente(), dados.dataConsulta());

        if (consultaIndisponivel)
            throw new ValidationException("O paciente não pode realizar mais de uma consulta no mesmo dia");
    }
}
