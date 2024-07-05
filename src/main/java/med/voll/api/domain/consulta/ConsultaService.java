package med.voll.api.domain.consulta;

import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.infra.exception.exceptions.IdNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;

    public ConsultaService(ConsultaRepository consulta, MedicoRepository medico, PacienteRepository paciente) {
        this.consultaRepository = consulta;
        this.medicoRepository = medico;
        this.pacienteRepository = paciente;
    }

    public Consulta agendarConsulta(DadosConsulta dados) {
        verificarExistenciaMedicoPaciente(dados);

        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var medico = selecionarMedico(dados);
        var consulta = new Consulta(null, medico, paciente, dados.dataConsulta());
        consultaRepository.save(consulta);
        return consulta;
    }

    /*public void cancelarConsulta(DadosCancelarConsulta dadosCancelamento) {
        var consultaCancelada = new DadosCancelarConsulta(dadosCancelamento);

        consultaRepository.deleteByIdPacienteAndDataConsulta(consultaCancelada.idPaciente(), consultaCancelada.dataConsulta());
    }*/

    private Medico selecionarMedico(DadosConsulta dadosConsulta) {
        if(dadosConsulta.idMedico() != null)
            return medicoRepository.getReferenceById(dadosConsulta.idMedico());

        return medicoRepository.escolherMedicoAleatorioDisponivel(dadosConsulta.especialidade(), dadosConsulta.dataConsulta());
    }

    private void verificarExistenciaMedicoPaciente(DadosConsulta dadosConsulta) {
        if (!pacienteRepository.existsById(dadosConsulta.idPaciente()))
            throw new IdNotFoundException("Id paciente não encontrado no banco de dados");

        if (dadosConsulta.idMedico() != null && !medicoRepository.existsById(dadosConsulta.idMedico()))
            throw new IdNotFoundException("Id do médico não encontrado no banco de dados");
    }
}
