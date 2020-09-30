package com.codeactuator.rocket.service;

import com.codeactuator.rocket.dto.TaskStatusDTO;

import java.util.Collection;
import java.util.Optional;

public interface TaskStatusService {

    Optional<TaskStatusDTO> create(TaskStatusDTO taskStatusDTO);
    Optional<TaskStatusDTO> update(TaskStatusDTO taskStatusDTO);
    Optional<TaskStatusDTO> remove(TaskStatusDTO taskStatusDTO);
    Optional<TaskStatusDTO> removeById(Long taskStatusId);

    Optional<TaskStatusDTO> findById(Long taskStatusId);
    Optional<Collection<TaskStatusDTO>> findAll();
    Optional<Collection<TaskStatusDTO>> findAll(Long organizationId);
}
