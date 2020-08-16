package com.codeactuator.rocket.service.impl;

import com.codeactuator.rocket.dao.ProjectRepository;
import com.codeactuator.rocket.domain.Project;
import com.codeactuator.rocket.domain.Workforce;
import com.codeactuator.rocket.dto.ProjectDTO;
import com.codeactuator.rocket.error.ProjectNotFoundException;
import com.codeactuator.rocket.service.ProjectService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.lang.String.*;

@Service
public class ProjectServiceImpl implements ProjectService {


    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Optional<ProjectDTO> create(ProjectDTO projectDTO) {
        Project project = projectDTO.marshall();
        projectRepository.save(project);
        projectDTO.setId(project.getId());
        return Optional.of(projectDTO);
    }

    @Override
    public Optional<ProjectDTO> update(ProjectDTO projectDTO) {
        Project project = projectDTO.marshall();
        Workforce workforce = project.getResources()
                .stream()
                .collect(Collectors.toCollection(ArrayList::new))
                .get(0);

        //TODO FEIGN CLIENT HERE


        projectRepository.save(project);
        projectDTO.setId(project.getId());
        return Optional.of(projectDTO);
    }

    @Override
    public Optional<ProjectDTO> remove(ProjectDTO projectDTO) {
        Project project = projectDTO.marshall();
        projectRepository.delete(project);
        projectDTO.setId(project.getId());
        return Optional.of(projectDTO);
    }

    @Override
    public Optional<ProjectDTO> removeById(Long projectId) {
        Project project = projectRepository.findById(projectId).get();
        projectRepository.deleteById(projectId);
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.unmarshal(project);
        return Optional.of(projectDTO);
    }

    @Override
    public Optional<ProjectDTO> findById(Long projectId) {

        /*
        ProjectDTO projectDTO = new ProjectDTO();
        projectRepository.findById(projectId)
                .ifPresent(project -> {
                    Hibernate.initialize(project.getTasks());
                    projectDTO.unmarshal(project);
                });
        */

        Optional<ProjectDTO> projectDTOOptional = projectRepository.findById(projectId)
                .map(new Function<Project, ProjectDTO>() {
                    @Override
                    public ProjectDTO apply(Project project) {
                        Hibernate.initialize(project.getTasks());
                        ProjectDTO projectDTO = new ProjectDTO();
                        projectDTO.unmarshal(project);
                        return projectDTO;
                    }
                });

        return projectDTOOptional;
    }

    @Override
    public Optional<Collection<ProjectDTO>> findAll() {
        List<Project> projects = new ArrayList<>();
        projectRepository.findAll()
                .forEach(project -> {
                    Hibernate.initialize(project.getTasks());
                    projects.add(project);
                });

        List<ProjectDTO> projectDTOList = projects.stream()
                .map(project -> {
                    ProjectDTO projectDTO = new ProjectDTO();
                    projectDTO.unmarshal(project);
                    return projectDTO;
                })
                .collect(Collectors.toList());
        return Optional.of(projectDTOList);
    }

    @Override
    public Optional<Collection<ProjectDTO>> findAll(Long organizationId) {
        List<Project> projects = new ArrayList<>();
        projectRepository.findAll()
                .forEach(project -> {
                    Hibernate.initialize(project.getTasks());
                    projects.add(project);
                });

        List<ProjectDTO> projectDTOList = projects.stream()
                .map(project -> {
                    ProjectDTO projectDTO = new ProjectDTO();
                    projectDTO.unmarshal(project);
                    return projectDTO;
                })
                .collect(Collectors.toList());
        return Optional.of(projectDTOList);
    }
}
