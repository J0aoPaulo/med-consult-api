package med.voll.api.domain.consulta;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosCancelarConsulta(
        @NotNull
        Long idMedico,
        @NotNull
        Long idPaciente,
        @NotNull
        LocalDateTime dataConsulta,
        @NotNull
        MotivoCancelamentoEnum motivoCancelamento) {

    public DadosCancelarConsulta(DadosCancelarConsulta dados) {
        this(dados.idMedico, dados.idPaciente, dados.dataConsulta, dados.motivoCancelamento);
    }
}