package med.voll.api.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.PathVariable;

import java.lang.annotation.Native;
import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByAtivoTrue(Pageable pageable);

    @Query(value = """
            SELECT m FROM medicos m
            WHERE
            m.ativo = true
            AND
            m.especialidade = :especialidade
            m.id NOT IN(SELECT c.id.medico FROM consultas c WHERE c.data_consulta = :data)
            ORDER BY rand()
            LIMIT 1
            """, nativeQuery = true)
    Medico escolherMedicoAleatorioDisponivel(@Param("especialidade") Especialidade especialidade, @Param("data") LocalDateTime data);
}