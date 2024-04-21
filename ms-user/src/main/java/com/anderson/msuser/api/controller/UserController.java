package com.anderson.msuser.api.controller;

import com.anderson.msuser.core.user.dtos.UserDTO;
import com.anderson.msuser.core.user.dtos.UserResponseDTO;
import com.anderson.msuser.core.user.model.User;
import com.anderson.msuser.core.user.services.UserService;
import com.anderson.msuser.api.validation.UserRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "register user", description = "register new user")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "successfully registered"),
            @ApiResponse(responseCode = "400", description = "Invalid request format", content = @Content(mediaType = "*/*", schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "409", description = "Conflict, email already registered", content = @Content(mediaType = "*/*", schema = @Schema(hidden = true)))
    })
    public ResponseEntity<UserResponseDTO> register(@RequestBody @Valid UserRequestDTO data) {
        UserDTO dto = toUserDTO(data);
        UserResponseDTO response = service.insert(dto);

        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri()).body(response);
    }

    @GetMapping("/findAll")
    @Operation(summary = "find all users", description = "search for all users in the database, only admin has access")
    @ApiResponse(responseCode = "200", description = "Fetch all users successfully")
    public ResponseEntity<List<UserResponseDTO>> findAll(@RequestHeader(name = "X-Page") Integer page, @RequestHeader(name = "X-Size") Integer size, @RequestHeader(name = "Authorization") String token) {
        List<User> users = service.findAll(page, size);
        List<UserResponseDTO> userResponseList = toUserResponseList(users);

        return ResponseEntity.ok(userResponseList);
    }
}
