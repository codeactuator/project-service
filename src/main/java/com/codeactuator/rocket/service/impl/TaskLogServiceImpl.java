package com.codeactuator.rocket.service.impl;

import com.codeactuator.rocket.dao.TaskLogRepository;
import com.codeactuator.rocket.domain.TaskLog;
import com.codeactuator.rocket.dto.TaskLogDTO;
import com.codeactuator.rocket.exception.TaskLogNotFoundException;
import com.codeactuator.rocket.service.TaskLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class TaskLogServiceImpl implements TaskLogService {


    @Autowired
    private TaskLogRepository taskLogRepository;

    @Override
    public Optional<TaskLogDTO> create(TaskLogDTO taskLogDTO) {
        TaskLog taskLog = taskLogDTO.marshall();
        taskLog = taskLogRepository.save(taskLog);
        taskLogDTO.unmarshal(taskLog);
        return Optional.of(taskLogDTO);
    }

    @Override
    public Optional<TaskLogDTO> update(TaskLogDTO taskLogDTO) {
        TaskLog taskLog = taskLogDTO.marshall();
        taskLog = taskLogRepository.save(taskLog);
        taskLogDTO.unmarshal(taskLog);
        return Optional.of(taskLogDTO);
    }

    @Override
    public Optional<TaskLogDTO> remove(TaskLogDTO taskLogDTO) {
        TaskLog taskLog = taskLogDTO.marshall();
        taskLogRepository.delete(taskLog);
        taskLogDTO.unmarshal(taskLog);
        return Optional.of(taskLogDTO);
    }

    @Override
    public Optional<TaskLogDTO> removeById(Long taskLogId) {
        TaskLog taskLog = taskLogRepository.findById(taskLogId)
                .orElseThrow(() -> new TaskLogNotFoundException(String.valueOf(taskLogId)));
        taskLogRepository.deleteById(taskLogId);
        TaskLogDTO taskLogDTO = new TaskLogDTO();
        taskLogDTO.unmarshal(taskLog);
        return Optional.of(taskLogDTO);
    }

    @Override
    public Optional<TaskLogDTO> findById(Long taskLogId) {
        TaskLog taskLog = taskLogRepository.findById(taskLogId)
                .orElseThrow(() -> new TaskLogNotFoundException(String.valueOf(taskLogId)));

        TaskLogDTO taskLogDTO = new TaskLogDTO();
        taskLogDTO.unmarshal(taskLog);
        return Optional.of(taskLogDTO);
    }

    @Override
    public Optional<Collection<TaskLogDTO>> findAll() {
        Collection<TaskLogDTO> taskLogDTOS = new ArrayList<>();
        taskLogRepository.findAll()
                .forEach(taskLog -> {
                    TaskLogDTO taskLogDTO = new TaskLogDTO();
                    taskLogDTO.unmarshal(taskLog);
                    taskLogDTOS.add(taskLogDTO);
                });
        return Optional.of(taskLogDTOS);
    }

    @Override
    public Optional<Collection<TaskLogDTO>> findAll(Long taskId) {
        Collection<TaskLogDTO> taskLogDTOS = new ArrayList<>();
        taskLogRepository.findAll()
                .forEach(taskLog -> {
                    TaskLogDTO taskLogDTO = new TaskLogDTO();
                    taskLogDTO.unmarshal(taskLog);
                    taskLogDTOS.add(taskLogDTO);
                });
        return Optional.of(taskLogDTOS);
    }
}
