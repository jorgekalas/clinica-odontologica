package com.dh.clinica.exceptions;

public class BadRequestException extends Exception{

    //Constructor de la clase
    public BadRequestException(String mensaje){
        super(mensaje);
    }
}
