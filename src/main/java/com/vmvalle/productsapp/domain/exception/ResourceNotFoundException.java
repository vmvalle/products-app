package com.vmvalle.productsapp.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    /**
     * Constructor of class ResourceNotFoundException.
     *
     * @param message Description error.
     */
    public ResourceNotFoundException(final String message) {
        super(message);
    }

}
