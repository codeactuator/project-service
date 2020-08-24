package com.codeactuator.rocket.service;

import com.codeactuator.rocket.dto.TaskTypeDTO;

import java.util.Collection;
import java.util.Optional;

public interface TaskTypeService {

    Optional<TaskTypeDTO> create(TaskTypeDTO taskTypeDTO);
    Optional<TaskTypeDTO> update(TaskTypeDTO taskTypeDTO);
    Optional<TaskTypeDTO> remove(TaskTypeDTO taskTypeDTO);
    Optional<TaskTypeDTO> removeById(Long taskTypeId);

    Optional<TaskTypeDTO> findById(Long taskTypeId);
    Optional<Collection<TaskTypeDTO>> findAll();
    Optional<Collection<TaskTypeDTO>> findAll(Long organizationId);
}
