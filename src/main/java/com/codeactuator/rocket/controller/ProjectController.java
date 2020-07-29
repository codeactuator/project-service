package com.codeactuator.rocket.controller;

import com.codeactuator.rocket.config.ConfigProperties;
import com.codeactuator.rocket.dao.ProjectRepository;
import com.codeactuator.rocket.domain.Project;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/projects")
@RefreshScope
public class ProjectController {


    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ConfigProperties configProperties;


    @GetMapping("/ping")
    public String ping(){
        return configProperties.getMessage() + configProperties.getEnv();
    }

    @GetMapping
    public List<Project> findAll(){
        List<Project> projects = new ArrayList<>();
        projectRepository
                .findAll()
                .forEach(projects::add);
        return  projects;
    }

    @HystrixCommand(commandKey = "byId", groupKey = "byId", fallbackMethod = "fallBackFindById")
    @GetMapping("/{id}")
    public Project findById(@PathVariable("id")Long projectId){
        /*
        Project project = projectRepository.findById(projectId).get();
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(project.getId());
        projectDTO.setName(project.getName());

        ResponseEntity<WorkforceDTO> stringResponseEntity =
                restTemplate.getForEntity("http://WORKFORCE-SERVICE/workforce/" + project.getId(), WorkforceDTO.class);
        projectDTO.getResources().add(stringResponseEntity.getBody());
        return projectDTO;
        */

        return projectRepository.findById(projectId).get();

    }

    public Project fallBackFindById(Long projectId){
        Project project = projectRepository.findById(projectId).get();
        /*
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(project.getId());
        projectDTO.setName(project.getName());
        return projectDTO;
        */
        return project;
    }
}
