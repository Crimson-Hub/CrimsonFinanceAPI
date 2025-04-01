package com.crimsonhub.CrimsonFinanceAPI.domain.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorType {

    PROFILE_NOT_FOUND(404, "Perfil não encontrado."),
    ACCOUNT_NOT_FOUND(404, "Conta não encontrada."),
    CARD_NOT_FOUND(404, "Cartão não encontrado."),
    INVALID_PASSWORD(0, "Acesso negado."),
    PROFILE_CONFLIT(409, "Perfil duplicado."),
    INVALID_FIELDS(400, "Erro de validação em campos.");

    private final int code;
    private final String message;
}
