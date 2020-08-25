package com.codeactuator.rocket.dao;

import com.codeactuator.rocket.domain.TaskStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TaskStatusRepositoryTest {

    private final String TASK_STATUS_NAME = "QC";

    @Autowired
    private TaskStatusRepository taskStatusRepository;

    @Test
    public void should_create_taskStatus(){
        TaskStatus taskStatus = new TaskStatus();
        taskStatus.setName(TASK_STATUS_NAME);
        taskStatusRepository.save(taskStatus);

        TaskStatus result = taskStatusRepository.findByName(TASK_STATUS_NAME);

        assert (result != null);
        assert (result.getId() != null);
    }
}
