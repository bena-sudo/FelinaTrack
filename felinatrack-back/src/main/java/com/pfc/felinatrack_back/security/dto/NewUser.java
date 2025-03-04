package com.pfc.felinatrack_back.security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class NewUser {
    @NotBlank
    private String name;
    
    @Email
    private String email;
    
    @NotBlank
    private String password;

    private String phone;

    private Set<String> roles = new HashSet<>();
}
