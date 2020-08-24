package com.codeactuator.rocket.dao;

import com.codeactuator.rocket.domain.Project;
import com.codeactuator.rocket.domain.Task;
import com.codeactuator.rocket.dto.ProjectDTO;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProjectRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private WorkforceRepository workforceRepository;

    @Autowired
    private TaskTypeRepository taskTypeRepository;


    @Test
    public void should_create_project(){
        Project atlantis = createProject("ATLANTIS");
        assert(atlantis != null);
        assert (atlantis.getId() > 0);
        Assert.assertThat(atlantis, Matchers.isA(Project.class));
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

        assert(blue_jeans != null);
        assert(blue_jeans.getTasks() != null);
        assert (blue_jeans.getTasks().size() > 0);
    }

    private Project createProject(String projectName){
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setName(projectName);

        Project project = projectDTO.marshall();
        Project resultProject = testEntityManager.persist(project);
        return  resultProject;
    }
}
