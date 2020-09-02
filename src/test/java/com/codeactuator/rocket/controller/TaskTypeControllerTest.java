package com.codeactuator.rocket.controller;


import com.codeactuator.rocket.config.ConfigProperties;
import com.codeactuator.rocket.dto.TaskTypeDTO;
import com.codeactuator.rocket.service.TaskTypeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {TaskTypeController.class})
public class TaskTypeControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TaskTypeService taskTypeService;

    @MockBean
    private ConfigProperties configProperties;

    private final String ENDPOINT = "/v1/taskTypes";

    @Test
    public void testPing() throws Exception {
        mockMvc.perform(get(ENDPOINT+"/ping").contentType(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=UTF-8"));
    }

    @Test
    public void testFindAll() throws Exception{
        Mockito.when(taskTypeService.findAll()).thenReturn(prepareDataList());
        mockMvc.perform(get(ENDPOINT).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    private Optional<Collection<TaskTypeDTO>> prepareDataList(){
        List<TaskTypeDTO> taskTypeDTOS = new ArrayList<>();
        taskTypeDTOS.add(new TaskTypeDTO
                .Builder("New Feature")
                .id(1L)
                .build());
        taskTypeDTOS.add(new TaskTypeDTO
                .Builder("Enhancement")
                .id(2L)
                .build());
        return  Optional.of(taskTypeDTOS);
    }


}
