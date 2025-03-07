package com.pfc.felinatrack_back.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CatEdit {
    private Integer id;

    @NotBlank
    @Size(max = 255, message = "The name cannot exceed 255 characters")
    private String name;

    @Size(max = 50, message = "The gender cannot exceed 50 characters")
    private String gender;

    @Size(max = 50, message = "The color cannot exceed 50 characters")
    private String color;

    @Size(max = 50, message = "The breed cannot exceed 50 characters")
    private String breed;

    @Size(max = 500, message = "The markings description is too long")
    private String markings;

    @Size(max = 50, message = "The health status cannot exceed 50 characters")
    private String health;
}
