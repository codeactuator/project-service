package com.codeactuator.rocket.controller;


import com.codeactuator.rocket.config.ConfigProperties;
import com.codeactuator.rocket.dto.TaskTypeDTO;
import com.codeactuator.rocket.service.TaskTypeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Log
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
    public void testCreate() throws Exception {
        TaskTypeDTO requestDTO = createObject();
        TaskTypeDTO responseDTO = createObject();
        responseDTO.setId(1L);

        String expectedResponseJson = objectMapper.writeValueAsString(responseDTO);

        //Given
        given(taskTypeService.create(requestDTO))
                .willReturn(Optional.of(responseDTO));

        //When
        mockMvc.perform(
                post(ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("Bug"))
                .andReturn();

    }

    @Test
    public void testFindAll() throws Exception{
        Collection<TaskTypeDTO> taskTypeDTOS = createObjectList().get();
        String expectedResponseJson = objectMapper.writeValueAsString(taskTypeDTOS);

        Mockito.when(taskTypeService.findAll()).thenReturn(createObjectList());
        mockMvc.perform(get(ENDPOINT).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedResponseJson));
    }


    private Optional<Collection<TaskTypeDTO>> createObjectList(){
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

    private TaskTypeDTO createObject(){
        TaskTypeDTO taskTypeDTO = new TaskTypeDTO
                .Builder("Bug")
                .build();
        return taskTypeDTO;
    }


}
