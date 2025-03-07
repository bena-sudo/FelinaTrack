package com.pfc.felinatrack_back.src.mapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.pfc.felinatrack_back.model.db.RoleDb;
import com.pfc.felinatrack_back.model.dto.RoleInfo;
import com.pfc.felinatrack_back.model.dto.RoleList;



@Mapper
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    RoleInfo roleDbToRoleInfo(RoleDb roleDb);
    Set<RoleInfo> rolesDbToRoleInfo(Set<RoleDb> rolesDb);

    List<RoleList> rolesDbToRoleList(List<RoleDb> rolesDb);
    List<RoleDb> rolesListToRolesDb(List<RoleList> rolesList);

    default Long roleDbToLong(RoleDb roleDb) {
        return roleDb.getId();
    }

    default RoleDb longToRoleDb(Long id) {
        RoleDb roleDb = new RoleDb();
        roleDb.setId(id);
        return roleDb;
    }

    default Set<Long> roleDbSetToLongSet(Set<RoleDb> roles) {
        return roles.stream().map(this::roleDbToLong).collect(Collectors.toSet());
    }

    default Set<RoleDb> longSetToRoleDbSet(Set<Long> roleIds) {
        return roleIds.stream().map(this::longToRoleDb).collect(Collectors.toSet());
    }
}