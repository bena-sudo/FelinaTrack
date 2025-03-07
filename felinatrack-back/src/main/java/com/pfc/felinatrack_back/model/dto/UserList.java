package com.pfc.felinatrack_back.model.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserList {
    private Long id;
    private String nombre;
    private String email;
    private Integer telefono;
    private String fechaCreacion;
    private Set<RoleInfo> roles;
}
