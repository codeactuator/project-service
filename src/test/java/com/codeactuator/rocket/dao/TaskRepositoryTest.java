package com.codeactuator.rocket.dao;


import com.codeactuator.rocket.domain.Task;
import com.codeactuator.rocket.domain.TaskStatus;
import com.codeactuator.rocket.domain.TaskType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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

    @Before
    public void init(){
        TaskType taskType = new TaskType();
        taskType.setName("Feature");
        taskTypeRepository.save(taskType);

        TaskStatus taskStatus = new TaskStatus();
        taskStatus.setName("NOT_STARTED");
        taskStatusRepository.save(taskStatus);
    }

    @Test
    public void should_create_task(){
        createTask("Documentation");
        Task task = taskRepository.findByName("Documentation");

        assert (task != null);
        assert (task.getId() > 0);
        assert (task.getTaskType().equals(taskTypeRepository.findByName("Feature")));
        assert (task.getStatus().equals(taskStatusRepository.findByName("NOT_STARTED")));
    }


    private Task createTask(String taskName){
        Task task = new Task();
        task.setName(taskName);

        //Task Type and Status
        task.setStatus(taskStatusRepository.findAll().iterator().next());
        task.setTaskType(taskTypeRepository.findAll().iterator().next());

        taskRepository.save(task);
        return task;
    }
}

