package com.pfc.felinatrack_back.src.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pfc.felinatrack_back.model.db.RolDb;
import com.pfc.felinatrack_back.model.enums.RoleName;
import com.pfc.felinatrack_back.repository.RoleRepository;
import com.pfc.felinatrack_back.src.RolService;

import org.springframework.lang.NonNull;
import java.util.Optional;

@Service
@Transactional
public class RolServiceImpl implements RolService {

    @Autowired
    RoleRepository rolRepository;

    public Optional<RolDb> getByRoleName(RoleName roleName){
        return rolRepository.findByName(roleName);
    }

    public void save(@NonNull RolDb rol){
        rolRepository.save(rol);
    }
}


