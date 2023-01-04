package com.dh.clinica.controller;

import com.dh.clinica.entities.Paciente;
import com.dh.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.service.PacienteService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    //Atributo para logging
    private static final Logger logger = Logger.getLogger(PacienteController.class);

    //Inyección de dependencia del Service
    private PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    //1. GET

    @GetMapping("{id}")
    public ResponseEntity<Paciente> buscarPacientePorId(@PathVariable Long id){

        if(pacienteService.buscarPacientePorId(id).isPresent()){
            logger.info("Se ha encontrado al paciente cuyo id es " + id + ": " + pacienteService.buscarPacientePorId(id).get());
            return ResponseEntity.ok(pacienteService.buscarPacientePorId(id).get());
        } else{
            logger.error("No existe un paciente con id " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @GetMapping
    public ResponseEntity<List<Paciente>> listarPacientes(){
        if(pacienteService.listarPacientes().size()>0){
//            logger.info("Se ha encontrado la siguiente lista de pacientes: " + pacienteService.listarPacientes());
            return ResponseEntity.ok(pacienteService.listarPacientes());
        }else{
//            logger.info("La lista de pacientes se encuentra vacia");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    //2. POST

    @PostMapping
    public ResponseEntity<Paciente> registrarPaciente(@RequestBody Paciente paciente){
        if(paciente.getApellido() != null && paciente.getNombre() != null && paciente.getEmail() != null && paciente.getFechaIngreso() != null && paciente.getDomicilio() != null) {
            return ResponseEntity.ok(pacienteService.registrarPaciente(paciente));
        } else{
            logger.error("No se han podido registrar los datos del paciente " + paciente);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    //3. PUT

    @PutMapping
    public ResponseEntity<Paciente> actualizarPaciente(@RequestBody Paciente paciente) {
        if (paciente.getApellido() != null && paciente.getNombre() != null && paciente.getEmail() != null && paciente.getFechaIngreso() != null && paciente.getDomicilio() != null) {
            return ResponseEntity.ok(pacienteService.actualizarPaciente(paciente));
        } else {
            logger.error("No se han podido actualizar los datos del paciente " + paciente);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    //4. DELETE

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPaciente(@PathVariable Long id) throws ResourceNotFoundException {

        pacienteService.eliminarPaciente(id);
        return ResponseEntity.ok("Se ha eliminado exitosamente al paciente con id " + id);

    }
}
