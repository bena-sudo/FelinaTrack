package com.pfc.felinatrack_back.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class IssueEdit {
    private Long id;

    @NotBlank
    @Size(max = 50, message = "The type cannot exceed 50 characters")
    @Pattern(regexp = "^(conflict|emergency)$", message = "Type must be 'conflict' or 'emergency'")
    private String type;

    @Size(max = 1000, message = "The description cannot exceed 1000 characters")
    private String description;

    @NotNull(message = "Date cannot be null")
    private LocalDateTime date;

    @NotNull(message = "Colony ID cannot be null")
    private Integer colonyId;
}
