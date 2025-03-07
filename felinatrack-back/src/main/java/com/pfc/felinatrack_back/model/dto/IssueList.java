package com.pfc.felinatrack_back.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class IssueList {
    private Long id;
    private String type;
    private String date;
    private String description;
}
