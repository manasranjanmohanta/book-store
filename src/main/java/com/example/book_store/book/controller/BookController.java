package com.example.book_store.book.controller;

import com.example.book_store.book.dto.request.CreateBookRequest;
import com.example.book_store.book.dto.response.BookResponse;
import com.example.book_store.book.service.BookService;
import com.example.book_store.common.response.GlobalApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
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
}
