package com.crimsonhub.CrimsonFinanceAPI.exception.handler;

import com.crimsonhub.CrimsonFinanceAPI.domain.type.ErrorType;
import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
public class ExceptionHandlerResponse {

    private final LinkedHashMap<String, Object> response;

    public ExceptionHandlerResponse(ErrorType errorType, Map<String, String> errors) {
        response = new LinkedHashMap<>();
        response.put("status", errorType.getCode());
        response.put("message", errorType.getMessage());
        response.put("errors", errors);
    }

    public ExceptionHandlerResponse(ErrorType errorType) {
        response = new LinkedHashMap<>();
        response.put("status", errorType.getCode());
        response.put("message", errorType.getMessage());
        response.put("errors", new LinkedHashMap<>());
    }
}
