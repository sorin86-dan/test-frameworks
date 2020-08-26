package com.testing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class DefaultTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void checkExceptionEndpoint() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/exception"))
                .andExpect(status().isConflict())
                .andExpect(content().string("Custom Exception"));
    }

    @Test
    public void checkMessageEndpoint() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/message?service=TestServiceX"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string("Hello, TestServiceX!"));
    }

    @Test
    public void checkJSONMessageEndpoint() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/json-message?service=TestServiceJSONX"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.message").value("Hello, TestServiceJSONX!"));
    }

    @Test
    public void checkInvalidEndpoint() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/mssage?service=TestServiceX"))
                .andExpect(status().is4xxClientError())
                .andExpect(content().string(""));
    }

    @Test
    public void checkMessageEndpointInvalidParameter() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/message?serviceX=TestServiceX"))
                .andExpect(status().is4xxClientError())
                .andExpect(content().string(""));
    }

}