package com.codeactuator.rocket.dao;

import com.codeactuator.rocket.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

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


    @BeforeEach
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

        assertThat(task).isNotNull();
        assertThat(task.getId()).isGreaterThan(0);
        assertThat(task.getTaskType()).isEqualTo(taskTypeRepository.findByName(TASK_TYPE));
        assertThat(task.getStatus()).isEqualTo(taskStatusRepository.findByName(TASK_STATUS));
        assertThat(task.getProject()).isEqualTo(projectRepository.findByName(PROJECT_NAME));
        assertThat(task.getLogs()).isNotEmpty();
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
