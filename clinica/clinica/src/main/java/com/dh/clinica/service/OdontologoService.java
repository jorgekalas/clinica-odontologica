package com.dh.clinica.service;

import com.dh.clinica.entities.Odontologo;
import com.dh.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.repository.OdontologoRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService implements OdontologoServiceInterface{

    //Atributo para logging
    private static final Logger logger = Logger.getLogger(OdontologoService.class);


    //Inyección de dependencia del repositorio
    @Autowired
    OdontologoRepository repository;

    //Sobreescritura de métodos

    @Override
    public List<Odontologo> listarOdontologos() {
        return repository.findAll();
    }

    @Override
    public Optional<Odontologo> buscarOdontologoPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Odontologo registrarOdontologo(Odontologo odontologo) {
        if(odontologo.getApellido()!=null && odontologo.getNombre()!= null && odontologo.getMatricula() != null){
            repository.save(odontologo);
            logger.info("Odontologo registrado con exito: " + odontologo);
            return odontologo;
        } else{
            logger.error("No se ha podido registrar al odontologo: " + odontologo);
        }
        return odontologo;
    }

    @Override
    public Odontologo actualizarOdontologo(Odontologo odontologo) {
        if(buscarOdontologoPorId(odontologo.getId()).isPresent()){
            repository.save(odontologo);
            logger.info("Odontologo actualizado con exito: " + odontologo);
            return odontologo;
        } else{
            logger.error("No han podido actualizarse los datos del odontologo " + odontologo);
            return null;
        }
    }

    @Override
    public void eliminarOdontologo(Long id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoBuscado = buscarOdontologoPorId(id);
        if(odontologoBuscado.isPresent()) {
            repository.deleteById(id);
            logger.info("Odontologo eliminado con exito, id: " + id);
        } else{
            logger.error("No se pudo eliminar el odontologo con id " + id + ", por ser el mismo inexistente");
            throw new ResourceNotFoundException("No existe un odontologo con el id " + id + ", por favor ingrese un id valido");
        }
    }
}