package com.codeactuator.projectors.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Project {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Integer> resources = new ArrayList<>();
}
