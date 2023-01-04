package com.dh.clinica.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

    @GetMapping("/")
    public ResponseEntity<String> home(){
        return ResponseEntity.ok(
                "<div style=\"display:flex; flex-direction:column; justify-content: center; align-items: center; height:30vh\">\n"+
                        "<h1 style=\"font-size: 2.3rem;\">¡Acceso correcto!</h1> \n"+
                        "<a href=index.html><button type=\"submit\" style=\"background:#457b9d; color: #F1F1F1; padding: 1rem;\n"+
                        "font-size: 1.5rem; border-radius:10px; cursor:pointer\">Ingresar al sitio</button></a>\n"+
                        "</div>\n"
        );
    }

}
