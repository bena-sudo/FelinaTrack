package com.pfc.felinatrack_back.security.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pfc.felinatrack_back.model.db.UserDb;
import com.pfc.felinatrack_back.security.entity.MainUser;
import com.pfc.felinatrack_back.security.service.UsuarioService;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserDb> usuarioOpt = usuarioService.getByEmail(email);
        
        if (usuarioOpt.isEmpty()) {
            throw new UsernameNotFoundException("Usuario no encontrado con email: " + email);
        }

        UserDb usuario = usuarioOpt.get();

        // DEBUG: Verificar si los roles del usuario están cargados correctamente
        System.out.println("Usuario encontrado: " + usuario.getEmail());
        System.out.println("Roles del usuario: " + usuario.getRoles());

        return MainUser.build(usuario);
    }
}
