package com.dh.clinica.service;

import com.dh.clinica.entities.Odontologo;
import com.dh.clinica.entities.Paciente;
import com.dh.clinica.entities.Turno;
import com.dh.clinica.exceptions.BadRequestException;
import com.dh.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.repository.OdontologoRepository;
import com.dh.clinica.repository.PacienteRepository;
import com.dh.clinica.repository.TurnoRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService implements TurnoInterface {

    //Atributo para logging
    private static final Logger logger = Logger.getLogger(TurnoService.class);

    //Inyección de dependencia de los repositorios a utilizar
    @Autowired
    TurnoRepository repository;
    @Autowired
    PacienteRepository pacienteRepository;
    @Autowired
    OdontologoRepository odontologoRepository;

    //Sobreescritura de métodos

    @Override
    public List<Turno> listarTurnos(){
        return repository.findAll();
    }

    @Override
    public Optional<Turno> buscarTurnoPorId(Long id){
        return repository.findById(id);
    }

    @Override
    public Turno registrarTurno(Turno turno) throws BadRequestException {
        Optional<Paciente> pacienteBuscado = pacienteRepository.findById(turno.getPaciente().getId());
        Optional<Odontologo> odontologoBuscado = odontologoRepository.findById(turno.getOdontologo().getId());
        if(pacienteBuscado.isPresent() && odontologoBuscado.isPresent()){
            repository.save(turno);
            logger.info("Turno registrado con exito: " + turno);
            return turno;
        } else{
            logger.error("No se ha podido registrar el turno: " + turno);
            throw new BadRequestException("No se han encontrado en la base de datos el paciente o el odontólogo que se intentan asociar a este turno");
        }
    }

    @Override
    public Turno actualizarTurno(Turno turno){
        if(buscarTurnoPorId(turno.getId()).isPresent()){
            repository.save(turno);
            logger.info("Turno actualizado con exito: " + turno);
            return turno;

        } else{
            logger.error("No han podido actualizarse los datos del turno " + turno);
            return null;
        }
    }

    @Override
    public void eliminarTurno(Long id) throws ResourceNotFoundException{
        Optional<Turno> turnoBuscado = buscarTurnoPorId(id);
        if(turnoBuscado.isPresent()) {
            repository.deleteById(id);
            logger.info("Turno eliminado con exito, id: " + id);
        }else{
            logger.error("No se pudo eliminar el turno con id " + id + ", por ser el mismo inexistente");
            throw new ResourceNotFoundException("No existe un turno con el id " + id + ", por favor ingrese un id válido.");
        }
    }
}
