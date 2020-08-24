package com.codeactuator.rocket.controller;

import com.codeactuator.rocket.client.WorkforceFeignClient;
import com.codeactuator.rocket.config.ConfigProperties;
import com.codeactuator.rocket.dao.ProjectRepository;
import com.codeactuator.rocket.dto.ProjectDTO;
import com.codeactuator.rocket.dto.TaskDTO;
import com.codeactuator.rocket.exception.ProjectNotFoundException;
import com.codeactuator.rocket.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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

    @Autowired
    private WorkforceFeignClient workforceFeignClient;


    @Value("${message:Hello Default!}")
    private String message;
    @Value("${eng: default}")
    private String env;

    @GetMapping("/ping")
    public String ping(){
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("Value Message: ")
                .append(message)
                .append("\n")
                .append("Properties Message: ")
                .append(configProperties.getMessage());

        return messageBuilder.toString();
    }

    @GetMapping
    public Collection<ProjectDTO> findAll(){
        return projectService.findAll().get();
    }

    //@HystrixCommand(commandKey = "byId", groupKey = "byId", fallbackMethod = "fallBackFindById")
    @GetMapping("/{id}")
    public ProjectDTO findById(@PathVariable("id")Long projectId){
        ProjectDTO projectDTO = projectService.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException(String.valueOf(projectId)));

        return projectDTO;
    }


    @PostMapping
    public ProjectDTO create(@RequestBody ProjectDTO projectDTO){
        return projectService.create(projectDTO)
                .orElseThrow(() -> new RuntimeException("Project could not created: "+projectDTO));
    }


    @PutMapping
    public ProjectDTO update(@RequestBody ProjectDTO projectDTO){
        return projectService.update(projectDTO)
                .orElseThrow(() -> new RuntimeException("Project could not updated: "+projectDTO));
    }

    @PutMapping("/{projectId}/workforces/{workforceId}")
    public ProjectDTO addResources(@PathVariable("projectId") Long projectId,
                             @PathVariable("workforceId") Long workforceId){

        return projectService.resources(projectId, workforceId)
                .orElseThrow(() -> new RuntimeException("Trying to add resource to invalid project: "+projectId));
    }

    @PutMapping("/{projectId}/tasks")
    public ProjectDTO addResources(@PathVariable("projectId") Long projectId,
                                   @RequestBody TaskDTO taskDTO){

        return projectService.tasks(projectId, taskDTO)
                .orElseThrow(() -> new RuntimeException("Trying to create task into invalid project: "+projectId));
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
