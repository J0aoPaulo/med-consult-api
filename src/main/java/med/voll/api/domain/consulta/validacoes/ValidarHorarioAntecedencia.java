package med.voll.api.domain.consulta.validacoes;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.DadosConsulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidarHorarioAntecedencia {

    public static void validar(DadosConsulta dados) {
        var horarioConsultaValido = Duration.between(dados.dataConsulta(), LocalDateTime.now()).toMinutes() >= 30;

        if (!horarioConsultaValido)
            throw new ValidationException("Consulta deve ser agendada com no mínimo 30 minutos de antecedência");
    }
}