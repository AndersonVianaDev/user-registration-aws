package com.anderson.msuser.api.controller;

import com.anderson.msuser.api.validation.UserRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

import static builders.user.UserBuilder.toUserRequestDTO;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@Transactional
class UserControllerTest {

    @Autowired
    ObjectMapper mapper;

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("Register user successfully")
    void register() throws Exception {
        UserRequestDTO dto = toUserRequestDTO();

        String dtoString = mapper.writeValueAsString(dto);

        mockMvc.perform(MockMvcRequestBuilders.post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoString))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }

}