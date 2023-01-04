package com.dh.clinica.service;

import com.dh.clinica.entities.Paciente;
import com.dh.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.repository.PacienteRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements PacienteServiceInterface{

    //Atributo para logging

    private static final Logger logger = Logger.getLogger(PacienteService.class);

    //Inyección de dependencia del repositorio
    @Autowired
    PacienteRepository repository;

    //Sobreescritura de métodos
    @Override
    public List<Paciente> listarPacientes() {
        return repository.findAll();
    }

    @Override
    public Optional<Paciente> buscarPacientePorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Paciente registrarPaciente(Paciente paciente) {

        if(paciente.getApellido() != null && paciente.getNombre() != null && paciente.getEmail() != null && paciente.getFechaIngreso() != null && paciente.getDomicilio() != null) {
            repository.save(paciente);
            logger.info("paciente registrado con exito: " + paciente);
            return paciente;
        } else{
            logger.error("No se ha podido registrar al paciente: " + paciente);
        }
        return paciente;
    }

    @Override
    public Paciente actualizarPaciente(Paciente paciente) {
        if(buscarPacientePorId(paciente.getId()).isPresent()){
            repository.save(paciente);
            logger.info("Paciente actualizado con exito: " + paciente);
            return paciente;
        } else{
            logger.error("No han podido actualizarse los datos del paciente " + paciente);
            return null;
        }
    }

    @Override
    public void eliminarPaciente(Long id) throws ResourceNotFoundException{
        Optional<Paciente> pacienteBuscado = buscarPacientePorId(id);
        if(pacienteBuscado.isPresent()) {
            repository.deleteById(id);
            logger.info("Paciente eliminado con exito, id: " + id);
        } else{
            logger.error("No se pudo eliminar el paciente con id " + id + ", por ser el mismo inexistente");
            throw new ResourceNotFoundException("No existe un paciente con el id " + id + ", por favor ingrese un id válido.");
        }
    }
}