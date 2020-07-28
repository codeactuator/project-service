package com.codeactuator.rocket.service;

import com.codeactuator.rocket.domain.Project;

import java.util.Collection;

public interface ProjectService {

    Project create(Project project);
    Project update(Project project);
    Project remove(Project project);
    Project removeById(Long projectId);

    Project findById(Long projectId);
    Collection<Project> findAll();
    Collection<Project> findAll(Long organizationId);
}
