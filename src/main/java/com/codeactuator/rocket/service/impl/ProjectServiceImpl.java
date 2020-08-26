package com.codeactuator.rocket.service.impl;

import com.codeactuator.rocket.client.WorkforceFeignClient;
import com.codeactuator.rocket.dao.ProjectRepository;
import com.codeactuator.rocket.dao.TaskRepository;
import com.codeactuator.rocket.domain.Project;
import com.codeactuator.rocket.domain.Task;
import com.codeactuator.rocket.domain.Workforce;
import com.codeactuator.rocket.dto.ProjectDTO;
import com.codeactuator.rocket.dto.TaskDTO;
import com.codeactuator.rocket.exception.ProjectNotFoundException;
import com.codeactuator.rocket.service.ProjectService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {


    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private WorkforceFeignClient workforceFeignClient;

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Optional<ProjectDTO> create(ProjectDTO projectDTO) {
        Project project = projectDTO.marshall();
        project = projectRepository.save(project);
        projectDTO.setId(project.getId());
        return Optional.of(projectDTO);
    }

    @Override
    public Optional<ProjectDTO> update(ProjectDTO projectDTO) {
        Project project = projectDTO.marshall();
        projectRepository.save(project);
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

    @Override
    public Optional<ProjectDTO> resources(Long projectId, Long workforceId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException(projectId.toString()));


        Workforce workforce = new Workforce();
        workforce.setId(workforceId);
        project.addResource(workforce);

        projectRepository.save(project);

        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.unmarshal(project);

        return Optional.of(projectDTO);
    }

    @Override
    public Optional<ProjectDTO> tasks(Long projectId, TaskDTO taskDTO) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException(projectId.toString()));


        Task task = taskDTO.marshall();
        //taskRepository.save(task);

        project.getTasks().add(task);


        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.unmarshal(project);

        return Optional.of(projectDTO);
    }

}
