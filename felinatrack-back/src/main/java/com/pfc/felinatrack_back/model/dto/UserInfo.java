package com.pfc.felinatrack_back.model.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserInfo {
    private Long id;
    private String name;
    private String email;
    private Integer phone;
    private Set<RoleInfo> roles;
}
