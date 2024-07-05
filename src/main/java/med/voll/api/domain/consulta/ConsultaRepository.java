package med.voll.api.domain.consulta;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    boolean existsByDataConsulta(LocalDateTime dataConsulta);
    Consulta findByPacienteIdAndMedicoIdAndDataConsulta(Long idMedico, Long IdPaciente, LocalDateTime localDateTime);
}