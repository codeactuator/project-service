package com.codeactuator.rocket.service.impl;

import com.codeactuator.rocket.dto.TaskDTO;
import com.codeactuator.rocket.service.TaskService;

import java.util.Collection;
import java.util.Optional;

public class TaskServiceImpl implements TaskService {



    @Override
    public Optional<TaskDTO> create(TaskDTO taskDTO) {
        return Optional.empty();
    }

    @Override
    public Optional<TaskDTO> update(TaskDTO taskDTO) {
        return Optional.empty();
    }

    @Override
    public Optional<TaskDTO> remove(TaskDTO taskDTO) {
        return Optional.empty();
    }

    @Override
    public Optional<TaskDTO> removeById(Long taskId) {
        return Optional.empty();
    }

    @Override
    public Optional<TaskDTO> findById(Long taskId) {
        return Optional.empty();
    }

    @Override
    public Optional<Collection<TaskDTO>> findAll() {
        return Optional.empty();
    }

    @Override
    public Optional<Collection<TaskDTO>> findAll(Long projectId) {
        return Optional.empty();
    }
}
