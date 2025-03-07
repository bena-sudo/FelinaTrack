package com.pfc.felinatrack_back.src;

import java.util.Optional;

import org.springframework.lang.NonNull;

import com.pfc.felinatrack_back.model.db.RoleDb;
import com.pfc.felinatrack_back.model.enums.RoleName;



public interface RoleService {
    public Optional<RoleDb> getByRoleName(RoleName roleName);
    public void save(@NonNull RoleDb rol);
}


