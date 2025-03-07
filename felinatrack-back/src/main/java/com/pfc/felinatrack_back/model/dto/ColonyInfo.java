package com.pfc.felinatrack_back.model.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ColonyInfo {
    private Long id;
    private String location;
    private String municipality;
    private Integer quantity;
    private String latitud;
    private String longitud;
    private Set<ResponsibleInfo> responsibles;
    private Set<CatList> cats;
}
