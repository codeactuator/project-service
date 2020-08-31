package com.codeactuator.rocket.controller;

import com.codeactuator.rocket.config.ConfigProperties;
import com.codeactuator.rocket.dto.TaskLogDTO;
import com.codeactuator.rocket.service.TaskLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("v1/taskLogs")
@RefreshScope
public class TaskLogController implements ApplicationController<TaskLogDTO> {


    @Autowired
    private TaskLogService taskLogService;

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
    public Collection<TaskLogDTO> findAll() {
        return null;
    }

    @Override
    public TaskLogDTO findById(Long projectId) {
        return null;
    }

    @Override
    public TaskLogDTO create(TaskLogDTO taskLogDTO) {
        return null;
    }

    @Override
    public TaskLogDTO update(TaskLogDTO taskLogDTO) {
        return null;
    }

    @Override
    public TaskLogDTO delete(Long id) {
        return null;
    }
}
