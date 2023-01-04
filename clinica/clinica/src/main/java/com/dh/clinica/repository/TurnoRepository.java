package com.dh.clinica.repository;

import com.dh.clinica.entities.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Creación del repositorio, que extiende de JPA
@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long> {
}
