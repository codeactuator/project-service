package com.codeactuator.rocket.dao;

import com.codeactuator.rocket.domain.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TaskLogRepositoryTest {


    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskTypeRepository taskTypeRepository;

    @Autowired
    private TaskStatusRepository taskStatusRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskLogRepository taskLogRepository;


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
    public void should_create_taskLog(){
        createTaskLog(TASK_NAME);
        Task task = taskRepository.findByName(TASK_NAME);

        assert (task != null);
        assert (task.getId() > 0);
        assert (task.getTaskType().equals(taskTypeRepository.findByName(TASK_TYPE)));
        assert (task.getStatus().equals(taskStatusRepository.findByName(TASK_STATUS)));
        assert (task.getProject().equals(projectRepository.findByName(PROJECT_NAME)));
        assert (task.getLogs().size() > 0);
    }


    private TaskLog createTaskLog(String taskName){
        Task task = new Task();
        task.setName(taskName);

        //Task Type and Status
        task.setStatus(taskStatusRepository.findAll().iterator().next());
        task.setTaskType(taskTypeRepository.findAll().iterator().next());

        Project project = projectRepository.findByName(PROJECT_NAME);
        task.setProject(project);


        TaskLog taskLog = new TaskLog();
        taskLog.setCreatedDate(Calendar.getInstance().getTime());
        taskLog.setSpentHrs(3.5f);
        taskLog.setDescription("Study and analyzed the requirement specification");

        task.addLogs(taskLog);
        taskRepository.save(task);
        return taskLog;
    }
}
