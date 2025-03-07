package com.pfc.felinatrack_back.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponsibleInfo {
    private Integer id;
    private String name;
    private String type;
    private String contact;
    private UserInfo user;
}
