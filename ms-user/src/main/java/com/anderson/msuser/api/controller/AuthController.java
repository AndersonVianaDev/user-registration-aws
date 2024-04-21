package com.anderson.msuser.api.controller;

import com.anderson.msuser.api.validation.LoginRequestDTO;
import com.anderson.msuser.core.auth.dtos.LoginDTO;
import com.anderson.msuser.core.auth.dtos.LoginResponseDTO;
import com.anderson.msuser.core.auth.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.anderson.msuser.shared.mapper.AuthMapper.toLoginDTO;

@RestController
@RequestMapping("/auth")
@Transactional
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/login")
    @Operation(summary = "user login", description = "login user into app")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login successful"),
            @ApiResponse(responseCode = "400", description = "Invalid request format", content = @Content(mediaType = "*/*", schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Invalid credentials", content = @Content(mediaType = "*/*", schema = @Schema(hidden = true)))
    })
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO data) {
        LoginDTO dto = toLoginDTO(data);
        LoginResponseDTO loginResponse = service.login(dto);

        return ResponseEntity.ok(loginResponse);
    }

}
