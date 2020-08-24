package com.codeactuator.rocket.dao;

import com.codeactuator.rocket.domain.TaskStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskStatusRepository extends CrudRepository<TaskStatus, Long> {

    public TaskStatus findByName(String name);
}