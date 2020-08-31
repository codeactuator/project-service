package com.codeactuator.rocket.service.impl;

import com.codeactuator.rocket.dao.TaskTypeRepository;
import com.codeactuator.rocket.domain.TaskType;
import com.codeactuator.rocket.dto.TaskTypeDTO;
import com.codeactuator.rocket.exception.TaskTypeNotFoundException;
import com.codeactuator.rocket.service.TaskTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class TaskTypeServiceImpl implements TaskTypeService {


    @Autowired
    private TaskTypeRepository taskTypeRepository;


    @Override
    public Optional<TaskTypeDTO> create(TaskTypeDTO taskTypeDTO) {
        TaskType taskType = taskTypeDTO.marshall();
        taskType = taskTypeRepository.save(taskType);
        taskTypeDTO.unmarshal(taskType);
        return Optional.of(taskTypeDTO);
    }

    @Override
    public Optional<TaskTypeDTO> update(TaskTypeDTO taskTypeDTO) {
        TaskType taskType = taskTypeDTO.marshall();
        taskType = taskTypeRepository.save(taskType);
        taskTypeDTO.unmarshal(taskType);
        return Optional.of(taskTypeDTO);
    }

    @Override
    public Optional<TaskTypeDTO> remove(TaskTypeDTO taskTypeDTO) {
        TaskType taskType = taskTypeDTO.marshall();
        taskTypeRepository.delete(taskType);
        taskTypeDTO.unmarshal(taskType);
        return Optional.of(taskTypeDTO);
    }

    @Override
    public Optional<TaskTypeDTO> removeById(Long taskTypeId) {
        TaskType taskType = taskTypeRepository
                .findById(taskTypeId)
                .orElseThrow(() -> new TaskTypeNotFoundException(String.valueOf(taskTypeId)));
        taskTypeRepository.deleteById(taskTypeId);

        TaskTypeDTO taskTypeDTO = new TaskTypeDTO();
        taskTypeDTO.unmarshal(taskType);
        return Optional.of(taskTypeDTO);
    }

    @Override
    public Optional<TaskTypeDTO> findById(Long taskTypeId) {
        TaskType taskType = taskTypeRepository
                .findById(taskTypeId)
                .orElseThrow(() -> new TaskTypeNotFoundException(String.valueOf(taskTypeId)));

        TaskTypeDTO taskTypeDTO = new TaskTypeDTO();
        taskTypeDTO.unmarshal(taskType);
        return Optional.of(taskTypeDTO);
    }

    @Override
    public Optional<Collection<TaskTypeDTO>> findAll() {
        Collection<TaskTypeDTO> taskTypeDTOS = new ArrayList<>();
        taskTypeRepository.findAll()
                .forEach(taskType -> {
                    TaskTypeDTO taskTypeDTO = new TaskTypeDTO();
                    taskTypeDTO.unmarshal(taskType);
                    taskTypeDTOS.add(taskTypeDTO);
                });

        return Optional.of(taskTypeDTOS);
    }

    @Override
    public Optional<Collection<TaskTypeDTO>> findAll(Long organizationId) {
        Collection<TaskTypeDTO> taskTypeDTOS = new ArrayList<>();
        taskTypeRepository.findAll()
                .forEach(taskType -> {
                    TaskTypeDTO taskTypeDTO = new TaskTypeDTO();
                    taskTypeDTO.unmarshal(taskType);
                    taskTypeDTOS.add(taskTypeDTO);
                });

        return Optional.of(taskTypeDTOS);
    }
}
