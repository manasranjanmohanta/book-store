package com.example.book_store.book;

import com.example.book_store.book.entity.Book;
import com.example.book_store.book.entity.BookStatus;
import com.example.book_store.book.repository.BookRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void saveBook_shouldPersistBook() {
        // Arrange
        Book book = Book.builder()
                .isbn("9780134685991")
                .title("Effective Java")
                .description("Java best practices")
                .language("English")
                .price(new BigDecimal("850.00"))
                .originalPrice(new BigDecimal("1000.00"))
                .pages(416)
                .format("Paperback")
                .status(BookStatus.ACTIVE)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        // Act
        Book savedBook = bookRepository.save(book);

        // Assert
        Assertions.assertThat(savedBook.getId()).isNotNull();
        Assertions.assertThat(savedBook.getIsbn())
                .isEqualTo("9780134685991");
        Assertions.assertThat(savedBook.getTitle())
                .isEqualTo("Effective Java");
    }
}
