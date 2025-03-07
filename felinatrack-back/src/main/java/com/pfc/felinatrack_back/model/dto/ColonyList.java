package com.pfc.felinatrack_back.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ColonyList {
    private Long id;
    private String location;
    private String municipality;
    private Integer quantity;
}
