package com.dh.clinica.service;

import com.dh.clinica.entities.Turno;
import com.dh.clinica.exceptions.BadRequestException;
import com.dh.clinica.exceptions.ResourceNotFoundException;
import java.util.List;
import java.util.Optional;

public interface TurnoInterface {

        //Declaración de los métodos a implementar

        List<Turno> listarTurnos();
        Optional<Turno> buscarTurnoPorId(Long id);
        Turno registrarTurno(Turno turno) throws BadRequestException;
        Turno actualizarTurno(Turno turno);
        void eliminarTurno(Long id) throws ResourceNotFoundException;

}

