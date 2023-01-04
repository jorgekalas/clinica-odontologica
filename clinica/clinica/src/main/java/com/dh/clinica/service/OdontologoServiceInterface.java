package com.dh.clinica.service;

import com.dh.clinica.entities.Odontologo;
import com.dh.clinica.exceptions.ResourceNotFoundException;
import java.util.List;
import java.util.Optional;

public interface OdontologoServiceInterface {

    //Declaración de los métodos a implementar

    public List<Odontologo> listarOdontologos();
    public Optional<Odontologo> buscarOdontologoPorId(Long id);
    public Odontologo registrarOdontologo(Odontologo o);
    public Odontologo actualizarOdontologo(Odontologo odontologo);
    public void eliminarOdontologo(Long id) throws ResourceNotFoundException;

}
