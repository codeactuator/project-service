package com.codeactuator.projectors.controller;

import com.codeactuator.projectors.dao.ProjectRepository;
import com.codeactuator.projectors.domain.Project;
import com.codeactuator.projectors.dto.ProjectDTO;
import com.codeactuator.projectors.dto.WorkforceDTO;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/project")
@RefreshScope
public class ProjectController {


    //@Value("${workforce.service.url}")
    //private String WORKFORCE_SERVICE_URL;

    @Value("${message: Hello Default!}")
    private String message;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private RestTemplate restTemplate;


    @GetMapping("/ping")
    public String ping(){
        return message;
    }

    @GetMapping
    public List<Project> findAll(){
        List<Project> projects = new ArrayList<>();
        projectRepository
                .findAll()
                .forEach(project -> projects.add(project));
        return  projects;
    }

    @HystrixCommand(commandKey = "byId", groupKey = "byId", fallbackMethod = "fallBackFindById")
    @GetMapping("/{id}")
    public ProjectDTO findById(@PathVariable("id")Integer projectId){
        //System.out.println("WORKFORCE_SERVICE_URL: "+WORKFORCE_SERVICE_URL);

        Project project = projectRepository.findById(projectId).get();
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(project.getId());
        projectDTO.setName(project.getName());

        //Application application = eurekaClient.getApplication("WORKFORCE-SERVICE");
        //RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<WorkforceDTO> stringResponseEntity =
                restTemplate.getForEntity("http://WORKFORCE-SERVICE/workforce/" + project.getId(), WorkforceDTO.class);

        projectDTO.getResources().add(stringResponseEntity.getBody());

        return projectDTO;
    }

    @HystrixCommand(commandKey = "findById", groupKey = "findById", fallbackMethod = "fallBackFindById")
    @GetMapping("find/{id}")
    public ProjectDTO find(@PathVariable("id")Integer projectId){
        //System.out.println("WORKFORCE_SERVICE_URL: "+WORKFORCE_SERVICE_URL);

        Project project = projectRepository.findById(projectId).get();
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(project.getId());
        projectDTO.setName(project.getName());

        //Application application = eurekaClient.getApplication("WORKFORCE-SERVICE");
        //RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<WorkforceDTO> stringResponseEntity =
                restTemplate.getForEntity("http://WORKFORCE-SERVICE/workforce/" + project.getId(), WorkforceDTO.class);

        projectDTO.getResources().add(stringResponseEntity.getBody());

        return projectDTO;
    }

    public ProjectDTO fallBackFindById(Integer projectId){
        Project project = projectRepository.findById(projectId).get();
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(project.getId());
        projectDTO.setName(project.getName());
        return projectDTO;
    }
}
