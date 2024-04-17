package com.anderson.msuser.api.validation;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRequestDTO(@NotNull String name, @Email @NotNull String email, @Size(min = 8, max = 12) @NotNull String password) {
}
