package com.codeactuator.rocket.dao;

import com.codeactuator.rocket.domain.Project;
import com.codeactuator.rocket.dto.ProjectDTO;
import com.codeactuator.rocket.dto.TaskDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProjectRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ProjectRepository projectRepository;


    @Test
    public void should_create_project(){
        Project atlantis = createProject("ATLANTIS");

        assertThat(atlantis).isNotNull();
        assertThat(atlantis.getId()).isGreaterThan(0);
        assertThat(atlantis).isInstanceOf(Project.class);
    }

    @Test
    public void should_add_task(){
        Project project = createProject("Blue JEANS");
        project = projectRepository.save(project);

        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.unmarshal(project);
        System.out.println(projectDTO);


        assertThat(project).isNotNull();
        assertThat(project.getId()).isGreaterThan(0);
        assertThat(project.getTasks()).isNotEmpty();
    }

    private Project createProject(String projectName){
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setName("Analysis and Documentation");
        ProjectDTO projectDTO = new ProjectDTO.Builder(projectName)
                .task(taskDTO)
                .build();


        Project project = projectDTO.marshall();
        Project resultProject = testEntityManager.persist(project);
        return  resultProject;
    }
}
