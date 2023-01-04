package com.dh.clinica.controller;

import com.dh.clinica.entities.Odontologo;
import com.dh.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.service.OdontologoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    //Atributo para logging
    private static final Logger logger = Logger.getLogger(OdontologoController.class);

    //Inyección de dependencia del Service
    private OdontologoService odontologoService;

    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    //1. GET

    @GetMapping
    public ResponseEntity<List<Odontologo>> buscarTodosLosOdontologos(){
        if(odontologoService.listarOdontologos().size()>0) {
//            logger.info("Se ha encontrado la siguiente lista de odontologos: " + odontologoService.listarOdontologos());
            return ResponseEntity.ok(odontologoService.listarOdontologos());
        }else{
//            logger.info("La lista de odontologos se encuentra vacia");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @GetMapping("{id}")
    public ResponseEntity<Odontologo> buscarOdontologoPorId(@PathVariable Long id){
        if(odontologoService.buscarOdontologoPorId(id).isPresent()){
            logger.info("Se ha encontrado al odontologo cuyo id es " + id + ": " + odontologoService.buscarOdontologoPorId(id).get());
            return ResponseEntity.ok(odontologoService.buscarOdontologoPorId(id).get());
        } else{
            logger.error("No existe un odontologo con id " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    //2. POST

    @PostMapping
    public ResponseEntity<Odontologo> registrarOdontologo(@RequestBody Odontologo odontologo){
        if(odontologo.getApellido()!=null && odontologo.getNombre()!= null && odontologo.getMatricula() != null) {
            return ResponseEntity.ok(odontologoService.registrarOdontologo(odontologo));
        } else{
            logger.error("No se han podido registrar los datos del odontologo " + odontologo);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

    //3. PUT

    @PutMapping
    public ResponseEntity<Odontologo> actualizarOdontologo(@RequestBody Odontologo odontologo){
        if(odontologo.getId()!=null && odontologo.getApellido()!=null && odontologo.getNombre()!= null && odontologo.getMatricula() != null) {
            return ResponseEntity.ok(odontologoService.actualizarOdontologo(odontologo));
        } else{
            logger.error("No se han podido actualizar los datos del odontologo " + odontologo);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    //4. DELETE

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {

        odontologoService.eliminarOdontologo(id);
        return ResponseEntity.ok("Se ha eliminado exitosamente al odontologo con id " + id);

    }
}
