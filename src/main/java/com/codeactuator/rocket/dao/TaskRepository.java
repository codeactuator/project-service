package com.codeactuator.rocket.dao;

import com.codeactuator.rocket.domain.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
}