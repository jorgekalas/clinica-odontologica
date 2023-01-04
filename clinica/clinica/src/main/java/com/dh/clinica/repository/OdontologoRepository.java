package com.dh.clinica.repository;

import com.dh.clinica.entities.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Creación del repositorio, que extiende de JPA
@Repository
public interface OdontologoRepository extends JpaRepository<Odontologo, Long> {
}
