package com.pfc.felinatrack_back.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CatInfo {
    private Long id;
    private String name;
    private String gender;
    private String color;
    private String breed;
    private String markings;
    private String health;
}
