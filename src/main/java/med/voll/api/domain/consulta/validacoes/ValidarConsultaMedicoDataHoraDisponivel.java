package med.voll.api.domain.consulta.validacoes;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosConsulta;
import org.springframework.stereotype.Component;

@Component
public class ValidarConsultaMedicoDataHoraDisponivel implements ValidarAgendamentoConsulta {

    private final ConsultaRepository consultaRepository;

    public ValidarConsultaMedicoDataHoraDisponivel(ConsultaRepository consulta) {
        this.consultaRepository = consulta;
    }

    public void validar(DadosConsulta dados) {
        var consultaNaoDisponivel = consultaRepository.existsByMedicoIdAndDataConsulta(dados.idMedico(), dados.dataConsulta());

        if (consultaNaoDisponivel)
            throw new ValidationException("Medico não disponível para consultas nessa data e hora");
    }
}