CREATE TABLE consultas(
    id bigint NOT NULL auto_increment,
    id_medico bigint NOT NULL,
    id_paciente bigint NOT NULL,
    data_consulta datetime NOT NULL,

    PRIMARY KEY(id),
    CONSTRAINT fk_consultas_id_medico FOREIGN KEY(id_medico) REFERENCES medicos(id),
    CONSTRAINT fk_consultas_id_paciente FOREIGN KEY(id_paciente) REFERENCES pacientes(id)
);