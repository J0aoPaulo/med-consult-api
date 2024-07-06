package med.voll.api.domain.consulta.validacoes;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.DadosConsulta;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidarMedicoPacienteAtivo implements ValidarAgendamentoConsulta{

    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;

    public ValidarMedicoPacienteAtivo(PacienteRepository paciente, MedicoRepository medico) {
        this.pacienteRepository = paciente;
        this.medicoRepository = medico;
    }

    public void validar(DadosConsulta dados) {
        var pacienteAtivo = pacienteRepository.existsById(dados.idPaciente());
        if (dados.idMedico() == null && !pacienteAtivo)
            throw new ValidationException("Paciente não está ativo no sistema");

        if (dados.idMedico() == null) return;

        var medicoAtivo = medicoRepository.existsById(dados.idMedico());
        if (!medicoAtivo)
            throw new ValidationException("Medico não está ativo no sistema");
    }
}