package com.codeactuator.projectors.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {

    private Integer id;
    private String name;
    private List<WorkforceDTO> resources = new ArrayList<>();
}
