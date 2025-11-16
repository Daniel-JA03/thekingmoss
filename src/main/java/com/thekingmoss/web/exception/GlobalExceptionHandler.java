package com.thekingmoss.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex) {
        ErrorResponse error = new ErrorResponse("No encontrado", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // manejo de errores del negocio: stock insuficiente, cantidad invalida, etc.
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorResponse> handleIllegalState(IllegalStateException ex) {
        ErrorResponse error = new ErrorResponse("Error de negocio", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST); // 400
    }

    // manejar IllegalArgumentException (cantidad <= 0, etc.)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException ex) {
        ErrorResponse error = new ErrorResponse("Datos inválidos", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST); // 400
    }
}
