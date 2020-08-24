package com.codeactuator.rocket.service;

import com.codeactuator.rocket.dto.TaskLogDTO;

import java.util.Collection;
import java.util.Optional;

public interface TaskLogService {

    Optional<TaskLogDTO> create(TaskLogDTO taskLogDTO);
    Optional<TaskLogDTO> update(TaskLogDTO taskLogDTO);
    Optional<TaskLogDTO> remove(TaskLogDTO taskLogDTO);
    Optional<TaskLogDTO> removeById(Long taskLogId);

    Optional<TaskLogDTO> findById(Long taskLogId);
    Optional<Collection<TaskLogDTO>> findAll();
    Optional<Collection<TaskLogDTO>> findAll(Long taskId);
}
