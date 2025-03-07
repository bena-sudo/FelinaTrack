package com.pfc.felinatrack_back.model.dto;

import com.pfc.felinatrack_back.model.enums.PermissionName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PermissionEdit {
    private Long id;

    @NotBlank(message = "Permission name cannot be empty")
    private PermissionName name;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;
}
