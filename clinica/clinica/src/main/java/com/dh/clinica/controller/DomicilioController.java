package com.dh.clinica.controller;

import com.dh.clinica.entities.Domicilio;
import com.dh.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.service.DomicilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/domicilios")
public class DomicilioController {


    //Inyección de dependencia del Service
    private DomicilioService domicilioService;

    @Autowired
    public DomicilioController(DomicilioService domicilioService) {
        this.domicilioService = domicilioService;
    }

    //1. GET

    @GetMapping
    public List<Domicilio> buscarTodosLosDomicilios(){
        return domicilioService.listarDomicilios();
    }

    @GetMapping("{id}")
    public ResponseEntity<Domicilio> buscarDomicilioPorId(@PathVariable Long id){
        if(domicilioService.buscarDomicilioPorId(id).isPresent()){
            return ResponseEntity.ok(domicilioService.buscarDomicilioPorId(id).get());
        } else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    //2. POST

    @PostMapping
    public Domicilio registrarDomicilio(@RequestBody Domicilio domicilio){
        return domicilioService.registrarDomicilio(domicilio);
    }

    //3. PUT

    @PutMapping
    public Domicilio actualizarDomicilio(@RequestBody Domicilio domicilio){
        return domicilioService.actualizarDomicilio(domicilio);
    }

    //4. DELETE

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarDomicilio(@PathVariable Long id) throws ResourceNotFoundException {

        domicilioService.eliminarDomicilio(id);
        return  ResponseEntity.ok("Se elimino el domicilio exitosamente");
    }
}