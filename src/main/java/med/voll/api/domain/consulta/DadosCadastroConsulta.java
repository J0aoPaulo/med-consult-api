package med.voll.api.domain.consulta;

import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.paciente.Paciente;

import java.time.LocalDateTime;

public record DadosCadastroConsulta(Long idMedico, Long idPaciente, LocalDateTime dataConsulta) {

    public DadosCadastroConsulta(Consulta consulta) {
        this(consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getDataConsulta());
    }
}