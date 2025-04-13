package com.crimsonhub.CrimsonFinanceAPI.domain.dto.security;

import jakarta.validation.constraints.*;

import java.sql.Timestamp;

public record RegisterDTO(@NotNull(message = "{email.error}") @NotEmpty(message = "{email.error}") @NotBlank(message = "{email.error}") @Email(message = "{email.error}") String email,
                          @NotNull(message = "{password.error}") @NotEmpty(message = "{password.error}") @NotBlank(message = "{password.error}") @Size(max = 12, min = 8, message = "{password.size.error}") String password,
                          @NotNull(message = "{generic.error}") @NotEmpty(message = "{generic.error}") @NotBlank(message = "{generic.error}") @Size(max = 60, message = "{name.size.error}") String fullName) {
}
