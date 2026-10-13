package com.example.book_store.book;

import com.example.book_store.book.dto.request.CreateBookRequest;
import com.example.book_store.book.dto.response.BookResponse;
import com.example.book_store.book.entity.Book;
import com.example.book_store.book.entity.BookStatus;
import com.example.book_store.book.repository.BookRepository;
import com.example.book_store.book.service.impl.BookServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    void createBook_shouldCreateBookSuccessfully() {
        // Arrange
        CreateBookRequest request = new CreateBookRequest();

        request.setIsbn("9780134685991");
        request.setTitle("Effective Java");
        request.setDescription("Java best practices");
        request.setLanguage("English");
        request.setPrice(new BigDecimal("850.00"));
        request.setOriginalPrice(new BigDecimal("1000.00"));
        request.setPages(416);
        request.setFormat("Paperback");

        Mockito.when(bookRepository.existsByIsbn(request.getIsbn()))
                .thenReturn(false);

        Book savedBook = Book.builder()
                .id(1L)
                .isbn(request.getIsbn())
                .title(request.getTitle())
                .subtitle(request.getSubtitle())
                .description(request.getDescription())
                .language(request.getLanguage())
                .price(request.getPrice())
                .originalPrice(request.getOriginalPrice())
                .pages(request.getPages())
                .format(request.getFormat())
                .status(BookStatus.ACTIVE)
                .build();
        Mockito.when(bookRepository.save(ArgumentMatchers.any(Book.class)))
                .thenReturn(savedBook);

        // Act
        BookResponse response = bookService.createBook(request);

        // Assert
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getId()).isEqualTo(1L);
        Assertions.assertThat(response.getIsbn()).isEqualTo("9780134685991");
        Assertions.assertThat(response.getTitle()).isEqualTo("Effective Java");
        Assertions.assertThat(response.getPrice()).isEqualTo(new BigDecimal("850.00"));
        Assertions.assertThat(response.getStatus()).isEqualTo(BookStatus.ACTIVE);

        Mockito.verify(bookRepository)
                .existsByIsbn("9780134685991");
        Mockito.verify(bookRepository)
                .save(ArgumentMatchers.any(Book.class));

    }
}
