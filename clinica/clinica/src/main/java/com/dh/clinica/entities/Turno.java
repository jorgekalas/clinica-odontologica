package com.dh.clinica.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "turnos")
public class Turno {

    //Atributos y generación de tablas y relaciones
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @ManyToOne
    @JoinColumn(name="paciente_id", nullable = false)
    private Paciente paciente;
    @ManyToOne
    @JoinColumn(name="odontologo_id", nullable = false)
    private Odontologo odontologo;
    @Column
    private LocalDate fecha;

    //Constructor vacío para Hibernate

    public Turno() {
    }

    //Getters, setters, toString


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Odontologo getOdontologo() {
        return odontologo;
    }

    public void setOdontologo(Odontologo odontologo) {
        this.odontologo = odontologo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Turno [" +
                " id: " + id +
                " - fecha: " + fecha +
                " - paciente: " + paciente.getNombre() + " " + paciente.getApellido() + " (id " + paciente.getId() + ")" +
                " - odontologo: " + odontologo.getNombre() + " " + odontologo.getApellido() + " (id " + odontologo.getId() + ")" +
                "]";
    }
}