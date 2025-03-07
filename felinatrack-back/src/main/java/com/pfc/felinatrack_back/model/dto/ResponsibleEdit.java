package com.pfc.felinatrack_back.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponsibleEdit {
    private Integer id;

    @NotBlank
    @Size(max = 255, message = "The name cannot exceed 255 characters")
    private String name;

    @NotBlank
    @Size(max = 50, message = "The type cannot exceed 50 characters")
    @Pattern(regexp = "^(association|volunteer|government)$", message = "Type must be 'association', 'volunteer' or 'government'")
    private String type;

    @Size(max = 255, message = "The contact information cannot exceed 255 characters")
    private String contact;

    @NotNull(message = "User ID cannot be null")
    private Long userId;
}
