package med.voll.api.domain.paciente;

import med.voll.api.domain.endereco.Endereco;

public record DadosCompletosPaciente(String nome, String email, String telefone, String cpf, Endereco endereco) {

    public DadosCompletosPaciente(Paciente paciente) {
        this(paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf(), paciente.getEndereco());
    }
}
