package com.pfc.felinatrack_back.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pfc.felinatrack_back.model.db.RolDb;
import com.pfc.felinatrack_back.model.enums.PermissionName;

public interface PermissionRepository extends JpaRepository<RolDb, Integer>{
    Optional<RolDb> findByName(PermissionName permissionName);
}
