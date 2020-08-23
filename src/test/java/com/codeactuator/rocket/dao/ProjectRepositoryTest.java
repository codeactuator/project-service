package com.codeactuator.rocket.dao;

import com.codeactuator.rocket.domain.Project;
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
    public void persist(){
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setName("ATLANTIS");

        Project project = projectDTO.marshall();
        Project resultProject = testEntityManager.persist(project);

        assert(resultProject != null);
        Assert.assertThat(resultProject, Matchers.isA(Project.class));
        Assert.assertTrue(projectRepository.findById(project.getId()).get().equals(project));
    }
}
