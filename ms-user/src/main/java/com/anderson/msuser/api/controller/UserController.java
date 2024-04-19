package com.anderson.msuser.api.controller;

import com.anderson.msuser.core.user.dtos.UserDTO;
import com.anderson.msuser.core.user.dtos.UserResponseDTO;
import com.anderson.msuser.core.user.model.User;
import com.anderson.msuser.core.user.services.UserService;
import com.anderson.msuser.api.validation.UserRequestDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

import static com.anderson.msuser.shared.mapper.UserMapper.toUserDTO;
import static com.anderson.msuser.shared.mapper.UserMapper.toUserResponseList;

@RestController
@RequestMapping("/users")
@Transactional
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody @Valid UserRequestDTO data) {
        UserDTO dto = toUserDTO(data);
        UserResponseDTO response = service.insert(dto);

        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri()).body(response);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<UserResponseDTO>> findAll(@RequestHeader(name = "X-Page") Integer page, @RequestHeader(name = "X-Size") Integer size) {
        List<User> users = service.findAll(page, size);
        List<UserResponseDTO> userResponseList = toUserResponseList(users);

        return ResponseEntity.ok(userResponseList);
    }
}
