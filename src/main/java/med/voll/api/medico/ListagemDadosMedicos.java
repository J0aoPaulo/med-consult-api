package med.voll.api.medico;

public record ListagemDadosMedicos(Long id, String nome, String email, String crm, Especialidade especialidade) {

    public ListagemDadosMedicos(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
