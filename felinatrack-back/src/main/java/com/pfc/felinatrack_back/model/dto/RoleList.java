package com.pfc.felinatrack_back.model.dto;

import com.pfc.felinatrack_back.model.enums.RoleName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoleList {
    private Long id;
    private RoleName name;
    private String description;
    private Set<PermissionList> permissions; // Resumen de permisos asociados
}
