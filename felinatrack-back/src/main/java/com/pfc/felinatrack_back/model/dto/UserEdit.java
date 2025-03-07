package com.pfc.felinatrack_back.model.dto;

import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserEdit {
    
    @Schema(example = "12345", description = "Unique user ID")
    private Long id;

    @NotBlank
    @Size(max = 255, message = "The name cannot exceed 255 characters")
    @Schema(example = "Juan Pérez", description = "User's full name")
    private String name;

    @NotBlank
    @Email
    @Size(max = 255, message = "The email cannot exceed 255 characters")
    @Schema(example = "example@example.com", description = "User's email address")
    private String email;
    
    @Size(max = 15, message = "The phone number cannot exceed 15 characters")
    @Pattern(regexp = "^\\+?[0-9]*$", message = "The phone number can only contain digits and optionally a country code prefix (+)")
    @Schema(example = "+34123456789", description = "User's phone number (with or without country code prefix)")
    private String phone;
    
    @Schema(description = "IDs of the roles associated with the user")
    private Set<Long> roleIds;
}
