package com.dh.clinica;

import com.dh.clinica.entities.Domicilio;
import com.dh.clinica.entities.Odontologo;
import com.dh.clinica.entities.Paciente;
import com.dh.clinica.entities.Turno;
import com.dh.clinica.exceptions.BadRequestException;
import com.dh.clinica.service.OdontologoService;
import com.dh.clinica.service.PacienteService;
import com.dh.clinica.service.TurnoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class TurnosTestIntegracion {

    //Inyección de las dependencias necesarias
    @Autowired
    MockMvc mockMvc;
    @Autowired
    PacienteService pacienteService;
    @Autowired
    OdontologoService odontologoService;
    @Autowired
    TurnoService turnoService;

    //Inicialización de objetos de prueba
    @BeforeAll
    public void cargarDatos() throws BadRequestException {

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


    //Prueba de integración: se verifica que la lista de turnos no se encuentre vacía

    @Test
    public void buscarTodosLosTurnos() throws Exception {
        //Carga de información
        this.cargarDatos();
        //Seteo de parámetros para el test
        MvcResult respuesta = mockMvc.perform(MockMvcRequestBuilders.get("/turnos")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        //Prueba de aserción
        Assert.assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }
}