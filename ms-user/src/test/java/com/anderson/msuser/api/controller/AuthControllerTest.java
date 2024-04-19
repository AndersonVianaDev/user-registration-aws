package com.anderson.msuser.api.controller;

import com.anderson.msuser.api.validation.LoginRequestDTO;
import com.anderson.msuser.core.user.dtos.UserDTO;
import com.anderson.msuser.core.user.services.UserService;
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

import static builders.auth.AuthBuilder.toLoginRequestDTO;
import static builders.user.UserBuilder.toUserDTO;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@Transactional
class AuthControllerTest {

    @Autowired
    ObjectMapper mapper;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserService userService;

    @Test
    @DisplayName("login successfully")
    void login() throws Exception {
        // adding user
        UserDTO dto = toUserDTO();
        userService.insert(dto);

        LoginRequestDTO loginDTO = toLoginRequestDTO();

        String dtoString = mapper.writeValueAsString(loginDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dtoString))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

}