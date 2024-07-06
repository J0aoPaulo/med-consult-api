package med.voll.api.domain.consulta;

import med.voll.api.domain.consulta.validacoes.ValidarAgendamentoConsulta;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.infra.exception.exceptions.AppointNotFoundException;
import med.voll.api.infra.exception.exceptions.IdNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final PacienteRepository pacienteRepository;
    private final MedicoRepository medicoRepository;
    private final List<ValidarAgendamentoConsulta> validacoes;

    public ConsultaService(ConsultaRepository consulta, MedicoRepository medico, PacienteRepository paciente, List<ValidarAgendamentoConsulta> validacoes) {
        this.consultaRepository = consulta;
        this.medicoRepository = medico;
        this.pacienteRepository = paciente;
        this.validacoes = validacoes;
    }

    public Consulta agendarConsulta(DadosConsulta dados) {
        var medico = selecionarMedico(dados);
        verificarExistenciaMedicoPaciente(dados.idPaciente(), dados.idMedico());
        validacoes.forEach(v -> v.validar(dados));

        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var consulta = new Consulta(null, medico, paciente, dados.dataConsulta());
        consultaRepository.save(consulta);
        return consulta;
    }

    public void cancelarConsulta(DadosCancelarConsulta dadosCancelamento) {
        verificarExistenciaMedicoPaciente(dadosCancelamento.idPaciente(), dadosCancelamento.idMedico());
        verificarExistenciaConsulta(dadosCancelamento);

        var paciente = pacienteRepository.getReferenceById(dadosCancelamento.idPaciente());
        var medico = medicoRepository.getReferenceById(dadosCancelamento.idMedico());
        var consulta = consultaRepository.findByPacienteIdAndMedicoIdAndDataConsulta(medico.getId(), paciente.getId(), dadosCancelamento.dataConsulta());
        consultaRepository.delete(consulta);
    }

    private Medico selecionarMedico(DadosConsulta dadosConsulta) {
        Medico medico;
        if (dadosConsulta.idMedico() == null) {
            medico = medicoRepository.escolherMedicoAleatorioDisponivel(dadosConsulta.especialidade(), dadosConsulta.dataConsulta());
        } else {
            medico = medicoRepository.getReferenceById(dadosConsulta.idMedico());
        }

        if (medico == null) {
            throw new IdNotFoundException("Nenhum médico disponível encontrado ou ID do médico é inválido.");
        }

        return medico;
    }

    private void verificarExistenciaConsulta(DadosCancelarConsulta dados) {
        boolean consultaExiste = consultaRepository.existsByDataConsulta(dados.dataConsulta());

        if (!consultaExiste)
            throw new AppointNotFoundException("Consulta médica não encontrada no banco de dados");
    }

    private void verificarExistenciaMedicoPaciente(Long idPaciente, Long idMedico) {
        if (!pacienteRepository.existsById(idPaciente))
            throw new IdNotFoundException("Dados do paciente não encontrados no banco de dados");

        if (idMedico != null && !medicoRepository.existsById(idMedico))
            throw new IdNotFoundException("Dados do médico não encontrados no banco de dados");
    }
}
