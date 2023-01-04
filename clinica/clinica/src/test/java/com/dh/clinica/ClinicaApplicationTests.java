package com.dh.clinica;

import com.dh.clinica.entities.Domicilio;
import com.dh.clinica.entities.Odontologo;
import com.dh.clinica.entities.Paciente;
import com.dh.clinica.entities.Turno;
import com.dh.clinica.exceptions.BadRequestException;
import com.dh.clinica.exceptions.ResourceNotFoundException;
import com.dh.clinica.service.OdontologoService;
import com.dh.clinica.service.PacienteService;
import com.dh.clinica.service.TurnoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.Assert;
import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
class ClinicaApplicationTests {


    //Inyección de dependecias de los Services
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private OdontologoService odontologoService;


    //0. Inicialización de objetos de prueba

    @BeforeEach
    public void cargaDatos() throws BadRequestException {
        //PACIENTE
        Paciente pacienteTest1 = new Paciente();
        pacienteTest1.setApellido("Kalas");
        pacienteTest1.setNombre("Jorge");
        pacienteTest1.setEmail("jorgekalas@gmail.com");
        pacienteTest1.setDni(36535493);
        pacienteTest1.setFechaIngreso(LocalDate.of(2022,11,11));
        Domicilio domicilioTest1 = new Domicilio();
        domicilioTest1.setCalle("Av. Siempreviva");
        domicilioTest1.setNumero(7500);
        domicilioTest1.setLocalidad("Cordoba");
        domicilioTest1.setProvincia("Cordoba");
        pacienteTest1.setDomicilio(domicilioTest1);
        pacienteService.registrarPaciente(pacienteTest1);

        //ODONTOLOGO

        Odontologo odontologoTest1 = new Odontologo();
        odontologoTest1.setApellido("Perez");
        odontologoTest1.setNombre("Juan");
        odontologoTest1.setMatricula("7145D");
        odontologoService.registrarOdontologo(odontologoTest1);

        //TURNO

        Turno turnoTest1 = new Turno();
        turnoTest1.setPaciente(pacienteTest1);
        turnoTest1.setOdontologo(odontologoTest1);
        turnoTest1.setFecha(LocalDate.of(2022,11,15));
        turnoService.registrarTurno(turnoTest1);

    }

    //1. POST - Tests de prueba de carga

    @Test
    public void pruebaCargaPaciente(){

        //PACIENTE
        Paciente pacienteTest2 = new Paciente();
        pacienteTest2.setApellido("Prueba");
        pacienteTest2.setNombre("De carga");
        pacienteTest2.setEmail("pruebadecarga@gmail.com");
        pacienteTest2.setDni(12345678);
        pacienteTest2.setFechaIngreso(LocalDate.of(2000,01,01));
        Domicilio domicilioTest2 = new Domicilio();
        domicilioTest2.setCalle("Prueba");
        domicilioTest2.setNumero(000);
        domicilioTest2.setLocalidad("Prueba");
        domicilioTest2.setProvincia("Prueba");
        pacienteTest2.setDomicilio(domicilioTest2);


        Paciente pacienteCargado = pacienteService.registrarPaciente(pacienteTest2);

        Assert.assertEquals(pacienteTest2.getId(), pacienteCargado.getId());
    }

    @Test

    public void pruebaCargaOdontologo(){

        //ODONTOLOGO

        Odontologo odontologoTest2 = new Odontologo();
        odontologoTest2.setApellido("Prueba");
        odontologoTest2.setNombre("De carga");
        odontologoTest2.setMatricula("0000TEST");

        Odontologo odontologoCargado = odontologoService.registrarOdontologo(odontologoTest2);

        Assert.assertEquals(odontologoTest2.getId(), odontologoCargado.getId());
    }

    @Test

    public void pruebaCargaTurno() throws BadRequestException {

        //PACIENTE
        Paciente pacienteTest2 = new Paciente();
        pacienteTest2.setApellido("Prueba");
        pacienteTest2.setNombre("De carga");
        pacienteTest2.setEmail("pruebadecarga@gmail.com");
        pacienteTest2.setDni(12345678);
        pacienteTest2.setFechaIngreso(LocalDate.of(2000,01,01));
        Domicilio domicilioTest2 = new Domicilio();
        domicilioTest2.setCalle("Prueba");
        domicilioTest2.setNumero(000);
        domicilioTest2.setLocalidad("Prueba");
        domicilioTest2.setProvincia("Prueba");
        pacienteTest2.setDomicilio(domicilioTest2);
        pacienteService.registrarPaciente(pacienteTest2);

        //ODONTOLOGO

        Odontologo odontologoTest2 = new Odontologo();
        odontologoTest2.setApellido("Prueba");
        odontologoTest2.setNombre("De carga");
        odontologoTest2.setMatricula("0000TEST");
        odontologoService.registrarOdontologo(odontologoTest2);

        //TURNO

        Turno turnoTest2 = new Turno();
        turnoTest2.setPaciente(pacienteTest2);
        turnoTest2.setOdontologo(odontologoTest2);
        turnoTest2.setFecha(LocalDate.of(2022,11,15));


        Turno turnoCargado = turnoService.registrarTurno(turnoTest2);

        Assert.assertEquals(turnoTest2.getId(), turnoCargado.getId());
    }


    //2. GET - Tests de búsqueda por Id y listado completo

    @Test
    public void pruebaBusquedaPacientePorId() {

        Assert.assertNotNull(pacienteService.buscarPacientePorId(1L));
    }

    @Test
    public void pruebaBusquedaOdontologoPorId() {
        Assert.assertNotNull(odontologoService.buscarOdontologoPorId(1L));
    }

    @Test
    public void pruebaBusquedaTurnoPorId() {
        Assert.assertNotNull(turnoService.buscarTurnoPorId(1L));
    }

    @Test
    public void pruebaBusquedaTodosLosPacientes() {
        Assert.assertTrue(pacienteService.listarPacientes().size() > 0);
    }

    @Test
    public void pruebaBusquedaTodosLosOdontologos() {
        Assert.assertTrue(odontologoService.listarOdontologos().size() > 0);
    }

    @Test
    public void pruebaBusquedaTodosLosTurnos() {
        Assert.assertTrue(turnoService.listarTurnos().size() > 0);
    }


    //3. PUT - Tests de actualización de datos


    @Test
    public void pruebaActualizacionDatosPaciente() {
        Paciente pacienteUpdate= new Paciente();
        pacienteUpdate.setApellido("Prueba");
        pacienteUpdate.setNombre("De carga");
        pacienteUpdate.setEmail("pruebadecarga@gmail.com");
        pacienteUpdate.setDni(12345678);
        pacienteUpdate.setFechaIngreso(LocalDate.of(2000,01,01));
        Domicilio domicilioUpdate = new Domicilio();
        domicilioUpdate.setCalle("Prueba");
        domicilioUpdate.setNumero(000);
        domicilioUpdate.setLocalidad("Prueba");
        domicilioUpdate.setProvincia("Prueba");
        pacienteUpdate.setDomicilio(domicilioUpdate);
        pacienteService.registrarPaciente(pacienteUpdate);

        Assert.assertNotNull(pacienteService.actualizarPaciente(pacienteUpdate));
    }

    //NOTA: IDs AUTOINCREMENTALES
    //VALORES 2L HARDCODEADOS PARA CORRER LA SUITE COMPLETA. SI SE DESEA CORRER EL TEST AISLADO, REEMPLAZAR 2L POR 1L
    @Test
    public void pruebaActualizacionDatosOdontologo() {

        //ODONTOLOGO

        Odontologo odontologoUpdate = new Odontologo();
        odontologoUpdate.setId(2L);
        odontologoUpdate.setApellido("Prueba");
        odontologoUpdate.setNombre("De carga");
        odontologoUpdate.setMatricula("0000TEST");

        Assert.assertNotNull(odontologoService.actualizarOdontologo(odontologoUpdate));
    }


    //NOTA: IDs AUTOINCREMENTALES
    //VALORES 2L HARDCODEADOS PARA CORRER LA SUITE COMPLETA. SI SE DESEA CORRER EL TEST AISLADO, REEMPLAZAR TODOS LOS 2L POR 1L

    @Test
    public void pruebaActualizacionDatosTurno() {

        //PACIENTE

        Paciente pacienteUpdate= new Paciente();
        pacienteUpdate.setId(2L);
        pacienteUpdate.setApellido("Prueba");
        pacienteUpdate.setNombre("De carga");
        pacienteUpdate.setEmail("pruebadecarga@gmail.com");
        pacienteUpdate.setDni(12345678);
        pacienteUpdate.setFechaIngreso(LocalDate.of(2000,01,01));
        Domicilio domicilioUpdate = new Domicilio();
        domicilioUpdate.setCalle("Prueba");
        domicilioUpdate.setNumero(000);
        domicilioUpdate.setLocalidad("Prueba");
        domicilioUpdate.setProvincia("Prueba");
        pacienteUpdate.setDomicilio(domicilioUpdate);

        //ODONTOLOGO

        Odontologo odontologoUpdate = new Odontologo();
        odontologoUpdate.setId(2L);
        odontologoUpdate.setApellido("Prueba");
        odontologoUpdate.setNombre("De carga");
        odontologoUpdate.setMatricula("0000TEST");

        //TURNO

        Turno turnoUpdate = new Turno();
        turnoUpdate.setId(2L);
        turnoUpdate.setPaciente(pacienteUpdate);
        turnoUpdate.setOdontologo(odontologoUpdate);
        turnoUpdate.setFecha(LocalDate.of(2022,11,15));


        Assert.assertNotNull(turnoService.actualizarTurno(turnoUpdate));
    }



    //4. DELETE - Tests de eliminación de datos

    @Test
    public void pruebaEliminacionPaciente() throws ResourceNotFoundException {
        //NOTA: IDs AUTOINCREMENTALES
        //DESCOMENTAR LA SIGUIENTE LINEA UNICAMENTE SI SE VA A EJECUTAR EL TEST AISLADO
//        turnoService.eliminarTurno(1L);
        pacienteService.eliminarPaciente(1L);

        Assert.assertEquals(pacienteService.buscarPacientePorId(1L), Optional.empty());
    }

    @Test
    public void pruebaEliminacionOdontologo() throws ResourceNotFoundException {
        //NOTA: IDs AUTOINCREMENTALES
        //DESCOMENTAR LA SIGUIENTE LINEA UNICAMENTE SI SE VA A EJECUTAR EL TEST AISLADO
//        turnoService.eliminarTurno(1L);
        odontologoService.eliminarOdontologo(1L);

        Assert.assertEquals(odontologoService.buscarOdontologoPorId(1L), Optional.empty());
    }

    @Test
    public void pruebaEliminacionTurno() throws ResourceNotFoundException {
        turnoService.eliminarTurno(1L);

        Assert.assertEquals(turnoService.buscarTurnoPorId(1L), Optional.empty());

    }

}
