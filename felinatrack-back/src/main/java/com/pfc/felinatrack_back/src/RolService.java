package com.pfc.felinatrack_back.src;

import java.util.Optional;

import org.springframework.lang.NonNull;

import com.pfc.felinatrack_back.model.db.RolDb;
import com.pfc.felinatrack_back.model.enums.RoleName;



public interface RolService {
    public Optional<RolDb> getByRoleName(RoleName roleName);
    public void save(@NonNull RolDb rol);
}


