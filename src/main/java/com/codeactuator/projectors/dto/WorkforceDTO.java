package com.codeactuator.projectors.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkforceDTO {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String designation;
}
