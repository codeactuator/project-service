package com.codeactuator.rocket.controller;

import com.codeactuator.rocket.config.ConfigProperties;
import com.codeactuator.rocket.dao.ProjectRepository;
import com.codeactuator.rocket.dto.ProjectDTO;
import com.codeactuator.rocket.error.ProjectNotFoundException;
import com.codeactuator.rocket.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;

@RestController
@RequestMapping(value = "v1/projects")
@RefreshScope
public class ProjectController {


    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ConfigProperties configProperties;


    @GetMapping("/ping")
    public String ping(){
        return configProperties.getMessage() + configProperties.getEnv();
    }

    @GetMapping
    public Collection<ProjectDTO> findAll(){
        return projectService.findAll().get();
    }

    //@HystrixCommand(commandKey = "byId", groupKey = "byId", fallbackMethod = "fallBackFindById")
    @GetMapping("/{id}")
    public ProjectDTO findById(@PathVariable("id")Long projectId){
        return projectService.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException(String.valueOf(projectId)));
    }


    @PostMapping
    public ProjectDTO create(@RequestBody ProjectDTO projectDTO){
        return projectService.create(projectDTO)
                .orElseThrow(() -> new RuntimeException("Project could not created: "+projectDTO));
    }


    @PutMapping
    public ProjectDTO update(@RequestBody ProjectDTO projectDTO){
        return projectService.create(projectDTO)
                .orElseThrow(() -> new RuntimeException("Project could not created: "+projectDTO));
    }

    @DeleteMapping("/{id}")
    public ProjectDTO delete(@PathVariable("id")Long projectId){
        return projectService.removeById(projectId)
                .orElseThrow(() -> new RuntimeException("Project could not deleted: "+projectId));
    }


    public ResponseEntity<ProjectDTO> fallBackFindById(Long projectId){
        //Project project = projectRepository.findById(projectId).get();
        ProjectDTO projectDTO = new ProjectDTO();
        return ResponseEntity.ok(projectDTO);
    }
}
