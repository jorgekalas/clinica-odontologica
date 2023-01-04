package com.dh.clinica.service;

import com.dh.clinica.entities.Paciente;
import com.dh.clinica.exceptions.ResourceNotFoundException;
import java.util.List;
import java.util.Optional;

public interface PacienteServiceInterface {

    //Declaración de los métodos a implementar
    List<Paciente> listarPacientes();
    Optional<Paciente> buscarPacientePorId(Long id);
    Paciente registrarPaciente(Paciente paciente);
    Paciente actualizarPaciente(Paciente paciente);
    void eliminarPaciente(Long id) throws ResourceNotFoundException;

}