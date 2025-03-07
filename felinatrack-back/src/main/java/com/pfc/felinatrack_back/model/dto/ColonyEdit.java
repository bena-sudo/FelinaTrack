package com.pfc.felinatrack_back.model.dto;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ColonyEdit {
    private Long id;

    @NotNull(message = "Quantity cannot be null")
    private Integer quantity;

    @NotBlank
    @Size(max = 255, message = "The location cannot exceed 255 characters")
    private String location;

    @NotBlank
    @Size(max = 255, message = "The municipality cannot exceed 255 characters")
    private String municipality;

    @Size(max = 50, message = "The latitude cannot exceed 50 characters")
    private String latitud;

    @Size(max = 50, message = "The longitude cannot exceed 50 characters")
    private String longitud;

    @NotNull(message = "At least one responsible must be assigned")
    private Set<Long> responsibleIds;

    private Set<Long> catIds;
}
