package com.dh.clinica.repository;

import com.dh.clinica.entities.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Creación del repositorio, que extiende de JPA
@Repository
public interface DomicilioRepository extends JpaRepository<Domicilio, Long> {
}
