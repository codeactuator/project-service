package com.codeactuator.rocket.service.impl;

import com.codeactuator.rocket.dto.TaskStatusDTO;
import com.codeactuator.rocket.service.TaskStatusService;

import java.util.Collection;
import java.util.Optional;

public class TaskStatusServiceMpl implements TaskStatusService {

    @Override
    public Optional<TaskStatusDTO> create(TaskStatusDTO taskStatusDTO) {
        return Optional.empty();
    }

    @Override
    public Optional<TaskStatusDTO> update(TaskStatusDTO taskStatusDTO) {
        return Optional.empty();
    }

    @Override
    public Optional<TaskStatusDTO> remove(TaskStatusDTO taskStatusDTO) {
        return Optional.empty();
    }

    @Override
    public Optional<TaskStatusDTO> removeById(Long taskStatusId) {
        return Optional.empty();
    }

    @Override
    public Optional<TaskStatusDTO> findById(Long taskStatusId) {
        return Optional.empty();
    }

    @Override
    public Optional<Collection<TaskStatusDTO>> findAll() {
        return Optional.empty();
    }

    @Override
    public Optional<Collection<TaskStatusDTO>> findAll(Long organizationId) {
        return Optional.empty();
    }
}
