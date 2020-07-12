package com.codeactuator.rocket.dao;

import com.codeactuator.rocket.domain.TaskLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TaskLogRepository extends CrudRepository<TaskLog, Long>{

}