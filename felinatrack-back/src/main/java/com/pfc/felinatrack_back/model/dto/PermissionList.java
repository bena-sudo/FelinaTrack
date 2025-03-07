package com.pfc.felinatrack_back.model.dto;

import com.pfc.felinatrack_back.model.enums.PermissionName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PermissionList {
    private Long id;
    private PermissionName name;
    private String description;
}
