package com.codeactuator.rocket.controller;

import com.codeactuator.rocket.config.ConfigProperties;
import com.codeactuator.rocket.dto.TaskDTO;
import com.codeactuator.rocket.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("v1/tasks")
@RefreshScope
public class TaskController implements ApplicationController<TaskDTO> {


    @Autowired
    private TaskService taskService;

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

    @Override
    public Collection<TaskDTO> findAll() {
        return null;
    }

    @Override
    public TaskDTO findById(Long projectId) {
        return null;
    }

    @Override
    public TaskDTO create(TaskDTO taskDTO) {
        return null;
    }

    @Override
    public TaskDTO update(TaskDTO taskDTO) {
        return null;
    }

    @Override
    public TaskDTO delete(Long id) {
        return null;
    }
}
