package com.crimsonhub.CrimsonFinanceAPI.domain.dto.profile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;

public record ProfileUpdateDTO(String email,
                               String password,
                               UpdatedProfile updatedProfile) {

    public record UpdatedProfile(@NotNull(message = "{generic.error}") @NotEmpty(message = "{generic.error}") @NotBlank(message = "{generic.error}") @Size(max = 60, message = "{name.size.error}") String fullName,
                                 @NotNull(message = "{generic.error}") @NotEmpty(message = "{generic.error}") @NotBlank(message = "{generic.error}") @Size(max = 60, message = "{name.size.error}") String preferredName,
                                 @NotNull(message = "{generic.error}") Date birthday,
                                 @NotNull(message = "{phone.error}") @NotEmpty(message = "{phone.error}") @NotBlank(message = "{phone.error}") @Size(max = 13, message = "{phone.size.error}") String phone,
                                 @NotNull(message = "{generic.error}") @NotEmpty(message = "{generic.error}") @NotBlank(message = "{generic.error}") String nationality,
                                 @NotNull(message = "{cep.error}") @NotEmpty(message = "{cep.error}") @NotBlank(message = "{cep.error}") @Size(max = 13, message = "{cep.size.error}") String cep) {
    }
}
