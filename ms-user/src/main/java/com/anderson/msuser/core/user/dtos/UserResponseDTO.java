package com.anderson.msuser.core.user.dtos;

import java.util.UUID;

public record UserResponseDTO(UUID id, String name, String email) {
}
