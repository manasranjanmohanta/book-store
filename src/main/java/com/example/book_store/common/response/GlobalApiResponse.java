package com.example.book_store.common.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Represents the standard response structure for REST APIs.
 *
 * @param <T> the type of response data
 */
@Getter
@Setter
public class GlobalApiResponse<T> {
    private final LocalDateTime timestamp;
    private final int status;
    private final String error;
    private final String message;
    private final T data;

    public GlobalApiResponse(int status, String error, String message, T data) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.error = error;
        this.message = message;
        this.data = data;
    }

    /**
     * Creates a successful API response.
     */
    // Static factory method for Success responses
    public static <T> GlobalApiResponse<T> success(int status, String message, T data) {
        return new GlobalApiResponse<>(status, null, message, data);
    }

    /**
     * Creates an error API response.
     */
    // Static factory method for Error responses
    public static <T> GlobalApiResponse<T> error(int status, String error, String message) {
        return new GlobalApiResponse<>(status, error, message, null);
    }
}
