package com.example.book_store.common.exception;

import com.example.book_store.common.response.GlobalApiResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.accept.MissingApiVersionException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handler for Resource Already Exists errors
    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<GlobalApiResponse<Void>> handleResourceAlreadyExistsException(
            ResourceAlreadyExistsException exception) {

        log.warn("Resource conflict: {}", exception.getMessage());

        GlobalApiResponse<Void> response = GlobalApiResponse.error(
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.name(),
                exception.getMessage()
        );
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(response);
    }

    // Handler for Jakarta Validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GlobalApiResponse<Map<String, String>>> handleValidationExceptions(
            MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        GlobalApiResponse<Map<String, String>> validationResponse = new GlobalApiResponse<>(
                HttpStatus.BAD_REQUEST.value(),
                "Validation Failed",
                "Input data validation failed",
                errors
        );
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(validationResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GlobalApiResponse<Void>> handleUnexpectedException(
            Exception exception) {

        log.error("Unexpected application error", exception);

        GlobalApiResponse<Void> response = GlobalApiResponse.error(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.name(),
                "An unexpected error occurred."
        );
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<GlobalApiResponse<Void>> handleResourceNotFoundException(ResourceNotFoundException exception) {
        log.warn("Resource not found: {}", exception.getMessage());

        GlobalApiResponse<Void> response = GlobalApiResponse.error(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.name(),
                exception.getMessage()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    @ExceptionHandler(MissingApiVersionException.class)
    public ResponseEntity<GlobalApiResponse<Void>> handleMissingApiVersionException(MissingApiVersionException exception) {
        log.error("Missing API Versioning", exception);

        GlobalApiResponse<Void> response = GlobalApiResponse.error(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.name(),
                "Missing API Versioning Header");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<GlobalApiResponse<Void>> handleConstraintViolationException(ConstraintViolationException exception) {
        log.error("Constraint violation exception", exception);

        GlobalApiResponse<Void> response = GlobalApiResponse.error(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.name(),
                exception.getMessage()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }
}
