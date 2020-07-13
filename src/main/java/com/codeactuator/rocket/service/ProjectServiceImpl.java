package com.codeactuator.rocket.service;

import com.codeactuator.rocket.dao.ProjectRepository;
import com.codeactuator.rocket.domain.Project;
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

    @Autowired
    private RestTemplate restTemplate;

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
        return projectRepository.findById(projectId).get();
    }

    @Override
    public Collection<Project> findAll() {
        List<Project> projects = new ArrayList<>();
        projectRepository.findAll()
                .forEach(project -> projects.add(project));
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
