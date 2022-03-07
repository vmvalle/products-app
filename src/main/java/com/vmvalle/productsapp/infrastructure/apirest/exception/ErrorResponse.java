package com.vmvalle.productsapp.infrastructure.apirest.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private HttpStatus status;

    private LocalDateTime timestamp;

    private String message;

}
