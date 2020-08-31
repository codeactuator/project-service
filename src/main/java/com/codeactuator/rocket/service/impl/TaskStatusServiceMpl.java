package com.codeactuator.rocket.service.impl;

import com.codeactuator.rocket.dao.TaskStatusRepository;
import com.codeactuator.rocket.domain.TaskStatus;
import com.codeactuator.rocket.dto.TaskStatusDTO;
import com.codeactuator.rocket.exception.TaskStatusNotFoundException;
import com.codeactuator.rocket.service.TaskStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class TaskStatusServiceMpl implements TaskStatusService {


    @Autowired
    private TaskStatusRepository taskStatusRepository;

    @Override
    public Optional<TaskStatusDTO> create(TaskStatusDTO taskStatusDTO) {
        TaskStatus taskStatus = taskStatusDTO.marshall();
        taskStatus = taskStatusRepository.save(taskStatus);
        taskStatusDTO.unmarshal(taskStatus);
        return Optional.of(taskStatusDTO);
    }

    @Override
    public Optional<TaskStatusDTO> update(TaskStatusDTO taskStatusDTO) {
        TaskStatus taskStatus = taskStatusDTO.marshall();
        taskStatus = taskStatusRepository.save(taskStatus);
        taskStatusDTO.unmarshal(taskStatus);
        return Optional.of(taskStatusDTO);
    }

    @Override
    public Optional<TaskStatusDTO> remove(TaskStatusDTO taskStatusDTO) {
        TaskStatus taskStatus = taskStatusDTO.marshall();
        taskStatusRepository.delete(taskStatus);
        taskStatusDTO.unmarshal(taskStatus);
        return Optional.of(taskStatusDTO);
    }

    @Override
    public Optional<TaskStatusDTO> removeById(Long taskStatusId) {
        TaskStatus taskStatus = taskStatusRepository
                .findById(taskStatusId)
                .orElseThrow(() -> new TaskStatusNotFoundException(String.valueOf(taskStatusId)));
        taskStatusRepository.deleteById(taskStatusId);

        TaskStatusDTO taskStatusDTO = new TaskStatusDTO();
        taskStatusDTO.unmarshal(taskStatus);
        return Optional.of(taskStatusDTO);
    }

    @Override
    public Optional<TaskStatusDTO> findById(Long taskStatusId) {
        TaskStatus taskStatus = taskStatusRepository
                .findById(taskStatusId)
                .orElseThrow(() -> new TaskStatusNotFoundException(String.valueOf(taskStatusId)));

        TaskStatusDTO taskStatusDTO = new TaskStatusDTO();
        taskStatusDTO.unmarshal(taskStatus);
        return Optional.of(taskStatusDTO);
    }

    @Override
    public Optional<Collection<TaskStatusDTO>> findAll() {
        Collection<TaskStatusDTO> taskStatusDTOS = new ArrayList<>();
        taskStatusRepository.findAll()
                .forEach(taskStatus -> {
                    TaskStatusDTO taskStatusDTO = new TaskStatusDTO();
                    taskStatusDTO.unmarshal(taskStatus);
                    taskStatusDTOS.add(taskStatusDTO);
                });

        return Optional.of(taskStatusDTOS);
    }

    @Override
    public Optional<Collection<TaskStatusDTO>> findAll(Long organizationId) {
        Collection<TaskStatusDTO> taskStatusDTOS = new ArrayList<>();
        taskStatusRepository.findAll()
                .forEach(taskStatus -> {
                    TaskStatusDTO taskStatusDTO = new TaskStatusDTO();
                    taskStatusDTO.unmarshal(taskStatus);
                    taskStatusDTOS.add(taskStatusDTO);
                });

        return Optional.of(taskStatusDTOS);
    }
}
