package med.voll.api.endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;

    public Endereco(DadosEndereco dados) {
        this.logradouro = dados.logradouro();
        this.bairro = dados.bairro();
        this.cep = dados.cep();
        this.uf = dados.uf();
        this.cidade = dados.cidade();
        this.numero = dados.numero();
        this.complemento = dados.complemento();
    }

    public void atualizarEndereco(DadosEndereco dadosUpdated) {
        if(dadosUpdated != null) {
            this.logradouro = Optional.ofNullable(dadosUpdated.logradouro()).orElse(this.logradouro);
            this.bairro = Optional.ofNullable(dadosUpdated.bairro()).orElse(this.bairro);
            this.cep = Optional.ofNullable(dadosUpdated.cep()).orElse(this.cep);
            this.uf = Optional.ofNullable(dadosUpdated.uf()).orElse(this.uf);
            this.cidade = Optional.ofNullable(dadosUpdated.cidade()).orElse(this.cidade);
            this.numero = Optional.ofNullable(dadosUpdated.numero()).orElse(this.numero);
            this.complemento = Optional.ofNullable(dadosUpdated.complemento()).orElse(this.complemento);
        }
    }
}
