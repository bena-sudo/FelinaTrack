package com.pfc.felinatrack_back.model.dto;

import com.pfc.felinatrack_back.model.enums.RoleName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoleEdit {
    private Long id;

    @NotBlank(message = "Role name cannot be empty")
    private RoleName name;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    private Set<Long> permissionIds;
}
