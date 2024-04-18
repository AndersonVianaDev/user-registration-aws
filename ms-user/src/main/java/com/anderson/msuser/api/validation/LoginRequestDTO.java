package com.anderson.msuser.api.validation;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LoginRequestDTO(@NotNull @Email String email, @NotNull @Size(min = 8, max = 12) String password) {
}
