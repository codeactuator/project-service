package com.codeactuator.rocket.dao;

import com.codeactuator.rocket.domain.TaskStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
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

        assertThat(result).isNotNull();
        assertThat(result.getId()).isGreaterThan(0);
    }
}
