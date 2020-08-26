package com.codeactuator.rocket.dao;

import com.codeactuator.rocket.domain.TaskType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
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
