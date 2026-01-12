package com.simplebank.handler;

import com.simplebank.exception.BadCredentialsException;
import com.simplebank.exception.NotFoundException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.DateTimeException;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            ConstraintViolationException.class,
            BadRequestException.class,
            MissingServletRequestParameterException.class,
            HttpMessageNotReadableException.class,
            IllegalArgumentException.class,
            DataIntegrityViolationException.class,
            DateTimeException.class
    })
    public ResponseEntity<ErrorResponseDto> handleBadRequests(Exception e) {
        log.error("Bad Requests error: {}", e.getMessage(), e);

        return new ResponseEntity<>(new ErrorResponseDto("400 BAD REQUEST", e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDto handleValidationExceptions(MethodArgumentNotValidException ex) {
        log.error(ex.getMessage(), ex);

        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> String.format("%s: %s", fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.joining("; "));

        return new ErrorResponseDto("400 BAD REQUEST", errorMessage);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ErrorResponseDto handleBadRequestsException(NotFoundException e) {
        log.error("Entity not found error: {}", e.getMessage(), e);

        return new ErrorResponseDto("404 NOT FOUND", e.getMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(BadCredentialsException.class)
    public ErrorResponseDto handleBadCredentialsException(BadCredentialsException e) {
        log.error("User not authorized: {}", e.getMessage(), e);

        return new ErrorResponseDto("401 UNAUTHORIZED", e.getMessage());
    }
}
