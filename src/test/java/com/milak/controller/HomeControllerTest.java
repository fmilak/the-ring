package com.milak.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.runner.RunWith;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testShowHome() throws Exception {
        this.mockMvc.perform(get("/api/home")
                .with(user("admin").password("admin")))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("outcomesList"))
                .andExpect(model().attributeExists("user"));
    }

    @Test
    void testNavigateToRealFrontend() throws Exception {
        this.mockMvc.perform(post("/api/home/update")
                .with(user("admin").password("admin")))
                .andExpect(status().isOk())
                .andExpect(model().attributeDoesNotExist("outcomesList"))
                .andExpect(model().attributeExists("user"));
    }

}
