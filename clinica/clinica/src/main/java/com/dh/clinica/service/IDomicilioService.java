package com.dh.clinica.service;

import com.dh.clinica.entities.Domicilio;
import com.dh.clinica.exceptions.ResourceNotFoundException;
import java.util.List;
import java.util.Optional;

public interface IDomicilioService {

    //Declaración de los métodos a implementar

    public List<Domicilio> listarDomicilios();
    public Optional<Domicilio> buscarDomicilioPorId(Long id);
    public Domicilio registrarDomicilio(Domicilio domicilio) ;
    public Domicilio actualizarDomicilio(Domicilio domicilio);
    public void eliminarDomicilio(Long id) throws ResourceNotFoundException;

}
