package com.codeactuator.rocket.dao;

import com.codeactuator.rocket.domain.TaskType;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TaskTypeRepository extends CrudRepository<TaskType, Long> {

    public TaskType findByName(String name);
}