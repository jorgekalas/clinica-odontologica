package com.dh.clinica.controller;

import com.dh.clinica.entities.Turno;
import com.dh.clinica.exceptions.BadRequestException;
import com.dh.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.service.OdontologoService;
import com.dh.clinica.service.PacienteService;
import com.dh.clinica.service.TurnoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    //Atributo para logging
    private static final Logger logger = Logger.getLogger(TurnoController.class);


    //Inyección de dependenciaS de los Services
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;

    //1. GET

    @GetMapping
    public ResponseEntity<List<Turno>> listarTurnos(){
        if(turnoService.listarTurnos().size()>0) {
//            logger.info("Se ha encontrado la siguiente lista de turnos: " + turnoService.listarTurnos());
            return ResponseEntity.ok(turnoService.listarTurnos());
        }else{
//            logger.info("La lista de turnos se encuentra vacia");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Turno> buscarTurnoPorId(@PathVariable Long id){
        if(turnoService.buscarTurnoPorId(id).isPresent()){
            logger.info("Se ha encontrado el turno cuyo id es " + id + ": " + turnoService.buscarTurnoPorId(id).get());
            return ResponseEntity.ok(turnoService.buscarTurnoPorId(id).get());
        } else{
            logger.error("No existe un turno con id " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    //2. POST

    @PostMapping
    public ResponseEntity<String> registrarTurno(@RequestBody Turno turno) throws BadRequestException {
        turnoService.registrarTurno(turno);
        return ResponseEntity.ok("El turno ha sido registrado exitosamente");
    }


    //3. PUT

    @PutMapping
    public ResponseEntity<Turno> actualizarTurno(@RequestBody Turno turno){
        if(turno.getId()!=null && turno.getPaciente().getId()!= null && turno.getOdontologo().getId() != null) {
            return ResponseEntity.ok(turnoService.actualizarTurno(turno));
        } else{
            logger.error("No se han podido actualizar los datos del turno " + turno);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


    //4. DELETE

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id) throws ResourceNotFoundException {

        turnoService.eliminarTurno(id);
        return ResponseEntity.ok("Se elimino el turno exitosamente");

    }
}