package com.codeactuator.rocket.dao;


import com.codeactuator.rocket.domain.Project;
import com.codeactuator.rocket.domain.Task;
import com.codeactuator.rocket.domain.TaskStatus;
import com.codeactuator.rocket.domain.TaskType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskTypeRepository taskTypeRepository;

    @Autowired
    private TaskStatusRepository taskStatusRepository;

    @Autowired
    private ProjectRepository projectRepository;


    private final String PROJECT_NAME = "Infra Blue";
    private final String TASK_NAME = "Documentation";
    private final String TASK_TYPE = "FEATURE";
    private final String TASK_STATUS = "NOT STARTED";


    @Before
    public void init(){
        Project project = new Project();
        project.setName(PROJECT_NAME);
        projectRepository.save(project);

        TaskType taskType = new TaskType();
        taskType.setName(TASK_TYPE);
        taskTypeRepository.save(taskType);

        TaskStatus taskStatus = new TaskStatus();
        taskStatus.setName(TASK_STATUS);
        taskStatusRepository.save(taskStatus);
    }

    @Test
    public void should_create_task(){
        createTask("Documentation");
        Task task = taskRepository.findByName("Documentation");

        assert (task != null);
        assert (task.getId() > 0);
        assert (task.getTaskType().equals(taskTypeRepository.findByName(TASK_TYPE)));
        assert (task.getStatus().equals(taskStatusRepository.findByName(TASK_STATUS)));
        assert (task.getProject().equals(projectRepository.findByName(PROJECT_NAME)));
    }


    private Task createTask(String taskName){
        Task task = new Task();
        task.setName(taskName);

        //Task Type and Status
        task.setStatus(taskStatusRepository.findAll().iterator().next());
        task.setTaskType(taskTypeRepository.findAll().iterator().next());

        Project project = projectRepository.findByName(PROJECT_NAME);
        task.setProject(project);

        taskRepository.save(task);
        return task;
    }
}

