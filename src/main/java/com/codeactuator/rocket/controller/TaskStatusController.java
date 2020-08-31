package com.codeactuator.rocket.controller;

import com.codeactuator.rocket.config.ConfigProperties;
import com.codeactuator.rocket.dto.TaskStatusDTO;
import com.codeactuator.rocket.exception.TaskTypeNotFoundException;
import com.codeactuator.rocket.service.TaskStatusService;
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
@RequestMapping("v1/taskStatuses")
@RefreshScope
public class TaskStatusController implements ApplicationController<TaskStatusDTO> {

    @Autowired
    private TaskStatusService taskStatusService;

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
    public Collection<TaskStatusDTO> findAll() {
        return taskStatusService.findAll().orElseGet(() -> {
            List<TaskStatusDTO> taskStatusDTOS = Collections.emptyList();
            return taskStatusDTOS;
        });
    }

    @GetMapping("/{id}")
    @Override
    public TaskStatusDTO findById(@PathVariable("id") Long taskStatusId) {
        return taskStatusService.findById(taskStatusId)
                .orElseThrow(() -> new TaskTypeNotFoundException(String.valueOf(taskStatusId)));
    }

    @Override
    public TaskStatusDTO create(TaskStatusDTO taskStatusDTO) {
        return taskStatusService.create(taskStatusDTO)
                .orElseThrow(() -> new RuntimeException("TaskStatus Could Not Created: "+taskStatusDTO.getName()));
    }

    @Override
    public TaskStatusDTO update(TaskStatusDTO taskStatusDTO) {

        return taskStatusService.update(taskStatusDTO)
                .orElseThrow(() -> new RuntimeException("TaskStatus Could Not Updated: "+taskStatusDTO.getName()));
    }

    @Override
    public TaskStatusDTO delete(Long id) {
        return taskStatusService.removeById(id)
                .orElseThrow(() -> new RuntimeException("TaskStatus Could Not Deleted: "+id));
    }
}
