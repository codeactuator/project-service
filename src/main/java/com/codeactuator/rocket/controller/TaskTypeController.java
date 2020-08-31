package com.codeactuator.rocket.controller;

import com.codeactuator.rocket.config.ConfigProperties;
import com.codeactuator.rocket.dto.TaskTypeDTO;
import com.codeactuator.rocket.exception.TaskTypeNotFoundException;
import com.codeactuator.rocket.service.TaskTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping("v1/taskTypes")
@RefreshScope
public class TaskTypeController implements ApplicationController<TaskTypeDTO>{


    @Autowired
    private TaskTypeService taskTypeService;

    @Autowired
    private ConfigProperties configProperties;

    @Value("${message:Hello Default!}")
    private String message;

    @Value("${eng: default}")
    private String env;



    @GetMapping("/ping")
    @Override
    public String ping() {
        StringBuilder messageBuilder = new StringBuilder();
        messageBuilder.append("Value Message: ")
                .append(message)
                .append("\n")
                .append("Properties Message: ")
                .append(configProperties.getMessage());

        return messageBuilder.toString();
    }

    @GetMapping
    @Override
    public Collection<TaskTypeDTO> findAll() {
        return taskTypeService.findAll().orElseGet(() -> {
            List<TaskTypeDTO> taskTypeDTOs = Collections.emptyList();
            return taskTypeDTOs;
        });
    }

    @GetMapping("/{id}")
    @Override
    public TaskTypeDTO findById(@PathVariable("id") Long taskTypeId) {
        return taskTypeService.findById(taskTypeId)
                .orElseThrow(() -> new TaskTypeNotFoundException(String.valueOf(taskTypeId)));
    }

    @Override
    public TaskTypeDTO create(TaskTypeDTO taskTypeDTO) {
        return taskTypeService.create(taskTypeDTO)
                .orElseThrow(() -> new RuntimeException("TaskType Could Not Created: "+taskTypeDTO.getName()));
    }

    @Override
    public TaskTypeDTO update(TaskTypeDTO taskTypeDTO) {

        return taskTypeService.update(taskTypeDTO)
                .orElseThrow(() -> new RuntimeException("TaskType Could Not Updated: "+taskTypeDTO.getName()));
    }

    @Override
    public TaskTypeDTO delete(Long id) {
        return taskTypeService.removeById(id)
                .orElseThrow(() -> new RuntimeException("TaskType Could Not Deleted: "+id));
    }
}
