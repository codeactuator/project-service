package com.codeactuator.rocket.controller;

import com.codeactuator.rocket.config.PropertiesConfig;
import com.codeactuator.rocket.dto.TaskLogDTO;
import com.codeactuator.rocket.service.TaskLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("v1/taskLogs")
@RefreshScope
public class TaskLogController implements ApplicationController<TaskLogDTO> {


    @Autowired
    private TaskLogService taskLogService;

    @Autowired
    private PropertiesConfig propertiesConfig;

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
                .append(propertiesConfig.getMessage());

        return messageBuilder.toString();
    }

    @GetMapping
    @Override
    public Collection<TaskLogDTO> findAll() {
        return taskLogService.findAll().get();
    }

    @GetMapping("/{id}")
    @Override
    public TaskLogDTO findById(@PathVariable(value = "id") Long projectId) {
        return taskLogService.findById(projectId).get();
    }

    @PostMapping
    @Override
    public TaskLogDTO create(@RequestBody TaskLogDTO taskLogDTO) {
        return taskLogService.create(taskLogDTO).get();
    }

    @PutMapping
    @Override
    public TaskLogDTO update(@RequestBody TaskLogDTO taskLogDTO) {
        return taskLogService.update(taskLogDTO).get();
    }

    @DeleteMapping("/{id}")
    @Override
    public TaskLogDTO delete(@PathVariable("id") Long id) {
        return taskLogService.removeById(id).get();
    }
}
