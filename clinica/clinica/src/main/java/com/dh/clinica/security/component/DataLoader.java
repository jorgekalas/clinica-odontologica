package com.dh.clinica.security.component;

import com.dh.clinica.security.entity.AppUsuario;
import com.dh.clinica.security.entity.UsuarioRoles;
import com.dh.clinica.security.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {


    private IUsuarioRepository usuarioRepository;
    @Autowired
    public DataLoader(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
        String password1= passwordEncoder.encode("abcd");
        String password2= passwordEncoder.encode("abcd");

        usuarioRepository.save(new AppUsuario("Jorge","Kalas","jorgekalas@gmail.com", password1, UsuarioRoles.ROLE_ADMIN));
        usuarioRepository.save(new AppUsuario("Juan","Perez","juanperez@gmail.com", password2, UsuarioRoles.ROLE_USER));
    }
}
