package com.pfc.felinatrack_back.security.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfc.felinatrack_back.model.db.RoleDb;
import com.pfc.felinatrack_back.model.enums.RoleName;
import com.pfc.felinatrack_back.repository.RoleRepository;
import com.pfc.felinatrack_back.security.service.RolDetailsService;

import org.springframework.lang.NonNull;
import java.util.Optional;

@Service
@Transactional
public class RolDetailsServiceImpl implements RolDetailsService {

    @Autowired
    RoleRepository rolRepository;

    public Optional<RoleDb> getByRoleName(RoleName rolNombre){
        return rolRepository.findByName(rolNombre);
    }

    public void save(@NonNull RoleDb rol){
        rolRepository.save(rol);
    }
}


