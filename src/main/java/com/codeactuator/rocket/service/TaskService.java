package com.codeactuator.rocket.service;

import com.codeactuator.rocket.dto.TaskDTO;

import java.util.Collection;
import java.util.Optional;

public interface TaskService {

    Optional<TaskDTO> create(TaskDTO taskDTO);
    Optional<TaskDTO> update(TaskDTO taskDTO);
    Optional<TaskDTO> remove(TaskDTO taskDTO);
    Optional<TaskDTO> removeById(Long taskId);

    Optional<TaskDTO> findById(Long taskId);
    Optional<Collection<TaskDTO>> findAll();
    Optional<Collection<TaskDTO>> findAll(Long projectId);
}
