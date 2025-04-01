package com.crimsonhub.CrimsonFinanceAPI.exception.handler;

import com.crimsonhub.CrimsonFinanceAPI.domain.type.ErrorType;
import com.crimsonhub.CrimsonFinanceAPI.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<?> handleAccountNotFoundException(AccountNotFoundException e) {
        return new ResponseEntity<>(
                new ExceptionHandlerResponse(ErrorType.ACCOUNT_NOT_FOUND).getResponse(),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(CardNotFoundException.class)
    public ResponseEntity<?> handleCardNotFoundException(CardNotFoundException e) {
        return new ResponseEntity<>(
                new ExceptionHandlerResponse(ErrorType.CARD_NOT_FOUND).getResponse(),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<?> handleInvalidPasswordException(InvalidPasswordException e) {
        return new ResponseEntity<>(
                new ExceptionHandlerResponse(ErrorType.INVALID_PASSWORD).getResponse(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(ProfileExistsException.class)
    public ResponseEntity<?> handleProfileExistsException(ProfileExistsException e) {
        return new ResponseEntity<>(
                new ExceptionHandlerResponse(ErrorType.PROFILE_CONFLIT).getResponse(),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(ProfileNotFoundException.class)
    public ResponseEntity<?> handleProfileNotFoundException(ProfileNotFoundException e) {
        return new ResponseEntity<>(
                new ExceptionHandlerResponse(ErrorType.PROFILE_NOT_FOUND).getResponse(),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodNotValidException(MethodArgumentNotValidException e) {
        Map<String, String> fieldErrors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error -> {
            fieldErrors.put(error.getField(), error.getDefaultMessage());
        });

        return new ResponseEntity<>(
                new ExceptionHandlerResponse(ErrorType.INVALID_FIELDS, fieldErrors).getResponse(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleGenericException(Exception e) {
        throw new RuntimeException(e);
//        return new ResponseEntity<>("An unexpected error occurred: "
//                + e, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
