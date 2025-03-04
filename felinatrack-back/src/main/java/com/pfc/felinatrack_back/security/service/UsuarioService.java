package com.pfc.felinatrack_back.security.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfc.felinatrack_back.model.db.UserDb;
import com.pfc.felinatrack_back.repository.UserRepository;

import org.springframework.lang.NonNull;

import java.util.Optional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    UserRepository usuarioRepository;


    public Optional<UserDb> getByEmail(String email){
        return usuarioRepository.findByEmail(email);
    }

    public boolean existsByEmail(String email){
        return usuarioRepository.existsByEmail(email);
    }

    public void save(@NonNull UserDb usuario){
        usuarioRepository.save(usuario);
    }

}