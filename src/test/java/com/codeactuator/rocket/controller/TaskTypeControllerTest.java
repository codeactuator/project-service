package com.codeactuator.rocket.controller;


import com.codeactuator.rocket.config.ConfigProperties;
import com.codeactuator.rocket.dto.TaskTypeDTO;
import com.codeactuator.rocket.service.TaskTypeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


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

    @Test
    public void whenListen_thenReturns200() throws Exception {
        TaskTypeDTO taskTypeDTO = new TaskTypeDTO
                .Builder("Development")
                .build();

        mockMvc.perform(
                MockMvcRequestBuilders.get("/v1/taskTypes/ping"))
                .andExpect(MockMvcResultMatchers.request().asyncNotStarted())
                .andReturn();
    }
}
