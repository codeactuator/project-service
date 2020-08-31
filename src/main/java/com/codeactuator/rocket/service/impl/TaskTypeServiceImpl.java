package com.codeactuator.rocket.service.impl;

import com.codeactuator.rocket.dto.TaskTypeDTO;
import com.codeactuator.rocket.service.TaskTypeService;

import java.util.Collection;
import java.util.Optional;

public class TaskTypeServiceImpl implements TaskTypeService {




    @Override
    public Optional<TaskTypeDTO> create(TaskTypeDTO taskTypeDTO) {
        return Optional.empty();
    }

    @Override
    public Optional<TaskTypeDTO> update(TaskTypeDTO taskTypeDTO) {
        return Optional.empty();
    }

    @Override
    public Optional<TaskTypeDTO> remove(TaskTypeDTO taskTypeDTO) {
        return Optional.empty();
    }

    @Override
    public Optional<TaskTypeDTO> removeById(Long taskTypeId) {
        return Optional.empty();
    }

    @Override
    public Optional<TaskTypeDTO> findById(Long taskTypeId) {
        return Optional.empty();
    }

    @Override
    public Optional<Collection<TaskTypeDTO>> findAll() {
        return Optional.empty();
    }

    @Override
    public Optional<Collection<TaskTypeDTO>> findAll(Long organizationId) {
        return Optional.empty();
    }
}
