package com.codeactuator.rocket.controller;

import com.codeactuator.rocket.config.PropertiesConfig;
import com.codeactuator.rocket.dto.TaskStatusDTO;
import com.codeactuator.rocket.service.TaskStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("v1/taskStatuses")
@RefreshScope
public class TaskStatusController implements ApplicationController<TaskStatusDTO> {

    @Autowired
    private TaskStatusService taskStatusService;

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
    public Collection<TaskStatusDTO> findAll() {
        return taskStatusService.findAll().get();
    }

    @GetMapping("/{id}")
    @Override
    public TaskStatusDTO findById(@PathVariable("id") Long taskStatusId) {
        return taskStatusService.findById(taskStatusId).get();
    }

    @PostMapping
    @Override
    public TaskStatusDTO create(@RequestBody TaskStatusDTO taskStatusDTO) {
        return taskStatusService.create(taskStatusDTO).get();
    }

    @PutMapping
    @Override
    public TaskStatusDTO update(@RequestBody TaskStatusDTO taskStatusDTO) {

        return taskStatusService.update(taskStatusDTO).get();
    }

    @DeleteMapping("/{id}")
    @Override
    public TaskStatusDTO delete(@PathVariable(value = "id") Long id) {
        return taskStatusService.removeById(id).get();
    }
}
