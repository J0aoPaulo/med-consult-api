package med.voll.api.domain.consulta;

import java.time.LocalDateTime;

public record DadosCadastroConsulta(Long id, Long idMedico, Long idPaciente, LocalDateTime dataConsulta) {
}