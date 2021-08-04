package com.hackathon.web.controllers;

import com.hackathon.web.HackathonApplication;
import com.hackathon.web.domain.Administrator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class AdministratorLoginControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    AdministratorController administratorController;

    @Test
    void whenValidInput_thenReturns200() throws Exception {

        mockMvc.perform(post("/administrator").params(paramsValid()))
                .andExpect(view().name("administrator"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    void whenInvalidInput_thenReturns400() throws Exception {

        mockMvc.perform(post("/administrator").params(paramsInValid()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is(302));
    }

    private MultiValueMap<String, String> paramsValid(){
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("username", "test");
        params.add("password", "test");
        return params;
    }
    private MultiValueMap<String, String> paramsInValid(){
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("username", "test_INVALID");
        params.add("password", "test_INVALID");
        return params;
    }

}
