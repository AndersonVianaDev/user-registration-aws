package com.anderson.msuser.api.controller;

import com.anderson.msuser.api.validation.UserRequestDTO;
import com.anderson.msuser.core.user.dtos.UserDTO;
import com.anderson.msuser.core.user.dtos.UserResponseDTO;
import com.anderson.msuser.core.user.model.User;
import com.anderson.msuser.core.user.repository.UserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static builders.user.UserBuilder.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@Transactional
class UserControllerTest {

    @Autowired
    ObjectMapper mapper;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserRepository repository;

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

    @Test
    @DisplayName("Find all user successfully")
    void findAll() throws Exception {
        // arrange
        User user1 = toUserNumber(1);
        User user2 = toUserNumber(2);
        User user3 = toUserNumber(3);

        repository.save(user1);
        repository.save(user2);
        repository.save(user3);

        // action
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/users/findAll")
                .contentType(MediaType.APPLICATION_JSON)
                .header("X-Page", 0)
                .header("X-Size", 2))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        String content = result.getResponse().getContentAsString();

        List<UserResponseDTO> userResponseDTOList = mapper.readValue(content, new TypeReference<List<UserResponseDTO>>(){});

        // assertions
        assertEquals(2, userResponseDTOList.size());

    }
}