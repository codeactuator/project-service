package com.codeactuator.rocket.service;

import com.codeactuator.rocket.dto.ProjectDTO;

import java.util.Collection;
import java.util.Optional;

public interface ProjectService {

    Optional<ProjectDTO> create(ProjectDTO projectDTO);
    Optional<ProjectDTO> update(ProjectDTO projectDTO);
    Optional<ProjectDTO> remove(ProjectDTO projectDTO);
    Optional<ProjectDTO> removeById(Long projectId);

    Optional<ProjectDTO> findById(Long projectId);
    Optional<Collection<ProjectDTO>> findAll();
    Optional<Collection<ProjectDTO>> findAll(Long organizationId);
}
