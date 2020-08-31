package com.codeactuator.rocket.service.impl;

import com.codeactuator.rocket.dto.TaskLogDTO;
import com.codeactuator.rocket.service.TaskLogService;

import java.util.Collection;
import java.util.Optional;

public class TaskLogServiceImpl implements TaskLogService {
    @Override
    public Optional<TaskLogDTO> create(TaskLogDTO taskLogDTO) {
        return Optional.empty();
    }

    @Override
    public Optional<TaskLogDTO> update(TaskLogDTO taskLogDTO) {
        return Optional.empty();
    }

    @Override
    public Optional<TaskLogDTO> remove(TaskLogDTO taskLogDTO) {
        return Optional.empty();
    }

    @Override
    public Optional<TaskLogDTO> removeById(Long taskLogId) {
        return Optional.empty();
    }

    @Override
    public Optional<TaskLogDTO> findById(Long taskLogId) {
        return Optional.empty();
    }

    @Override
    public Optional<Collection<TaskLogDTO>> findAll() {
        return Optional.empty();
    }

    @Override
    public Optional<Collection<TaskLogDTO>> findAll(Long taskId) {
        return Optional.empty();
    }
}
