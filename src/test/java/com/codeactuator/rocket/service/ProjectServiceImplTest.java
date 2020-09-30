package com.codeactuator.rocket.service;


import com.codeactuator.rocket.client.WorkforceFeignClient;
import com.codeactuator.rocket.dao.ProjectRepository;
import com.codeactuator.rocket.dao.TaskRepository;
import com.codeactuator.rocket.domain.Project;
import com.codeactuator.rocket.dto.ProjectDTO;
import com.codeactuator.rocket.dto.TaskDTO;
import com.codeactuator.rocket.dto.WorkforceDTO;
import com.codeactuator.rocket.service.impl.ProjectServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class ProjectServiceImplTest {


    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private WorkforceFeignClient workforceFeignClient;

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private ProjectServiceImpl projectService;


    @BeforeEach
    public void init(){
    }


    @Test
    public void createProject(){
        ProjectDTO projectDTO = new ProjectDTO.Builder("Project Dummyy")
                .id(1L)
                .build();

        Project project = projectDTO.marshall();
        Mockito.when(projectRepository.save(project)).thenReturn(project);


        ProjectDTO result = projectService.create(projectDTO)
                .orElseThrow(() -> new RuntimeException("Could not create policy: " + projectDTO.getName()));


        assertThat(result).isNotNull();
        assertThat(result.getId()).isGreaterThan(0);
        assertThat(result.getName()).contains("Dummy");
    }

    public void update(){

    }

    public void remove(){

    }

    public void removeById(){

    }

    public void findById(){

    }

    public void findAll(){

    }

    public void findAllByOrganizationId(){

    }

    @Test
    public void addResource(){
        ProjectDTO projectDTO = new ProjectDTO.Builder("Project Dummy")
                .id(1l)
                .workforce(new WorkforceDTO.Builder("Shekhar Kumar").build())
                .build();

        Project project = projectDTO.marshall();
        Mockito.when(projectRepository.save(project))
                .thenReturn(project);

        ProjectDTO result = projectService.create(projectDTO)
                .orElseThrow(() -> new RuntimeException("Could not create project: " + project.getName()));

        assertThat(result).isNotNull();
        assertThat(result.getId()).isGreaterThan(0);
        assertThat(result.getName()).contains("Dummy");
        assertThat(result.getWorkforces()).isNotEmpty();
    }

    @Test
    public void addTask(){
        ProjectDTO projectDTO = new ProjectDTO.Builder("Project Dummyy")
                .id(1L)
                .task(new TaskDTO.Builder("Implementation").build())
                .build();

        Project project = projectDTO.marshall();
        Mockito.when(projectRepository.save(project)).thenReturn(project);

        ProjectDTO result = projectService.create(projectDTO)
                .orElseThrow(() -> new RuntimeException("Could not create policy: " + projectDTO.getName()));

        assertThat(result).isNotNull();
        assertThat(result.getId()).isGreaterThan(0);
        assertThat(result.getName()).contains("Dummy");
        assertThat(result.getTasks()).isNotEmpty();
    }

}
