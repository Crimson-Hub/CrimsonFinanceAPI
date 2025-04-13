package com.crimsonhub.CrimsonFinanceAPI.domain.dto.profile;

import jakarta.validation.constraints.*;

public record ChangePasswordDTO(@NotNull(message = "{email.error}") @NotEmpty(message = "{email.error}") @NotBlank(message = "{email.error}") @Email(message = "{email.error}") String email,
                                @NotNull(message = "{password.error}") @NotEmpty(message = "{password.error}") @NotBlank(message = "{password.error}") @Size(max = 12, min = 8, message = "{password.size.error}") String password,
                                @NotNull(message = "{password.error}") @NotEmpty(message = "{password.error}") @NotBlank(message = "{password.error}") @Size(max = 12, min = 8, message = "{password.size.error}") String newPassword) {
}
