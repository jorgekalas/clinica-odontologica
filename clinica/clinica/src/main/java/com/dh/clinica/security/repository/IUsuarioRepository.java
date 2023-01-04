package com.dh.clinica.security.repository;


import com.dh.clinica.security.entity.AppUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly=true)
public interface IUsuarioRepository extends JpaRepository<AppUsuario, Long> {
    Optional<AppUsuario> findByEmail(String email);
}
