package com.dh.clinica.exceptions;

public class ResourceNotFoundException extends Exception{

    //Constructor de la clase
    public ResourceNotFoundException(String mensaje){
        super(mensaje);
    }
}
