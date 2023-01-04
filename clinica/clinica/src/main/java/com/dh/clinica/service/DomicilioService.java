package com.dh.clinica.service;

import com.dh.clinica.entities.Domicilio;
import com.dh.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.repository.DomicilioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DomicilioService implements IDomicilioService{

    //Inyección de dependencia del repositorio
    @Autowired
    DomicilioRepository repository;

    //Sobreescritura de métodos

    @Override
    public List<Domicilio> listarDomicilios() {
        return repository.findAll();
    }

    @Override
    public Optional<Domicilio> buscarDomicilioPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Domicilio registrarDomicilio(Domicilio domicilio) {
        return repository.save(domicilio);
    }

    @Override
    public Domicilio actualizarDomicilio(Domicilio domicilio) {
        if(buscarDomicilioPorId(domicilio.getId()).isPresent()){
            return repository.save(domicilio);
        } else{
            return null;
        }
    }

    @Override
    public void eliminarDomicilio(Long id) throws ResourceNotFoundException{
        Optional<Domicilio> domicilioBuscado = buscarDomicilioPorId(id);
        if(domicilioBuscado.isPresent()) {
            repository.deleteById(id);
        } else{
            throw new ResourceNotFoundException("No existe un domicilio con el id " + id + ", por favor ingrese un id válido.");
        }
    }
}