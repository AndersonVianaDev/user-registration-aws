package com.anderson.msuser.api.validation;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRequestDTO(@NotNull(message = "name field cannot be null.") String name,
                             @Email(message = "email format is not valid.") @NotNull(message = "email field cannot be null.") String email,
                             @Size(min = 8, max = 12, message = "the password must be between 8 to 12 digits.") @NotNull(message = "password field cannot be null.") String password) {
}
