package com.codeactuator.rocket.service;


import com.codeactuator.rocket.client.WorkforceFeignClient;
import com.codeactuator.rocket.dao.ProjectRepository;
import com.codeactuator.rocket.dao.TaskRepository;
import com.codeactuator.rocket.domain.Project;
import com.codeactuator.rocket.dto.ProjectDTO;
import com.codeactuator.rocket.exception.ProjectNotFoundException;
import com.codeactuator.rocket.service.impl.ProjectServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
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
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setName("Project Dummy");
        projectDTO.setId(1L);

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

    public void resources(){

    }

    public void tasks(){

    }

}
