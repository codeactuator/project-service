package com.codeactuator.rocket.service.impl;

import com.codeactuator.rocket.dao.ProjectRepository;
import com.codeactuator.rocket.domain.Project;
import com.codeactuator.rocket.service.ProjectService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {


    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Project create(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project update(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project remove(Project project) {
        projectRepository.delete(project);
        return project;
    }

    @Override
    public Project removeById(Long projectId) {
        Project project = projectRepository.findById(projectId).get();
        projectRepository.deleteById(projectId);
        return project;
    }

    @Override
    public Project findById(Long projectId) {
        Project project = projectRepository.findById(projectId).get();
        Hibernate.initialize(project.getTasks());
        return project;
    }

    @Override
    public Collection<Project> findAll() {
        List<Project> projects = new ArrayList<>();
        projectRepository.findAll()
                .forEach(project -> {
                    Hibernate.initialize(project.getTasks());
                    projects.add(project);
                });
        return projects;
    }

    @Override
    public Collection<Project> findAll(Long organizationId) {
        List<Project> projects = new ArrayList<>();
        projectRepository.findAll()
                .forEach(project -> projects.add(project));
        return projects;
    }
}
