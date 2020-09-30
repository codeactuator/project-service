package com.codeactuator.rocket.controller;

import com.codeactuator.rocket.config.ConfigProperties;
import com.codeactuator.rocket.dto.TaskTypeDTO;
import com.codeactuator.rocket.service.TaskTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


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
        return taskTypeService.findAll().get();
    }

    @GetMapping("/{id}")
    @Override
    public TaskTypeDTO findById(@PathVariable("id") Long taskTypeId) {
        return taskTypeService.findById(taskTypeId).get();
    }

    @PostMapping
    @Override
    public TaskTypeDTO create(@RequestBody TaskTypeDTO taskTypeDTO) {
        return taskTypeService.create(taskTypeDTO).get();
    }

    @PutMapping
    @Override
    public TaskTypeDTO update(@RequestBody TaskTypeDTO taskTypeDTO) {
        return taskTypeService.update(taskTypeDTO).get();
    }

    @DeleteMapping("/{id}")
    @Override
    public TaskTypeDTO delete(@PathVariable(value = "id") Long id) {
        return taskTypeService.removeById(id).get();
    }
}
