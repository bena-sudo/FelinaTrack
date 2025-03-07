package com.pfc.felinatrack_back.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pfc.felinatrack_back.model.db.RoleDb;
import com.pfc.felinatrack_back.model.enums.PermissionName;

public interface PermissionRepository extends JpaRepository<RoleDb, Integer>{
    Optional<RoleDb> findByName(PermissionName permissionName);
}
