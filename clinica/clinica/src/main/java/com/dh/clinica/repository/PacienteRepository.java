package com.dh.clinica.repository;

import com.dh.clinica.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//Creación del repositorio, que extiende de JPA
@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}
