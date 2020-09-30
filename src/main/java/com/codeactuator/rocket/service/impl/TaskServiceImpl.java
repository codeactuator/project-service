package com.codeactuator.rocket.service.impl;

import com.codeactuator.rocket.dao.TaskRepository;
import com.codeactuator.rocket.domain.Task;
import com.codeactuator.rocket.dto.TaskDTO;
import com.codeactuator.rocket.exception.TaskNotFoundException;
import com.codeactuator.rocket.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {


    @Autowired
    private TaskRepository taskRepository;


    @Override
    public Optional<TaskDTO> create(TaskDTO taskDTO) {
        Task task = taskDTO.marshall();
        task = taskRepository.save(task);
        taskDTO.unmarshal(task);
        return Optional.of(taskDTO);
    }

    @Override
    public Optional<TaskDTO> update(TaskDTO taskDTO) {
        Task task = taskDTO.marshall();
        task = taskRepository.save(task);
        taskDTO.unmarshal(task);
        return Optional.of(taskDTO);
    }

    @Override
    public Optional<TaskDTO> remove(TaskDTO taskDTO) {
        Task task = taskDTO.marshall();
        taskRepository.delete(task);
        taskDTO.unmarshal(task);
        return Optional.of(taskDTO);
    }

    @Override
    public Optional<TaskDTO> removeById(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(String.valueOf(taskId)));
        taskRepository.deleteById(taskId);

        TaskDTO taskDTO = new TaskDTO();
        taskDTO.unmarshal(task);
        return Optional.of(taskDTO);
    }

    @Override
    public Optional<TaskDTO> findById(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(String.valueOf(taskId)));

        TaskDTO taskDTO = new TaskDTO();
        taskDTO.unmarshal(task);
        return Optional.of(taskDTO);
    }

    @Override
    public Optional<Collection<TaskDTO>> findAll() {
        Collection<TaskDTO> taskDTOS = new ArrayList<>();
        taskRepository.findAll()
                .forEach(task -> {
                    TaskDTO taskDTO = new TaskDTO();
                    taskDTO.unmarshal(task);
                    taskDTOS.add(taskDTO);
                });
        return Optional.of(taskDTOS);
    }

    @Override
    public Optional<Collection<TaskDTO>> findAll(Long projectId) {
        Collection<TaskDTO> taskDTOS = new ArrayList<>();
        taskRepository.findAll()
                .forEach(task -> {
                    TaskDTO taskDTO = new TaskDTO();
                    taskDTO.unmarshal(task);
                    taskDTOS.add(taskDTO);
                });
        return Optional.of(taskDTOS);
    }
}
