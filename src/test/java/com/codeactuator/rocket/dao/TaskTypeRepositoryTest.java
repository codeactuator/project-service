package com.codeactuator.rocket.dao;

import com.codeactuator.rocket.domain.TaskType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class TaskTypeRepositoryTest {


    private final String TASK_TYPE_NAME = "Enhancement";

    @Autowired
    private TaskTypeRepository taskTypeRepository;

    @Test
    public void should_create_taskType(){
        TaskType taskType = new TaskType();
        taskType.setName(TASK_TYPE_NAME);
        taskTypeRepository.save(taskType);

        TaskType result = taskTypeRepository.findByName(TASK_TYPE_NAME);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isGreaterThan(0);
    }
}
