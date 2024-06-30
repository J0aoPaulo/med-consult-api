package med.voll.api.domain.medico;

import med.voll.api.domain.endereco.Endereco;

public record DadosCompletosMedico(Long id, String nome, String email, String telefone,
                                   String crm, Especialidade especialidade, Endereco endereco) {

    public DadosCompletosMedico(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getTelefone(),
                medico.getCrm(), medico.getEspecialidade(), medico.getEndereco());
    }
}