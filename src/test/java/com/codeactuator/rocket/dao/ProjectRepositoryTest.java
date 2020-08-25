package com.codeactuator.rocket.dao;

import com.codeactuator.rocket.domain.Project;
import com.codeactuator.rocket.domain.Task;
import com.codeactuator.rocket.dto.ProjectDTO;
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
        Project blue_jeans = createProject("Blue JEANS");

        Task task = new Task();
        task.setName("Analysis and Documentation");
        blue_jeans.addTask(task);
        blue_jeans = projectRepository.save(blue_jeans);

        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.unmarshal(blue_jeans);
        System.out.println(projectDTO);


        assertThat(blue_jeans).isNotNull();
        assertThat(blue_jeans.getId()).isGreaterThan(0);
        assertThat(blue_jeans.getTasks()).isNotEmpty();
    }

    private Project createProject(String projectName){
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setName(projectName);

        Project project = projectDTO.marshall();
        Project resultProject = testEntityManager.persist(project);
        return  resultProject;
    }
}
