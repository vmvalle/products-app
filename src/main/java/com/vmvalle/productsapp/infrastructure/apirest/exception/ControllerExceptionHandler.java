package com.vmvalle.productsapp.infrastructure.apirest.exception;

import com.vmvalle.productsapp.domain.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {

    /**
     * Handler for ResourceNotFoundException
     *
     * @param ex ResourceNotFoundException
     * @return ErrorResponse
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse resourceNotFoundException(ResourceNotFoundException ex) {
        log.warn("ResourceNotFoundException: {}", ex.getMessage());
        return new ErrorResponse(HttpStatus.NOT_FOUND, LocalDateTime.now(), ex.getMessage());
    }

}