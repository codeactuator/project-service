package com.codeactuator.rocket.dao;

import com.codeactuator.rocket.domain.TaskType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TaskTypeRepository extends CrudRepository<TaskType, Long> {
}