package med.voll.api.domain.consulta.validacoes;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.DadosConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidarHorarioFuncionamento implements ValidarAgendamentoConsulta{

    public void validar(DadosConsulta dados) {
        var dataConsulta = dados.dataConsulta();

        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDeAbrir = dataConsulta.getHour() < 7;
        var depoisDeFechar = dataConsulta.getHour() > 18;

        if (domingo || antesDeAbrir || depoisDeFechar)
            throw new ValidationException("Consulta fora do horario de funcionamento da clinica.");
    }
}