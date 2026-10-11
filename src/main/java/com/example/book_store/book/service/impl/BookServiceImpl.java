package com.example.book_store.book.service.impl;

import com.example.book_store.book.dto.request.CreateBookRequest;
import com.example.book_store.book.dto.response.BookResponse;
import com.example.book_store.book.entity.Book;
import com.example.book_store.book.entity.BookStatus;
import com.example.book_store.book.repository.BookRepository;
import com.example.book_store.book.service.BookService;
import com.example.book_store.common.exception.ResourceAlreadyExistsException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Service layer responsible for handling book-related business operations.
 *
 * <p>Provides functionality to create books,</p>
 *
 * @author Manas Ranjan Mohanta
 * @since 1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    /**
     * Creates a new book after validating ISBN uniqueness.
     *
     * @param request contains the details of the book to create
     * @return the response DTO containing the saved book details
     * @throws ResourceAlreadyExistsException if a book with the ISBN already exists
     */
    @Override
    public BookResponse createBook(CreateBookRequest request) {
        log.info("Initiating book creation with ISBN: {}", request.getIsbn());

        // Validate whether a book with the given ISBN already exists.
        if (bookRepository.existsByIsbn(request.getIsbn())) {
            log.warn("Book creation rejected. ISBN already exists: {}", request.getIsbn());

            throw new ResourceAlreadyExistsException("Book already exists with ISBN: " + request.getIsbn());
        }

        // Build the book entity using the request details.
        Book book = Book.builder()
                .isbn(request.getIsbn())
                .title(request.getTitle())
                .subtitle(request.getSubtitle())
                .description(request.getDescription())
                .language(request.getLanguage())
                .edition(request.getEdition())
                .publicationDate(request.getPublicationDate())
                .price(request.getPrice())
                .originalPrice(request.getOriginalPrice())
                .pages(request.getPages())
                .format(request.getFormat())
                .status(BookStatus.ACTIVE)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        // Save the new book in the database
        Book savedBook = bookRepository.save(book);

        log.info("Book created successfully. Book ID: {}, ISBN: {}",
                savedBook.getId(),
                savedBook.getIsbn()
        );

        // Convert the saved book entity into the API response DTO.
        return mapToResponse(savedBook);
    }

    /**
     * Converts a Book entity into a BookResponse DTO.
     *
     * @param book the entity to convert
     * @return the mapped book response
     */
    private BookResponse mapToResponse(Book book) {
        // Map entity fields required by the API response
        return BookResponse.builder()
                .id(book.getId())
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .subTitle(book.getSubtitle())
                .description(book.getDescription())
                .language(book.getLanguage())
                .edition(book.getEdition())
                .publicationDate(book.getPublicationDate())
                .price(book.getPrice())
                .originalPrice(book.getOriginalPrice())
                .pages(book.getPages())
                .format(book.getFormat())
                .status(book.getStatus())
                .createdAt(book.getCreatedAt())
                .build();
    }
}
