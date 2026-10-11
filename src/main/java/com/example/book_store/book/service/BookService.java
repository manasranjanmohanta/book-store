package com.example.book_store.book.service;

import com.example.book_store.book.dto.request.CreateBookRequest;
import com.example.book_store.book.dto.response.BookResponse;

public interface BookService {
    BookResponse createBook(CreateBookRequest request);
}
