package com.example.book_store.book.controller;

import com.example.book_store.book.dto.request.CreateBookRequest;
import com.example.book_store.book.dto.response.BookResponse;
import com.example.book_store.book.service.BookService;
import com.example.book_store.common.response.GlobalApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@Validated // REQUIRED: Enables parameter validation on this bean
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    @PostMapping(version = "1.0")
    public ResponseEntity<GlobalApiResponse<BookResponse>> createBookV1(
            @Valid @RequestBody CreateBookRequest request) {

        log.info("Received request to create a book");

        BookResponse response = bookService.createBook(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        GlobalApiResponse.success(
                                HttpStatus.CREATED.value(),
                                "Book Created Successfully.",
                                response
                        )
                );
    }

    @GetMapping(value = "/{isbn}", version = "1.0")
    public ResponseEntity<GlobalApiResponse<BookResponse>> getBookByIsbnV1(
            @PathVariable
            @NotBlank(message = "ISBN must not be blank or null")
            String isbn) {
        log.info("Received request to get a book with ISBN: {}", isbn);

        BookResponse response = bookService.getBookByIsbn(isbn);

        return ResponseEntity
                .ok(GlobalApiResponse.success(
                        HttpStatus.OK.value(),
                        "Book fetched successfully.",
                        response)
                );
    }
}
